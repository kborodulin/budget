<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<form:form name="createNewAccountForm" action="/wallet/create" method="post">
    <div class="modal fade bd-example-modal-sm" id="newWalletDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Новый счет</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                </div>
                <div class="modal-body">
                    <div class="alert alert-danger" role="alert" id="creationError" style="display: none"></div>
                    <div class="form-group">
                        <label for="walletName" class="col-form-label">Название</label><br>
                        <input type="text" class="form-control" id="walletName" name="name" maxlength=20 required>
                    </div>
                    <div class="form-group">
                        <label for="walletBalance" class="col-form-label">Баланс</label><br>
                        <input type="number" class="form-control" name="amount" max="9999999" min="0" value="0" id="walletBalance">
                    </div>
                    <div class="form-group">
                        <label for="walletType" class="col-form-label">Тип счета</label><br>
                        <select class="form-control" id="walletType" name="acctypeid"></select>
                    </div>
                    <input type="hidden" name="dateopen" value="">
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="saveNewAccount">Сохранить</button>
                </div>
            </div>
        </div>
    </div>
</form:form>
<script>
    let errdiv = document.querySelector("#creationError");
    let balance = document.querySelector("#walletBalance");
    function errorAlert(msg){
        errdiv.style.display = "";
        errdiv.innerHTML = msg;
    }
    document.querySelector("#saveNewAccount").addEventListener("click", function(){
        if (this.disabled) return;
        if (!document.querySelector("#walletName").value) return errorAlert("Укажите название счета");
        if (balance.value<0 || balance.value>9999999) return errorAlert("Баланс должен быть в диапазоне [0...9999999]");
        this.disabled = true;
        this.form.submit();
    });
</script>