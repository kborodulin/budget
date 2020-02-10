<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="allcategory">
    <c:forEach var="category" items="${refallcategory}">
        <option value=<c:out value="${category.categoryid}"/>><c:out value="${category.name}"/></option>
    </c:forEach>
</select>