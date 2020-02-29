<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<span id="u">
    <a class="nav-item nav-link mr-md-2" href="#" id="nav-account" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <c:if test="${findallaccountbyuser.size() == 0}">
            «Не заполнен раздел счета!!!»
        </c:if>
        <c:forEach var="account" items="${findallaccountbyuser}" end="0">
            ${account.name}: ${account.amount} руб.
        </c:forEach>
    </a>
</span>
<div class="dropdown-menu dropdown-menu-md-right" aria-labelledby="nav-account" id="accountbyusermain">
    <c:forEach var="account" items="${findallaccountbyuser}">
        <a class="dropdown-item" href="#" id="h${account.accountid}">${account.name}: ${account.amount} руб.</a>
    </c:forEach>
</div>