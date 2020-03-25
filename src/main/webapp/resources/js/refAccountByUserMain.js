fetch("/ref/accountbyusermain").then(r=>r.text()).then(data=>{
    document.querySelector("#accountbyusermain").innerHTML = data;
});