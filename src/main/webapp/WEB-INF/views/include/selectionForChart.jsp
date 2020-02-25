<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<body>

<div>Члены семьи</div>
<c:forEach items="${familyMembers}" var="familyMember">
<div class="form-check">
    <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
    <label class="form-check-label" for="defaultCheck1">
        <c:out value="${familyMember.user.login}"/>
    </label>
</div>
</c:forEach>

<div>Категории</div>
<c:forEach items="${categoryList}" var="category">
    <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="defaultCheck2">
        <label class="form-check-label" for="defaultCheck2">
            <c:out value="${category.name}"/>
        </label>
    </div>
</c:forEach>

<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/personalAccount.js"></script>
<script src="../resources/js/refCategory.js"></script>
<script src="../resources/js/refCategoryPeriod.js"></script>
<script src="../resources/js/utils.js"></script>
<script src="../resources/js/refAccountByUser.js"></script>
<script src="../resources/js/income.js"></script>
</body>
</html>