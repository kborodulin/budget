<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="allcategoryperiod">
    <c:forEach var="category" items="${refallcategory}">
        <c:choose>
            <c:when test="${allcategoryperiod == category.categoryid}">
                <option selected value=<c:out value="${category.categoryid}"/>><c:out
                        value="${category.name}"/></option>
            </c:when>
            <c:otherwise>
                <option value=<c:out value="${category.categoryid}"/>><c:out value="${category.name}"/></option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select>