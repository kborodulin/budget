<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Modal -->
<form:form name="editwallet" action="/wallet/edit" method="post">
    <div class="modal fade bd-example-modal-sm" id="editWalletDialogModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Редактирование счета</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="alert alert-danger" role="alert" id="editingError" style="display: none"></div>
                    <div class="form-group">
                        <label for="walletEditName" class="col-form-label">Название</label><br>
                        <input type="text" class="form-control" id="walletEditName" name="name" maxlength=20 required>
                    </div>
                    <div class="form-group">
                        <label for="walletEditBalance" class="col-form-label">Баланс</label><br>
                        <input type="number" class="form-control" name="amount" max="9999999" min="0" value="0" id="walletEditBalance">
                    </div>
                    <div class="form-group">
                        <label for="walletEditType" class="col-form-label">Тип счета</label><br>
                        <select class="form-control" id="walletEditType" name="acctypeid"></select>
                    </div>
                    <input type="hidden" name="accountid" value="" id="accountEditId">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-info">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>
</form:form>