fetch("/ref/accountbyuser").then(r=>r.text()).then(data=>{
    document.querySelector("#accountbyuser").innerHTML = data;
});