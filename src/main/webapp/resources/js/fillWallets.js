function addListeners() {
    let removeWalletBtns = document.querySelectorAll(".removeWallet");
    for (let rwb of removeWalletBtns) rwb.addEventListener("click", removeWalletDialog);
    let recoverWalletBtns = document.querySelectorAll(".recoverWallet");
    for (let rcw of recoverWalletBtns) rcw.addEventListener("click", recoverWalletDialog);
}

function removeWalletDialog() {
    document.querySelector("#delWalletId").value = this.dataset.walletid;
    document.querySelector("#delWalletMessage").innerHTML = this.dataset.amount==0?
        `Кошелек "${this.dataset.walletname}" будет удален. Он более не будет отображаться в списке кошельков`:
        `Кошелек "${this.dataset.walletname}" не пуст. Кошелек будет отображаться в списке как неактивный. На него нельзя будет переводить деньги. После обнуления баланса кошелек будет удален.`;
    $("#deleteWalletDialogModal").modal();
}

function recoverWalletDialog() {
    document.querySelector("#recoverWalletId").value = this.dataset.walletid;
    document.querySelector("#recoverWalletMessage").innerHTML = `Кошелек "${this.dataset.walletname}" будет восстановлен.`;
    $("#recoverWalletDialogModal").modal();
}

fetch("/ref/allaccounttype").then(r => r.text()).then(data => {
    document.querySelector("#walletType").innerHTML = data;
});

addListeners();