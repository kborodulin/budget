const outAccount = document.querySelector("#outAccount");
const inAccount = document.querySelector("#inAccount");
const saveTrans = document.querySelector("#save-item-wallet");
const inUser =  document.querySelector("#inUser");

fetch("/ref/allaccounttype").then(r=>r.text()).then(data=>{
    document.querySelector("#walletType").innerHTML = data;
});

const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

let headers = {};
headers[header] = token;

function getUsersFromSelect(accountOptions){
    let users = new Set();
    console.log(accountOptions);
    Array.from(accountOptions.options).forEach(opt=>users.add(opt.dataset.user));
    console.log(users);
    let select = document.createElement("select");
    users.forEach(user=>select.options.add(new Option(user,user)));
    return select;
}

fetch("/wallet/getallwallets", {method:"POST", headers}).then(r=>r.text()).then(data=>{
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
});

function toggleSaveTransBtn(disable){
    saveTrans.style.display = disable?"none":"";
}

function toggleTransactionForm(disable){
    document.querySelector("#inputSumWallet").disabled = disable;
    inAccount.disabled = disable;
    outAccount.disabled = disable;
    document.querySelector("#comments").disabled = disable;
    toggleSaveTransBtn(disable);
}

function checkTransactionAvailable(){
    if (inAccount.options.length <=1 && outAccount.options.length<=1) {
        return toggleTransactionForm(true);
    }
    toggleTransactionForm(false);
    if (inAccount.value === outAccount.value) toggleSaveTransBtn(true);
}

function filterInUserAccounts(){
    let val = inUser.value;
    let found = false;
    Array.from(inAccount.options).forEach(option=>{
        if (val==option.dataset.user) {
            option.style.display = "";
            if (!found) {
                option.selected = true;
                checkTransactionAvailable();
                found = true;
            }
            return;
        }
        option.style.display = "none";
    });
}

inAccount.addEventListener("change", checkTransactionAvailable);
outAccount.addEventListener("change", checkTransactionAvailable);
inUser.addEventListener("change", filterInUserAccounts);

