<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>

<ul id="_accountList">
    <c:forEach var="account" items="${accounts}">
        <c:if test="${account.isclosesign<1||account.amount>0||account.amount<0}">
            <li class="nav-item d-flex justify-content-between align-items-center px-3 mt-1" data-accountid=<c:out value="${account.accountid}"/> >
                <a class="nav-link <c:choose><c:when test="${account.isclosesign>0}">text-muted</c:when><c:when test="${account.amount>0}">text-success</c:when><c:when test="${account.amount<0}">text-danger</c:when></c:choose>" href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card"><rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect><line x1="1" y1="10" x2="23" y2="10"></line></svg>
                    <c:out value="${account.name} >> ${account.amount}₽"/>
                </a>
                <c:choose>
                    <c:when test="${account.isclosesign<1&&account.famem.famemid.equals(myfamem.famemid)}">
                        <a class="removeWallet" href="#" data-walletid="${account.accountid}" data-walletname="${account.name}" data-amount="${account.amount}" data-closed="${account.isclosesign}">
                            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather text-danger"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                        </a>
                    </c:when>
                    <c:when test="${account.famem.famemid.equals(myfamem.famemid)}">
                        <a class="recoverWallet" href="#" data-walletid="${account.accountid}" data-walletname="${account.name}" data-amount="${account.amount}" data-closed="${account.isclosesign}">
                            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather text-muted"><polyline points="1 4 1 10 7 10"></polyline><path d="M3.51 15a9 9 0 1 0 2.13-9.36L1 10"></path></svg>
                        </a>
                    </c:when>
                </c:choose>
            </li>
        </c:if>
    </c:forEach>
</ul>
<select id="_myAccountSelect">
    <option disabled selected value="0">Счет отправителя</option>
    <c:forEach var="account" items="${accounts}">
        <c:if test="${myfamem.famemid.equals(account.famem.famemid)&&(account.isclosesign<1||account.amount>0)}">
            <option value=<c:out value="${account.accountid}"/>><c:out value="${account.name}"/></option>
        </c:if>
    </c:forEach>
</select>
<select id="_accountSelect">
    <option disabled selected value="0">Счет получателя</option>
    <c:forEach var="account" items="${accounts}">
        <c:if test="${account.isclosesign<1||account.amount<0}">
            <option value=<c:out value="${account.accountid}"/> data-user=<c:out value="${users.stream().filter(u->u.getUserid().equals(famems.stream().filter(f->f.getFamemid().equals(account.famem.famemid)).findFirst().get().user.userid)).findFirst().get().login}"/>><c:out value="${account.name}"/></option>
        </c:if>
    </c:forEach>
</select>
