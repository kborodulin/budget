<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="accountbyuser">
    <c:forEach var="account" items="${findallaccountbyuser}">
        <option value=<c:out value="${account.accountid}"/>><c:out value="${account.name}"/></option>
    </c:forEach>
</select>