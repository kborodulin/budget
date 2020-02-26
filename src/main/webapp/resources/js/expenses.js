var setSaveExpense = document.querySelector("#save-expenses");
var setAddExpense = document.querySelector("#addExpense");
var setSend = 0;


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
        try {
            if (expdate.value < '1899.12.31') {
                alert("Введите год равным или больше 1900!!!");
                return false;
            }
        } catch (e) {}
        try {
            if (accountexpbyuser.value == '') {
                alert("Проверьте наличие созданного счета в разделе счета у данного пользователя!!!");
                return false;
            }
        } catch (e) {}
        if (setSend == 0) {
            setSend = 1;
            setAddExpense.submit();
        }
    });