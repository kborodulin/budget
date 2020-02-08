const link = document.querySelector("#importAccounts");
const outAccountSel = document.querySelector("#outAccount");
const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
let headers = {};
headers[header] = token;

fetch("/ref/allaccounttype").then(r=>r.text()).then(data=>{
    document.querySelector("#walletType").innerHTML = data;
});

fetch("/wallet/getallwallets", {method:"POST", headers}).then(r=>r.text()).then(data=>{
     let div = document.createElement("div");
     div.innerHTML = data;
     document.querySelector("#accountsList").innerHTML = div.querySelector("#_accountList").innerHTML;

     console.log(div.querySelector("#_accountSelect"));
     outAccountSel.innerHTML += div.querySelector("#_accountSelect").innerHTML;
});