<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Modal -->
<form:form name="deltransa" action="/transactions/remove" method="post">
    <div class="modal fade" id="deleteTransactionDialogModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Удаление перевода</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Перевод будет удален!
                    Продолжить?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">Удалить</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="" id="outopid" name="outOperationId">
    <input type="hidden" value="" id="inopid" name="inOperationId">
</form:form>
