<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form:form method="post" class="form-signup" id="updateFamilyName" name="familyUpdateInfo" action="account/updateFamily">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h3 class="h3">Данные семьи
            <input type="text" class="form-control set-control-family" id="FamilyUpdatedName" name="name"
                   value="${family.name}" required="">
        </h3>
        <a class="d-flex align-items-center text-muted" href="#" style="display:none">
            <span id="edition-family" style="text-decoration: underline; display:inherit; color: #007bff;">редактировать&nbsp;</span>
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
            <span id="item-add-family" style="text-decoration: underline; color: #007bff;">отправить приглашение&nbsp;</span>
        </a>
    </div>
</form:form>
<c:if test="${fn:length(sendAlert) gt 0}">
    <h6 class="h6">Приглашения
    </h6>
    <c:forEach var="alert" items="${sendAlert}">
            <div style="white-space: nowrap;" class="col-md-6 mb-3">
                <input type="text" class="form-control " id="alertReceiverName" placeholder="" value="${alert.receiver.name}" required="" disabled>
                <input type="text" class="form-control " id="alertReceiverSurname" placeholder="" value="${alert.receiver.surname}" required="" disabled>
                <input type="text" style="display: none" id="alertId" placeholder="" value="${alert.alertid}" name="alertid" required="" disabled>
            </div>
    </c:forEach>
</c:if>
<c:if test="${fn:length(membersList) gt 1}">
    <h6 class="h6">Члены семьи
    </h6>
    <c:forEach var="member" items="${membersList}">
        <c:if test="${member.user.userid != user.userid}">
        <div style="white-space: nowrap;" class="col-md-6 mb-3">
            <input type="text" class="form-control " id="LoginFamily" placeholder="" value="${member.name}" required="" disabled>
            <input type="email" class="form-control" id="EmailFamily" placeholder="" value="${member.surname}" required="" disabled>
        </div>
        </c:if>
    </c:forEach>
</c:if>
<a class="nav-link" id="nav-item-exit" href="${pageContext.request.contextPath}/leftfamily" style="color: red; display:none;" onclick="return confirmDelete();">
    <span data-feather="power"></span>
    <span style="text-decoration: underline;">выйти из семьи</span>
</a>

