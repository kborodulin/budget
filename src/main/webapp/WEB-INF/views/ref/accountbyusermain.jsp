<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<span id="u">
    <a class="nav-item nav-link mr-md-2" href="#" id="nav-account" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <c:if test="${findallaccountbyuser.size() == 0}">
            «Не заполнен раздел счета!!!»
        </c:if>
        <c:if test="${findallaccountbyusersortfilter == null}">
            <c:forEach var="account" items="${findallaccountbyuser}" end="0">
                ${account.name}: ${account.amount} руб.
            </c:forEach>
        </c:if>
        <c:if test="${findallaccountbyusersortfilter != null}">
            ${findallaccountbyusersortfilter}
        </c:if>
    </a>
</span>
<div class="dropdown-menu dropdown-menu-md-right" aria-labelledby="nav-account" id="accountbyusermain">
    <c:forEach var="account" items="${findallaccountbyuser}">
        <a class="dropdown-item" href="#" id="h${account.accountid}">${account.name}: ${account.amount} руб.</a>
    </c:forEach>
    <c:forEach var="account" items="${findallaccountbyusersortid}">
        <a class="dropdown-item" href="#" id="h${account.accountid}">${account.name}: ${account.amount} руб.</a>
    </c:forEach>
</div>