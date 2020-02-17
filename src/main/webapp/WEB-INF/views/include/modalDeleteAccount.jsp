<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Modal -->
<form:form name="deletewallet" action="/wallet/remove" method="post">
    <div class="modal fade" id="deleteWalletDialogModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Удаление кошелька</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="delWalletMessage">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">Удалить</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="" id="delWalletId" name="accountid">
</form:form>
