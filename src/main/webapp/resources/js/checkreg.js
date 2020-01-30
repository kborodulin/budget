var regbtn = document.querySelector("#buttonSignin");
var pswd = document.querySelector("#inputPasswordReg");
var pswdrpt = document.querySelector("#inputRepeatPassword");


function isFormValid() {
    return pswd.value && pswdrpt.value/* && pswd.value.length >= 8 && pswdrpt.value.length >= 8 */&& pswd.value == pswdrpt.value;
}

function passwordMatch() {
    pswd.style.backgroundColor = "#c6dbc69c";
    pswdrpt.style.backgroundColor = "#c6dbc69c";
}

function passwordMismatch() {
    pswd.style.backgroundColor = "#f94d3b82";
    pswdrpt.style.backgroundColor = "#f94d3b82";
}

function passwordCheck() {
    pswd.value == pswdrpt.value ? passwordMatch() : passwordMismatch();
}

regbtn.addEventListener("click", e => {
    if (!isFormValid()) {
        e.preventDefault();
    }
});

pswd.addEventListener("input", passwordCheck);
pswdrpt.addEventListener("input", passwordCheck);