var setSaveIncome = document.querySelector("#save-income");
var setAddIncome = document.querySelector("#addincome");


if (setSaveIncome)
    setSaveIncome.addEventListener("click", e => {
        e.preventDefault();
        setAddIncome.submit();
    });
