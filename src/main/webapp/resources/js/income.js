var setSaveIncome = document.querySelector("#save-income");
var setAddIncome = document.querySelector("#addincome");
var setSend = 0;


if (setSaveIncome)
    setSaveIncome.addEventListener("click", e => {
        e.preventDefault();
        if (inputSumIncome.value == "" || inputSumIncome.value == null) {
            alert("Заполните поле сумма!!!");
            return false;
        }
        if (inputSumIncome.value < 0) {
            alert("Введите положительную сумму!!!");
            return false;
        }
        if (inputSumIncome.value == 0) {
            alert("Введите сумму больше 0!!!");
            return false;
        }
        if (theDate.value < '1899.12.31!!!') {
            alert("Введите год равным или больше 1900!!!");
            return false;
        }
        if (setSend == 0) {
            setSend = 1;
            setAddIncome.submit();
        }
    });
