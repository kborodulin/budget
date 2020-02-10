fetch("/ref/allcategory").then(r=>r.text()).then(data=>{
    document.querySelector("#allcategory").innerHTML = data;
});