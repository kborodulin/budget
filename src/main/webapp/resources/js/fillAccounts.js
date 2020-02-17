const outAccount = document.querySelector("#outAccount");
const inAccount = document.querySelector("#inAccount");
const saveTrans = document.querySelector("#save-item-wallet");
const inUser = document.querySelector("#inUser");
const inputSumWallet = document.querySelector("#inputSumWallet");
const comments = document.querySelector("#comments");

fetch("/ref/allaccounttype").then(r => r.text()).then(data => {
    document.querySelector("#walletType").innerHTML = data;
});

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

function removeWalletDialog() {
    document.querySelector("#delWalletId").value = this.dataset.walletid;
    document.querySelector("#delWalletMessage").innerHTML = this.dataset.amount==0?
        `Кошелек "${this.dataset.walletname}" будет удален. Он более не будет отображаться в списке кошельков`:
        `Кошелек "${this.dataset.walletname}" не пуст. Кошелек будет отображаться в списке как неактивный. На него нельзя будет переводить деньги. После обнуления баланса кошелек будет удален.`;
    $("#deleteWalletDialogModal").modal();
}

function recoverWalletDialog() {
    document.querySelector("#recoverWalletId").value = this.dataset.walletid;
    document.querySelector("#recoverWalletMessage").innerHTML = `Кошелек "${this.dataset.walletname}" будет восстановлен.`;
    $("#recoverWalletDialogModal").modal();
}

function addListeners() {
    let removeWalletBtns = document.querySelectorAll(".removeWallet");
    for (let rwb of removeWalletBtns) rwb.addEventListener("click", removeWalletDialog);
    let recoverWalletBtns = document.querySelectorAll(".recoverWallet");
    for (let rcw of recoverWalletBtns) rcw.addEventListener("click", recoverWalletDialog);

}

fetch("/wallet/getallwallets", {method: "POST", headers}).then(r => r.text()).then(data => {
    let div = document.createElement("div");
    div.innerHTML = data;
    let accountsSelect = div.querySelector("#_accountSelect");
    let recAccountlist = div.querySelector("#_accountList");
    document.querySelector("#accountsList").innerHTML = recAccountlist.innerHTML;
    outAccount.innerHTML = div.querySelector("#_myAccountSelect").innerHTML;
    inAccount.innerHTML = accountsSelect.innerHTML;
    inUser.innerHTML = getUsersFromSelect(accountsSelect).innerHTML;
    checkTransactionAvailable();
    filterInUserAccounts();
    addListeners();
});

function toggleSaveTransBtn(disable) {
    saveTrans.style.display = disable ? "none" : "";
}

function toggleTransactionForm(disable, disableSaveBtn = disable) {
    inputSumWallet.disabled = disable;
    inAccount.disabled = disable;
    outAccount.disabled = disable;
    comments.disabled = disable;
    toggleSaveTransBtn(disableSaveBtn);
}

function checkTransactionAvailable() {
    if (outAccount.options.length <= 1 && inAccount.options.length <= 1) {
        return toggleTransactionForm(true);
    }
    toggleTransactionForm(false, !comments.value || !inputSumWallet.value || inAccount.value==0 || outAccount.value==0 || inAccount.value == outAccount.value || inAccount.options.length <= 1 || outAccount.options.length <= 1);
}

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

inAccount.addEventListener("change", checkTransactionAvailable);
outAccount.addEventListener("change", checkTransactionAvailable);
inUser.addEventListener("change", filterInUserAccounts);
inputSumWallet.addEventListener("input", checkTransactionAvailable);
comments.addEventListener("input", checkTransactionAvailable);