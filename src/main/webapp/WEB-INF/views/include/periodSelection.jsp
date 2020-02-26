<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<body>
<div class="input-group">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1">Период</span>
    </div>
    <select class="custom-select form-control" name="dateRange" id="period" aria-describedby="basic-addon1"
            onchange="this.form.submit()">
        <option value="1" ${periodselected == 1 ? "selected" : ""}>день</option>
        <option value="2" ${periodselected == 2 ? "selected" : ""}>неделя</option>
        <option value="3" ${periodselected == 3 ? "selected" : ""}>месяц</option>
        <option value="4" ${periodselected == 4 ? "selected" : ""}>год</option>
        <option value="5" ${periodselected == 5 ? "selected" : ""}>весь</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon2">Категория</span>
    </div>
    <select class="form-control" id="allcategoryperiod" name="categoryperiod" onchange="this.form.submit()"></select>
</div>
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
</body>
</html>