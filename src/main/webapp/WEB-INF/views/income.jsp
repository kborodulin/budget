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
                <input type="number" step="0.01" min="0" max="999999999" style="width:150px;" class="form-control" name="amount"
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
                            <a class="editOperation" href="/income/find/${income[5]}">
                                <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                            </a>
                        </td>
                        <td>
                            <a class="removeOperation" href="/income/delete/${income[5]}">
                                <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather text-danger"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <c:choose>
                    <c:when test="${countPage == page || countPage == 1}">
                        <tr class="table-active" style="font-style: italic">
                            <td>
                                    ${intervalperiod}
                            </td>
                            <td style="color: #1e7e34" colspan="6">
                                    ${sumperiod}
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
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