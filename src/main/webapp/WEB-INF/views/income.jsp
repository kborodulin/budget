<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Доход - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">

    <style>
        #nav-item-income {
            color: #007bff;
        }

        .sidebar .nav-link .feather {
            color: inherit !important;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .form-control {
            display: inline-block;
            width: auto;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="../resources/css/dashboard.css" rel="stylesheet">
</head>
<body>
<%@include file="include/navBar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <%@include file="include/mainMenu.jsp" %>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">Доходы</h3>
                <a class="d-flex align-items-center text-muted" id="save-income" href="#">
                    <span id="save-item-income" style="text-decoration: underline;  color: #007bff;">сохранить доход&nbsp;</span>
                </a>
            </div>
            <form:form method="post" class="form-inline" name="incomeForm" id="addincome" action="/income/add">
                <input type="number" step="0.01" min="0" max="999999999" class="form-control" name="amount"
                       id="inputSumIncome"
                       placeholder="Сумма" onKeyDown="if(this.value.length==9) return false;"
                       value="${findincome.amount}">
                <div class="input-group-append">
                    <span class="input-group-text">₽</span>
                </div>
                <c:choose>
                    <c:when test="${findincome.account.accountid == null}">
                        <div class="form-group mx-sm-1">
                            <select class="form-control" id="accountbyuser" name="accountid"></select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group mx-sm-1">
                            <div class="form-group mx-sm-1">
                                <select class="form-control" name="accountid">
                                    <c:forEach var="account" items="${findallaccountbyusersort}">
                                        <option value=<c:out value="${account.accountid}"/>><c:out
                                                value="${account.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${findincome.category.categoryid == null}">
                        <div class="form-group mx-sm-1">
                            <select class="form-control" id="allcategory" name="categoryid"></select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group mx-sm-1">
                            <div class="form-group mx-sm-1">
                                <select class="form-control" name="categoryid">
                                    <c:forEach var="category" items="${findallcategoriessort}">
                                        <option value=<c:out value="${category.categoryid}"/>><c:out
                                                value="${category.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${findincome.dateoper == null}">
                        <div class="form-group mx-sm-1">
                            <input type="date" class="form-control" class="mydate" name="dateoper" id="theDate"
                                   placeholder="Дата">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group mx-sm-1">
                            <input type="date" class="form-control" class="mydate" name="dateoper"
                                   value="${findincome.dateoper}"
                                   placeholder="Дата">
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-group mx-sm-1">
                    <label for="comments" class="sr-only">Комментарий</label>
                    <input type="text" maxlength="50" class="form-control" id="comments" name="comment"
                           placeholder="Комментарий" style="display: inline-block;width:500px;"
                           value="${findincome.comment}">
                </div>
                <div class="form-group mx-sm-1">
                    <input type="text" maxlength="50" class="form-control" name="typeoperationid" value="1"
                           hidden="true">
                </div>
                <div class="form-group mx-sm-1">
                    <input type="text" class="form-control" name="operationid" value="${findoperationid}"
                           hidden="true">
                </div>
            </form:form>
            <table class="table my-5">
                <thead>
                <tr>
                    <td colspan="4">
                        <form:form method="post" name="pickDate" id="pickDate" action="/income/filter">
                            <jsp:include page="include/periodSelection.jsp"/>
                        </form:form>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Наименование</th>
                    <th>Сумма</th>
                    <th>Дата</th>
                    <th>Категория</th>
                    <th>Комментарий</th>
                    <th>Изменить</th>
                    <th>Удалить</th>
                </tr>
                <c:forEach items="${allincomeuser}" var="income">
                    <tr>
                        <td>
                            <div class="">${income[0]} </div>
                        </td>
                        <td>
                            <div class="text-success">${income[1]} руб.</div>
                        </td>
                        <td>
                            <div class=""> ${income[2]} </div>
                        </td>
                        <td>
                            <div class="">${income[3]} </div>
                        </td>
                        <td>
                            <div class="">${income[4]}</div>
                        </td>
                        <td>
                            <a href="/income/find/${income[5]}">Изменить</a>
                        </td>
                        <td>
                            <a href="/income/delete/${income[5]}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table align="center">
                <c:forEach begin="1" end="${countPage}" var="page">
                    <th>
                        <c:choose>
                            <c:when test="${isfilter == 1}">
                                <a href="/income/filter${page}">${page}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/income${page}">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </th>
                </c:forEach>
            </table>
        </main>
    </div>
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
<script src="../resources/js/income.js"></script>
</body>
</html>