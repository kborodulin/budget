const outAccount = document.querySelector("#outAccount");
const inAccount = document.querySelector("#inAccount");
const saveTrans = document.querySelector("#save-item-wallet");

fetch("/ref/allaccounttype").then(r=>r.text()).then(data=>{
    document.querySelector("#walletType").innerHTML = data;
});

const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

let headers = {};
headers[header] = token;

fetch("/wallet/getallwallets", {method:"POST", headers}).then(r=>r.text()).then(data=>{
    let div = document.createElement("div");
    div.innerHTML = data;
    let accountsSelect = div.querySelector("#_accountSelect");
    document.querySelector("#accountsList").innerHTML = div.querySelector("#_accountList").innerHTML;
    outAccount.innerHTML = accountsSelect.innerHTML;
    inAccount.innerHTML = accountsSelect.innerHTML;

    checkTransactionAvailable();
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

inAccount.addEventListener("change", checkTransactionAvailable);
outAccount.addEventListener("change", checkTransactionAvailable);
