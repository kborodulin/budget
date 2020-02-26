

$("#addNewInvoice").on("click", ()=>{
    $('#newWalletDialog').modal();
});

const editWalletBtns = document.querySelectorAll(".editWallet");
for (let ewb of editWalletBtns){
    ewb.addEventListener("click",function(){
        $("#editWalletDialogModal").modal();
        document.querySelector("#walletEditName").value = this.dataset.accountname;
        document.querySelector("#accountEditId").value = this.dataset.accountid;
        document.querySelector("#walletEditType").value = this.dataset.accounttypeid;
        document.querySelector("#walletEditBalance").value = this.dataset.amount;
    });
}