var getOperations = document.querySelector("#getOperations");
var operationTable = document.querySelector("#operationTable");
var getStatistic = document.querySelector("#getStatistic");
var formFilterStatistic = document.querySelector("#filterStatistic");

if (getOperations)
    getOperations.addEventListener("click", e => {
        e.preventDefault();
        if (operationTable.style.display === "none"){
            operationTable.style.display = "";
        }
        else {
            operationTable.style.display = "none";
        }
    });

var dC1 = document.getElementById('defaultCheck1');
var qwe = document.getElementsByClassName('qwe');
var flag = 1;
dC1.onchange = function (e) {
    for (var i = 0; i < qwe.length; i++) {
        if (dC1.checked) {
            qwe[i].checked = true;
            flag = 1;
        } else {
            qwe[i].checked = false;
        }
    }
}

for (var j = 0; j < qwe.length; j++) {
    qwe[j].onchange = function () {
        if (flag === 1 && dC1.checked) {
            dC1.checked = false;
            flag = 0;
        }
    }
}

var dC3 = document.getElementById('defaultCheck3');
var qwe3 = document.getElementsByClassName('qwe3');
var flag3 = 1;
dC3.onchange = function (e) {
    for (var i = 0; i < qwe3.length; i++) {
        if (dC3.checked) {
            qwe3[i].checked = true;
            flag3 = 1;
        } else {
            qwe3[i].checked = false;
        }
    }
}

for (var i = 0; i < qwe3.length; i++) {
    qwe3[i].onchange = function () {
        if (flag3 === 1 && dC3.checked) {
            dC3.checked = false;
            flag3 = 0;
        }
    }
}

if (getStatistic)
    getStatistic.addEventListener("click", e => {
        e.preventDefault();
        formFilterStatistic.submit();
    });