<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="walletType">
    <c:forEach var="type" items="${refallaccounttype}">
        <option value=<c:out value="${type.accounttypeid}"/>><c:out value="${type.name}"/></option>
    </c:forEach>
</select>