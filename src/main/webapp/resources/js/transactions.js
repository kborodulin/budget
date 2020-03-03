let delTransferBtns = document.querySelectorAll(".removeTransfer");
let cancelBtn = document.querySelector("#cancel-changes-wallet");
let saveChangeBtn = document.querySelector("#save-changes-wallet");

for (let dtb of delTransferBtns){
    dtb.addEventListener("click", function(){
        document.querySelector("#outopid").value = this.dataset.outopid;
        document.querySelector("#inopid").value = this.dataset.inopid;
        $("#deleteTransactionDialogModal").modal();
    });
}


function cancelChanges(clearOld){
    cancelBtn.style.display="none";
    saveChangeBtn.style.display = "none";
    saveTransfer.style.display = "";
    let alert = document.querySelector(".alert.alert-warning");
    if (alert) alert.classList.remove("alert", "alert-warning");
    document.querySelector("#inOperationId").value = "";
    document.querySelector("#outOperationId").value = "";
    if (clearOld){
        theDate.value = new Date().toISOString().split("T")[0];
        inUser.selectedIndex = 0;
        inAccount.value = "0";
        document.querySelector("#comments").value = "";
        inputSumWallet.value = "";
        outAccount.value = "0";
    }
}



cancelBtn.addEventListener("click", ()=>{
    cancelChanges(true);
});

function saveChanges(){
    if (checkTransactionAvailable()){
        document.querySelector("#transactionForm").submit();
        saveChangeBtn.removeEventListener("click", saveChanges);
    }transactions
}

saveChangeBtn.addEventListener("click", saveChanges);

document.querySelector('#outAccount').addEventListener('change', function(){
    document.querySelector('#nav-account').text = document.getElementById('h' + this.value).text;
})