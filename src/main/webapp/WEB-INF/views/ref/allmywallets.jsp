<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page trimDirectiveWhitespaces="true" %>--%>

<ul id="_accountList">
    <c:forEach var="account" items="${accounts}">
        <li class="nav-item" data-accountid=<c:out value="${account.accountid}"/> >
            <a class="nav-link" href="#" style="color: green">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card"><rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect><line x1="1" y1="10" x2="23" y2="10"></line></svg>
                <c:out value="${account.name} (${types.stream().filter(t->t.getAccounttypeid().equals(account.accounttype.accounttypeid)).findFirst().get().getName()})"/>
            </a>
        </li>
    </c:forEach>
</ul>
<select id="_accountSelect">
    <c:forEach var="account" items="${accounts}">
        <option value=<c:out value="${account.accountid}"/>><c:out value="${account.name}"/></option>
    </c:forEach>
</select>
