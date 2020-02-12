var setSaveIncome = document.querySelector("#save-income");
var setAddIncome = document.querySelector("#addincome");


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
        setAddIncome.submit();
    });
