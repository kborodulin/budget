<%@ page contentType="text/html;charset=UTF-8"%>
<form:form method="post" class="form-signup" id="setingForm" name="userEdFamily" >
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h3 class="h3">Новая семья
    </h3>
    <a class="d-flex align-items-center text-muted" href="#" style= "display:none">
        <span id="edition-new-family" style="text-decoration: underline; display:inherit;color: #007bff">создать семью&nbsp;</span>
        <span id="save-item-new-family" style="text-decoration: underline; display:none;color: red;">сохранить семью&nbsp;</span>
    </a>
</div>
    <div>
    <div style="white-space: nowrap;" class="col-md-6 mb-3">
    <input type="text" class="form-control" id="NewFamily" placeholder="введите имя семьи" value="" required="" style= "display:none">
    </div>
</div>
</form:form>