<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Расход - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">


    <style>
        #nav-item-expenses {
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
                <h3 class="h3">Расходы</h3>
                <a class="d-flex align-items-center text-muted" id="save-expenses" href="#">
                    <span id="save-item-expenses" style="text-decoration: underline;  color: #007bff;">сохранить расход&nbsp;</span>
                </a>
            </div>
            <form:form id= "addExpense" class="form-inline" name="expensesForm" action="/expenses/add">
                <input type="number" step="0.01" min="0" max="999999999" class="form-control" name="amount" value="${updatedOperation.amount}"
                       id="inputSumExpense"
                       placeholder="Сумма" onKeyDown="if(this.value.length==9) return false;">
                <div class="input-group-append">
                    <span class="input-group-text">₽</span>
                </div>
                <div class="form-group mx-sm-1">
                    <select class="form-control" name="accountid">
                        <c:forEach var="account" items="${findallaccountbyuser}">
                            <c:if test="${updatedOperation.account.name == account.name}">
                                <option selected value=<c:out value="${account.accountid}"/>><c:out value="${account.name}"/></option>
                            </c:if>
                            <c:if test="${updatedOperation.account.name != account.name}">
                                <option value=<c:out value="${account.accountid}"/>><c:out value="${account.name}"/></option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group mx-sm-1">
                    <select class="form-control" name="categoryid">
                    <c:forEach var="category" items="${refallcategory}">
                        <c:if test="${updatedOperation.category.name == category.name}">
                            <option selected value=<c:out value="${category.categoryid}"/>><c:out value="${category.name}"/></option>
                        </c:if>
                        <c:if test="${updatedOperation.category.name != category.name}">
                            <option value=<c:out value="${category.categoryid}"/>><c:out value="${category.name}"/></option>
                        </c:if>
                    </c:forEach>
                    </select>
                </div>
                <div class="form-group mx-sm-1">
                    <c:choose>
                        <c:when test="${updatedOperation.dateoper == null}">
                            <input type="date" class="form-control" class="mydate" name="dateoper" id="theDate"
                                   placeholder="Дата">
                        </c:when>
                        <c:otherwise>
                            <input type="date" class="form-control" class="mydate" name="dateoper"
                                   value="${updatedOperation.dateoper}"
                                   placeholder="Дата">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group mx-sm-1">
                    <label for="comments" class="sr-only">Комментарий</label>
                    <input type="text" maxlength="50" class="form-control" id="comments" name="comment" value="${updatedOperation.comment}"
                           placeholder="Комментарий" style="display: inline-block; width:500px;">
                </div>
                <div class="form-group mx-sm-1">
                    <input type="text" maxlength="50" class="form-control" name="typeoperationid" value="2" hidden="true">
                </div>
                <div class="form-group mx-sm-1">
                    <input type="text" class="form-control" name="operationid" value="${updatedOperation.operationid}"
                           hidden="true">
                </div>
            </form:form>
            <table class="table my-5">
                <thead>
                    <tr>
                        <td colspan="2">
                            <form:form method="post" name="pickDate" id="pickDate" action="/expenses/filter">
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
                <c:forEach items="${allExpensesUser}" var="expense">
                    <tr>
                        <td>
                            <div class="">${expense.account.name} </div>
                        </td>
                        <td>
                            <div class="text-success">${expense.amount} руб.</div>
                        </td>
                        <td>
                            <div class=""> ${expense.dateoper} </div>
                        </td>
                        <td>
                            <div class="">${expense.category.name} </div>
                        </td>
                        <td>
                            <div class="">${expense.comment}</div>
                        </td>
                        <td>
                            <a href="/expenses/${expense.operationid}">Изменить</a>
                        </td>
                        <td>
                            <a href="/expenses/delete/${expense.operationid}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</div>
<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/expenses.js"></script>
<script src="../resources/js/refCategory.js"></script>
<script src="../resources/js/utils.js"></script>
<script src="../resources/js/refAccountByUser.js"></script>
</body>
</html>