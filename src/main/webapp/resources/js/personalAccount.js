var setAccount = document.querySelectorAll(".set-control");
var setFamily = document.querySelectorAll(".set-control-family");
var setEditionFamily = document.querySelector("#edition-family");
var setItemFamily = document.querySelector("#save-item-family")
var setEditionNewFamily = document.querySelector("#edition-new-family");
var setItemNewFamily = document.querySelector("#save-item-new-family")

for (let input of setAccount) {
    input.disabled = true;
}
for (let input of setFamily) {
    input.disabled = true;
}

document.querySelector("#edition-account").addEventListener("click", e => {
    e.preventDefault();
    document.querySelector("#edition-account").style.display = "none";
    document.querySelector("#save-item-account").style.display = "";
    for (let input of setAccount) {
        input.disabled = false;
    }
});

document.querySelector("#save-item-account").addEventListener("click", e => {
    e.preventDefault();
    document.querySelector("#setingForm").submit()
});

if (setEditionFamily)
    setEditionFamily.addEventListener("click", e => {
    e.preventDefault();
    document.querySelector("#edition-family").style.display = "none";
    document.querySelector("#save-item-family").style.display = "";
    document.querySelector("#nav-item-exit").style.display = "";
    for (let input of setFamily) {
        input.disabled = false;
    }
});
if(setItemFamily)
setItemFamily.addEventListener("click", e => {
    e.preventDefault();
    document.querySelector("#save-item-family").style.display = "none";
    document.querySelector("#edition-family").style.display = "";
    document.querySelector("#nav-item-exit").style.display = "none";
    for (let input of setFamily) {
        input.disabled = true;
    }
});
if (setEditionNewFamily)
setEditionNewFamily.addEventListener("click", e => {
    e.preventDefault();
    document.querySelector("#edition-new-family").style.display = "none";
    document.querySelector("#save-item-new-family").style.display = "";
    document.querySelector("#NewFamily").style.display = "";
});
if (setItemNewFamily)
setItemNewFamily.addEventListener("click", e => {
    e.preventDefault();
    document.querySelector("#save-item-new-family").style.display = "none";
    document.querySelector("#edition-new-family").style.display = "";
    document.querySelector("#NewFamily").style.display = "none";
});