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
                    <form:form method="post" name="filterStatistic" id="filterStatistic" action="/statistic/filter">
                    <tbody>
                    <tr>
                    <td rowspan="2" style="width: 75%;">
                        <div style="width:100%;">
                            <canvas id="canvas"></canvas>
                        </div>
                    </td>
                    <td colspan="2">
                        <div class="form-group">
                            <label for="exampleFormControlSelect1" style="color: #007bff">Период</label>
                            <select class="form-control" id="exampleFormControlSelect1" disabled>
                                    <%--        <option value="1" ${periodselected == 1 ? "selected" : ""}>день</option>--%>
                                    <%--        <option value="2" ${periodselected == 2 ? "selected" : ""}>неделя</option>--%>
                                    <%--        <option value="3" ${periodselected == 3 ? "selected" : ""}>месяц</option>--%>
                                <option value="4">год</option>
                                    <%--        <option value="5" ${periodselected == 5 ? "selected" : ""}>весь</option>--%>
                            </select>
                        </div>
                    </td>
                    <tr>
                        <td>
                            <div style="color: #007bff">Члены семьи</div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" checked>
                                <label class="form-check-label" for="defaultCheck1">
                                    Все
                                </label>
                            </div>
                            <c:forEach items="${familyMembers}" var="familyMember">
                                <div class="form-check">
                                    <input class="form-check-input qwe" type="checkbox" value="" id="defaultCheck2" checked>
                                    <label class="form-check-label" for="defaultCheck2">
                                        <c:out value="${familyMember.user.login}"/>
                                    </label>
                                </div>
                            </c:forEach>
                        </td>
                        <td>
                        <div style="color: #007bff">Категории</div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck3" checked>
                                <label class="form-check-label" for="defaultCheck3">
                                    Все
                                </label>
                            </div>
                        <c:forEach items="${categoryList}" var="category">
                            <div class="form-check">
                                <input class="form-check-input qwe3" type="checkbox" value="" id="defaultCheck4" checked>
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
                        <span id="get-operations" style="text-decoration: underline;  color: #007bff;">детали&nbsp;</span>
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
                    <c:forEach items="${operationList}" var="operation">
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


            </c:if>
        </main>
    </div>
</div>
<script>
    var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    var config = {
        type: 'line',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [{
                label: 'Доходы',
                backgroundColor: window.chartColors.red,
                borderColor: window.chartColors.red,
                data: [
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor()
                ],
                fill: false,
            }, {
                label: 'Расходы',
                fill: false,
                backgroundColor: window.chartColors.blue,
                borderColor: window.chartColors.blue,
                data: [
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor(),
                    randomScalingFactor()
                ],
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: 'Статистика расходов и доходов за год'
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
                        labelString: 'Month'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                }]
            }
        }
    };

    window.onload = function() {
        var ctx = document.getElementById('canvas').getContext('2d');
        window.myLine = new Chart(ctx, config);
    };

    document.getElementById('randomizeData').addEventListener('click', function() {
        config.data.datasets.forEach(function(dataset) {
            dataset.data = dataset.data.map(function() {
                return randomScalingFactor();
            });

        });

        window.myLine.update();
    });

    var colorNames = Object.keys(window.chartColors);
    document.getElementById('addDataset').addEventListener('click', function() {
        var colorName = colorNames[config.data.datasets.length % colorNames.length];
        var newColor = window.chartColors[colorName];
        var newDataset = {
            label: 'Dataset ' + config.data.datasets.length,
            backgroundColor: newColor,
            borderColor: newColor,
            data: [],
            fill: false
        };

        for (var index = 0; index < config.data.labels.length; ++index) {
            newDataset.data.push(randomScalingFactor());
        }

        config.data.datasets.push(newDataset);
        window.myLine.update();
    });

    document.getElementById('addData').addEventListener('click', function() {
        if (config.data.datasets.length > 0) {
            var month = MONTHS[config.data.labels.length % MONTHS.length];
            config.data.labels.push(month);

            config.data.datasets.forEach(function(dataset) {
                dataset.data.push(randomScalingFactor());
            });

            window.myLine.update();
        }
    });

    document.getElementById('removeDataset').addEventListener('click', function() {
        config.data.datasets.splice(0, 1);
        window.myLine.update();
    });

    document.getElementById('removeData').addEventListener('click', function() {
        config.data.labels.splice(-1, 1); // remove the label first

        config.data.datasets.forEach(function(dataset) {
            dataset.data.pop();
        });

        window.myLine.update();
    });
</script>
<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/statistic.js"></script>
</body>
</html>