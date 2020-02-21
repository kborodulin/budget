var setSaveExpense = document.querySelector("#save-expenses");
var setAddExpense = document.querySelector("#addExpense");


if (setSaveExpense)
    setSaveExpense.addEventListener("click", e => {
        e.preventDefault();
        if (inputSumExpense.value == "" || inputSumExpense.value == null) {
            alert("Заполните поле сумма!!!");
            return false;
        }
        if (inputSumExpense.value < 0) {
            alert("Введите положительную сумму!!!");
            return false;
        }
        if (inputSumExpense.value == 0) {
            alert("Введите сумму больше 0!!!");
            return false;
        }
        setAddExpense.submit();
    }, {once: true});
