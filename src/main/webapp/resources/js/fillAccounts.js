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
    document.querySelector("#accountsList").innerHTML = div.querySelector("#_accountList").innerHTML;
    document.querySelector("#outAccount").innerHTML = div.querySelector("#_accountSelect").innerHTML;
});