// const link = document.querySelector("#importAccounts");
// const outAccountSel = document.querySelector("#outAccount");
//
// fetch("/ref/allaccounts").then(r=>r.text()).then(data=>{
//     let div = document.createElement("div");
//     div.innerHTML = data;
//     document.querySelector("#accountsList").innerHTML = div.querySelector("#_accountList").innerHTML;
//
//     console.log(div.querySelector("#_accountSelect"));
//     outAccountSel.innerHTML += div.querySelector("#_accountSelect").innerHTML;
// });
//

fetch("/ref/allaccounttype").then(r=>r.text()).then(data=>{
    document.querySelector("#walletType").innerHTML = data;
});