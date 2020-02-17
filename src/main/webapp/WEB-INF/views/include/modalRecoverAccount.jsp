<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<form:form name="recoverwallet" action="/wallet/recover" method="post">
    <div class="modal fade" id="recoverWalletDialogModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Восстановить кошелек</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="recoverWalletMessage">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Восстановить</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="" id="recoverWalletId" name="accountid">
</form:form>
