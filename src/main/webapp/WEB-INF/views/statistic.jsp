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
        canvas{
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
                    <form:form method="post" name="filterStatistic" id="filterStatistic" action="/statistic">
                    <tbody>
                    <tr>
                    <td rowspan="2" style="width: 75%;">
                        <div style="width:100%;">
                            <canvas id="canvas"></canvas>
                        </div>
                    </td>
                    <td colspan="2">
                        <div class="form-group" data-toggle="tooltip" data-placement="top" title="Период может быть от 1 недели до 1 года">
                            <div style="color: #007bff">Период</div>
                            <div class="form-group mx-sm-1">
                                <label for="startDate">с&ensp;</label>
                                <input type="date" class="form-control" class="mydate" name="startDate" id="startDate"
                                       value="${filterStatistic.startDate}"
                                       placeholder="Дата">
                            </div>
                            <div class="form-group mx-sm-1">
                                <label for="endDate">по</label>
                                <input type="date" class="form-control" class="mydate" name="endDate" id="endDate"
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
                    <tr>
                        <td>
                            <div style="color: #007bff">Члены семьи</div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="all" id="defaultCheck1" name="flagAllFamilyMembers"
                                <c:if test="${filterStatistic.flagAllFamilyMembers=='all'}"> checked </c:if>
                                >
                                <label class="form-check-label" for="defaultCheck1">
                                    Все
                                </label>
                            </div>
                            <c:forEach items="${familyMembers}" var="familyMember">
                                <div class="form-check">
                                    <input class="form-check-input qwe" type="checkbox" value=" <c:out value="${familyMember.famemid}"/>" id="defaultCheck2" name="familyMembers"
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
                                    <input class="form-check-input" type="checkbox" value="all" name="flagAllCategories" id="defaultCheck3"
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
                        </tr>
                        </tbody>
                    </form:form>
                </table>
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h3 class="h3"></h3>
                    <a class="d-flex align-items-center text-muted" id="getOperations" href="#">
                        <span id="get-operations"
                              style="text-decoration: underline;  color: #007bff;">детали&nbsp;</span>
                    </a>
                </div>

                <table class="table my-5" id="operationTable" style="display:none;">
                    <tbody>
                    <tr>
                        <th>Член семьи</th>
                        <th>Счет</th>
                        <th>Сумма</th>
                        <th>Дата</th>
                        <th>Категория</th>
                        <th>Комментарий</th>
                    </tr>
                    <c:forEach items="${points}" var="point">
                        <c:forEach items="${point.operations}" var="operation">
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
                    </c:forEach>
                    </tbody>
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
                label: 'Расходы',
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
                text: 'Статистика расходов за период'
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