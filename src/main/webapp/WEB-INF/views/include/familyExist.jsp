<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" class="form-signup" id="setingForm2" name="familyInfo" action="account/savefamily">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h3 class="h3">Данные семьи
            <input type="text" class="form-control set-control-family" id="FamilyName" placeholder="Family" name="name"
                   value="${family.name}" required="">
        </h3>
        <a class="d-flex align-items-center text-muted" href="#" style="display:none">
            <span id="edition-family" style="text-decoration: underline; display:inherit; color: #007bff;">редактирование&nbsp;</span>
            <span id="save-item-family" style="text-decoration: underline; display:none; color: red">сохранить изменения&nbsp;</span>
        </a>
    </div>
</form:form>
<form:form method="post" class="form-signup" id="addMemberForm" name="familyInfoMember" action="account/addmember">
    <div style="white-space: nowrap;" class="col-md-6 mb-3">
        <input type="email" class="form-control" id="EmailAdd" placeholder="Email" value="" name="email"
               required="">
        <c:if test="${result.hasErrors()}">
                                <span style="color: #ff0000;">
                                    <c:forEach var="err" items="${result.getAllErrors()}">
                                        <c:out value="${err.getDefaultMessage()}"/> <br>
                                    </c:forEach>
                                </span>
        </c:if>
        <c:if test="${success=='success'}">
            <span style="color: #38ff4d;"> Приглашение успешно отправлено </span>
        </c:if>
        <a class="d-flex align-items-center text-muted" href="#" style="display:none">
            <span id="item-add-family" style="text-decoration: underline; color: #007bff;">&nbsp;отправить приглашение&nbsp;</span>
        </a>
    </div>
</form:form>
<div style="white-space: nowrap;" class="col-md-6 mb-3">
    <input type="text" class="form-control " id="LoginFamily" placeholder="Логин" value="" required="" disabled>
    <input type="email" class="form-control" id="EmailFamily" placeholder="Email" value="" required="" disabled>
    <input type="text" class="form-control set-control-family" id="FamilyNick" placeholder="имя" value="" required="">
</div>
<div style="white-space: nowrap;" class="col-md-6 mb-3">
    <input type="text" class="form-control " id="LoginFamily2" placeholder="Логин" value="" required="" disabled>
    <input type="email" class="form-control" id="EmailFamily2" placeholder="Email" value="" required="" disabled>
    <input type="text" class="form-control set-control-family" id="FamilyNick2" placeholder="имя" value="" required="">
</div>
<a class="nav-link" id="nav-item-exit" href="${pageContext.request.contextPath}/leftfamily" style="color: red; display:none;" onclick="return confirmDelete();">
    <span data-feather="power"></span>
    <span style="text-decoration: underline;">выйти из семьи</span>
</a>

