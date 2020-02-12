var setSaveExpense = document.querySelector("#save-expenses");
var setAddExpense = document.querySelector("#addIncome");


if (setSaveExpense)
    setSaveExpense.addEventListener("click", e => {
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
        setAddExpense.submit();
    });
