

$("#addNewInvoice").on("click", ()=>{
    $('#newWalletDialog').modal();
});

$("#save-item-wallet").on("click",e=>{
   e.preventDefault();
   document.querySelector("#transactionForm").submit();
});