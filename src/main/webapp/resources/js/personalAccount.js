var setAccount = document.querySelectorAll(".set-control");
var setFamily = document.querySelectorAll(".set-control-family");
var setEditionFamily = document.querySelector("#edition-family");
var setItemFamily = document.querySelector("#save-item-family");
var setEditionNewFamily = document.querySelector("#edition-new-family");
var setItemNewFamily = document.querySelector("#save-item-new-family");
var setEdAccount = document.querySelector("#edition-account");
var setSettingForm = document.querySelector("#setingForm");
var setNavItemExit = document.querySelector("#nav-item-exit");
var setNewFamily = document.querySelector("#NewFamily");
var saveItemAccount = document.querySelector("#save-item-account");
var saveNewMember = document.querySelector("#item-add-family");
var setNewMember = document.querySelector("#addMemberForm")
;
for (let input of setAccount) {
    input.disabled = true;
}
for (let input of setFamily) {
    input.disabled = true;
}
if (setEdAccount)
    setEdAccount.addEventListener("click", e => {
        e.preventDefault();
        setEdAccount.style.display = "none";
        saveItemAccount.style.display = "";
        for (let input of setAccount) {
            input.disabled = false;
        }
    });
if (saveItemAccount)
    saveItemAccount.addEventListener("click", e => {
        e.preventDefault();
        setSettingForm.submit()
    });

if (setEditionFamily)
    setEditionFamily.addEventListener("click", e => {
        e.preventDefault();
        setEditionFamily.style.display = "none";
        setItemFamily.style.display = "";
        setNavItemExit.style.display = "";
        for (let input of setFamily) {
            input.disabled = false;
        }
    });
if (setItemFamily)
    setItemFamily.addEventListener("click", e => {
        e.preventDefault();
        setItemFamily.style.display = "none";
        setEditionFamily.style.display = "";
        setNavItemExit.style.display = "none";
        for (let input of setFamily) {
            input.disabled = true;
        }
    });
if (setEditionNewFamily)
    setEditionNewFamily.addEventListener("click", e => {
        e.preventDefault();
        setEditionNewFamily.style.display = "none";
        setItemNewFamily.style.display = "";
        setNewFamily.style.display = "";
    });
if (setItemNewFamily)
    setItemNewFamily.addEventListener("click", e => {
        e.preventDefault();
        document.querySelector("#setingForm2").submit();
    });

if (saveNewMember)
    saveNewMember.addEventListener("click", e => {
        e.preventDefault();
        setNewMember.submit()
    });