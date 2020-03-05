const outAccount = document.querySelector("#outAccount");
const inAccount = document.querySelector("#inAccount");
const inUser = document.querySelector("#inUser");
const inputSumWallet = document.querySelector("#inputSumWallet");
const saveTransfer = document.querySelector("#save-item-wallet");

const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

let headers = {};
headers[header] = token;

function getUsersFromSelect(accountOptions) {
    let users = new Set();
    //console.log(accountOptions);
    Array.from(accountOptions.options).forEach(opt => users.add(opt.dataset.user));
    //console.log(users);
    let select = document.createElement("select");
    let defOption = new Option("Получатель", null, true, true);
    defOption.disabled = true;
    select.add(defOption);
    users.forEach(user => {
        if (user) select.options.add(new Option(user, user));
    });
    return select;
}


fetch("/transactions/getallwallets", {method: "POST", headers}).then(r => r.text()).then(data => {
    let div = document.createElement("div");
    div.innerHTML = data;
    let accountsSelect = div.querySelector("#_accountSelect");
    outAccount.innerHTML = div.querySelector("#_myAccountSelect").innerHTML;
    inAccount.innerHTML = accountsSelect.innerHTML;
    inUser.innerHTML = getUsersFromSelect(accountsSelect).innerHTML;
    filterInUserAccounts();
    editBtnActivate();
});

let editTransferBtns = document.querySelectorAll(".editTransfer");

function editBtnActivate(){
    for (let etb of editTransferBtns){
        etb.style.display = "";
        etb.addEventListener("click", function(){
            let some = Array.prototype.some;
            if (this.dataset.outsum && this.dataset.dateoper && this.dataset.outopid && this.dataset.inopid && some.call(outAccount.options, (opt,i)=>{
                if (opt.innerHTML == this.dataset.outname){
                    outAccount.selectedIndex =  i;
                    return true;
                }
            }) && some.call(inUser.options, (opt,i)=>{
                if (opt.innerHTML == this.dataset.inowner){
                    inUser.selectedIndex = i;
                    inUser.dispatchEvent(new Event("change"));
                    return true;
                }
            }) && some.call(inAccount.options, (opt, i)=>{
                if (opt.innerHTML == this.dataset.inname){
                    inAccount.selectedIndex = i;
                    return true;
                }
            })) {
                cancelChanges();
                inputSumWallet.value = this.dataset.outsum;
                document.querySelector("#theDate").value = this.dataset.dateoper;
                document.querySelector("#comments").value = this.dataset.comment;
                document.querySelector("#inOperationId").value = this.dataset.inopid;
                document.querySelector("#outOperationId").value = this.dataset.outopid;
                saveTransfer.style.display = "none";
                saveChangeBtn.style.display = "";
                cancelBtn.style.display = "";
                this.parentElement.parentElement.classList.add("alert", "alert-warning");
                document.querySelector('#outAccount').dispatchEvent(new Event("change"));
                return;
            }
            return alert("Этот перевод не может быть отредактирован");
        });
    }
}


function checkTransactionAvailable() {
    let checkresult = outAccount.options.length > 1
        && inAccount.options.length > 1
        && Number(inputSumWallet.value) > 0
        && Number(outAccount.value) > 0
        && theDate.value
        && Number(inAccount.value) > 0;

    if (outAccount.value == inAccount.value) {
        alert("Нельзя проводить операцию между одинаковыми счетами");
        return false;
    }
    if (Number(inputSumWallet.value) <= 0 || Number(inputSumWallet.value) > 999999999) {
        alert("Необходимо указать сумму в диапазоне (0...999999999]");
        return false;
    }
    if (!checkresult) alert("Необходимо заполнить все поля формы перевода");

    return checkresult;
}

function submitTransferForm(e) {
    e.preventDefault();
    if (checkTransactionAvailable()) {
        document.querySelector("#transactionForm").submit();
        saveTransfer.removeEventListener("click", submitTransferForm);
    }
}

saveTransfer.addEventListener("click", submitTransferForm);

function filterInUserAccounts() {
    let val = inUser.value;
    Array.from(inAccount.options).forEach((option, i) => {
        if (!i) return;
        if (val == option.dataset.user) {
            option.style.display = "";
            return;
        }
        option.style.display = "none";
    });
    inAccount.options[0].selected = true;
}

inUser.addEventListener("change", filterInUserAccounts);
theDate.value = new Date().toISOString().split("T")[0];