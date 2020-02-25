var getOperations = document.querySelector("#getOperations");
var operationTable = document.querySelector("#operationTable");

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
dC1.onchange = function(e){
    for(var i = 0; i<qwe.length; i++){
        if(dC1.checked){
            qwe[i].checked = true;
        }else{
            qwe[i].checked = false;
        }
    }
}

var dC3 = document.getElementById('defaultCheck3');
var qwe3 = document.getElementsByClassName('qwe3');
dC3.onchange = function(e){
    for(var i = 0; i<qwe3.length; i++){
        if(dC3.checked){
            qwe3[i].checked = true;
        }else{
            qwe3[i].checked = false;
        }
    }
}