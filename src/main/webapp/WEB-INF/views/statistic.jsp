<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Отчёты - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">

    <script src="../resources/js/utilsChart.js"></script>
    <script src="../resources/js/Chart.js/Chart.min.js"></script>
    <style>
        canvas {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
    </style>
    <style>
        #nav-item-statistic {
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
                <h3 class="h3">Статистика</h3>
                <a class="d-flex align-items-center text-muted" id="getStatistic" href="#">
                    <span id="get-item-statictic" style="text-decoration: underline;  color: #007bff;">получить данные&nbsp;</span>
                </a>
            </div>
            <c:if test="${family == null}">
                <div class="container-fluid">
                    Недостаточно данных для отображения статистики...
                </div>
            </c:if>
            <c:if test="${family != null}">
                <table class="table my-2 table-borderless">
                    <form:form method="post" name="filterStatistic" id="typeOperation" action="/statistic">
                        <thead>
                        <tr>
                            <th style="text-align: center" class="text-muted">
                                Статистика
                                <c:if test="${filterStatistic.operationType == 2}"> расходов </c:if>
                                <c:if test="${filterStatistic.operationType == 1}"> доходов </c:if>
                                за период
                            </th>
                            <th colspan="2" style="text-align: right">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input operation-type-radio" type="radio" name="operationType" id="expensesRadio"
                                           value="2"
                                    <c:if test="${filterStatistic.operationType == 2}"> checked </c:if>
                                           onchange="this.form.submit()">
                                    <label class="form-check-label" for="expensesRadio">Расходы</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input operation-type-radio" type="radio" name="operationType" id="incomeRadio"
                                           value="1"
                                    <c:if test="${filterStatistic.operationType == 1}"> checked </c:if>
                                           onchange="this.form.submit()">
                                    <label class="form-check-label" for="incomeRadio">Доходы</label>
                                </div>
                            </th>
                        </tr>
                        </thead>
                    </form:form>
                    <jsp:include page="${filterStatistic.operationType == 2?\"include/expensesStatistic.jsp\":\"include/incomeStatistic.jsp\"}"/>
                </table>
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h3 class="h3"></h3>
                    <a class="d-flex align-items-center text-muted" id="getOperations" href="#">
                        <span id="get-operations"
                              style="text-decoration: underline;  color: #007bff;">детали&nbsp;</span>
                    </a>
                </div>
                <table class="table my-5" id="operationTable"
                       <c:if test="${currentPage == 1}"> style="display:none;" </c:if>
                       <c:if test="${currentPage != 1}"> style="" </c:if>
                >
                    <tbody>
                    <tr>
                        <th>Член семьи</th>
                        <th>Счет</th>
                        <th>Сумма</th>
                        <th>Дата</th>
                        <th>Категория</th>
                        <th>Комментарий</th>
                    </tr>
                        <c:forEach items="${operations}" var="operation">
                            <tr>
                                <td>
                                    <div class="">${operation.account.famem.user.login} </div>
                                </td>
                                <td>
                                    <div class="">${operation.account.name} </div>
                                </td>
                                <td>
                                    <div class="text-success">${operation.amount} руб.</div>
                                </td>
                                <td>
                                    <div class=""> ${operation.dateoper} </div>
                                </td>
                                <td>
                                    <div class="">${operation.category.name} </div>
                                </td>
                                <td>
                                    <div class="">${operation.comment}</div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <table  align="center" id="pagesNumber"
                        <c:if test="${currentPage == 1}"> style="display: none" </c:if>
                        <c:if test="${currentPage != 1}"> style="" </c:if>
                >
                <tr>
                    <c:forEach begin="1" end="${pages}" var="page">
                        <th>
                            <a href="/statistic/${page}"
                            <c:if test="${page==currentPage}"> style="text-decoration: underline;" </c:if>>
                                    ${page}</a>
                        </th>
                    </c:forEach>
                </tr>
                </table>
            </c:if>
        </main>
    </div>
</div>
<script>
    var config = {
        type: 'line',
        data: {
            labels: [
                <c:forEach var="point" items="${points}">
                '<c:out value="${point.dateBrief}"/>',
                </c:forEach>
            ],
            datasets: [{
                label:
                    <c:if test="${filterStatistic.operationType == 2}"> 'Расходы', </c:if>
                <c:if test="${filterStatistic.operationType == 1}"> 'Доходы', </c:if>
                fill: false,
                backgroundColor: window.chartColors.blue,
                borderColor: window.chartColors.blue,
                data: [
                    <c:forEach var="point" items="${points}">
                    <c:out value="${point.amount}, "/>
                    </c:forEach>
                ],
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Дата'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Сумма'
                    }
                }]
            }
        }
    };

    window.onload = function () {
        var ctx = document.getElementById('canvas').getContext('2d');
        window.myLine = new Chart(ctx, config);
    };
</script>
<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/statistic.js"></script>
</body>
</html>