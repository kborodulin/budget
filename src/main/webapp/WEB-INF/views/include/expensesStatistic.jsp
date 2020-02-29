<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" name="filterStatistic" id="filterStatistic" action="/statistic">
    <tbody>
    <tr>
        <td><input class="form-check-input" type="radio" name="operationType" id="inlineRadio1"
                   value="2" style="display: none" checked></td>
    </tr>
    <tr>
        <td rowspan="2" style="width: 75%;">
            <div style="width:100%;">
                <canvas id="canvas"></canvas>
            </div>
        </td>
        <td colspan="2">
            <div class="form-group" data-toggle="tooltip" data-placement="top"
                 title="Период может быть от 1 недели до 1 года">
                <div style="color: #007bff">Период</div>
                <div class="form-group mx-sm-1">
                    <label for="startDate">с&ensp;</label>
                    <input type="date" class="form-control" class="mydate" name="startDate"
                           id="startDate"
                           value="${filterStatistic.startDate}"
                           placeholder="Дата">
                </div>
                <div class="form-group mx-sm-1">
                    <label for="endDate">по</label>
                    <input type="date" class="form-control" class="mydate" name="endDate"
                           id="endDate"
                           value="${filterStatistic.endDate}"
                           placeholder="Дата">
                </div>
                <c:if test="${result.hasErrors()}">
                    <div style="color: #ff0000;">
                        <c:forEach var="err" items="${result.getAllErrors()}">
                            <c:out value="${err.getDefaultMessage()}"/> <br>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div style="color: #007bff">Члены семьи</div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="all" id="defaultCheck1"
                       name="flagAllFamilyMembers"
                <c:if test="${filterStatistic.flagAllFamilyMembers=='all'}"> checked </c:if>
                >
                <label class="form-check-label" for="defaultCheck1">
                    Все
                </label>
            </div>
            <c:forEach items="${familyMembers}" var="familyMember">
                <div class="form-check">
                    <input class="form-check-input qwe" type="checkbox"
                           value=" <c:out value="${familyMember.famemid}"/>" id="defaultCheck2" name="familyMembers"
                    <c:if test="${filterStatistic.familyMembers.contains(familyMember.famemid)}"> checked </c:if>
                    >
                    <label class="form-check-label" for="defaultCheck2">
                        <c:out value="${familyMember.user.login}"/>
                    </label>
                </div>
            </c:forEach>
        </td>
        <td>
            <div style="color: #007bff">Категории</div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="all" name="flagAllCategories"
                       id="defaultCheck3"
                <c:if test="${filterStatistic.flagAllCategories=='all'}"> checked </c:if>
                >
                <label class="form-check-label" for="defaultCheck3">
                    Все
                </label>
            </div>
            <c:forEach items="${categoryList}" var="category">
                <div class="form-check">
                    <input class="form-check-input qwe3" type="checkbox"
                           value="<c:out value="${category.categoryid}"/>" id="defaultCheck4"
                           name="categoryList"
                    <c:if test="${filterStatistic.categoryList.contains(category.categoryid)}"> checked </c:if>
                    >
                    <label class="form-check-label" for="defaultCheck4">
                        <c:out value="${category.name}"/>
                    </label>
                </div>
            </c:forEach>
        </td>
    </tr>
    </tbody>
</form:form>
