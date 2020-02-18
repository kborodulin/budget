fetch("/ref/allcategoryperiod").then(r=>r.text()).then(data=>{
    document.querySelector("#allcategoryperiod").innerHTML = data;
});