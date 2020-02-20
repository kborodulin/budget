<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Главная - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">

    <script src="../resources/js/utilsChart.js"></script>
    <script src="../resources/js/Chart.js/Chart.min.js"></script>

    <style>
        #nav-item-main {
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

    <style>
        canvas {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
    </style>
    <style type="text/css">/* Chart.js */
    @keyframes chartjs-render-animation {
        from {
            opacity: .99
        }
        to {
            opacity: 1
        }
    }

    .chartjs-render-monitor {
        animation: chartjs-render-animation 1ms
    }

    .chartjs-size-monitor, .chartjs-size-monitor-expand, .chartjs-size-monitor-shrink {
        position: absolute;
        direction: ltr;
        left: 0;
        top: 0;
        right: 0;
        bottom: 0;
        overflow: hidden;
        pointer-events: none;
        visibility: hidden;
        z-index: -1
    }

    .chartjs-size-monitor-expand > div {
        position: absolute;
        width: 1000000px;
        height: 1000000px;
        left: 0;
        top: 0
    }

    .chartjs-size-monitor-shrink > div {
        position: absolute;
        width: 200%;
        height: 200%;
        left: 0;
        top: 0
    }</style>
</head>

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
                <h3 class="h3">Главная</h3>
            </div>
            <div class="container-fluid">
                <h5 class="h5">Баланс семьи
                    <span style="font-style: italic">${family.name} </span>
                    <c:if test="${familyBalance gt 0}">
                    <span class="text-success">${familyBalance} ₽</span>
                    </c:if>
                    <c:if test="${familyBalance lt 0}">
                        <span class="text-danger">${familyBalance} ₽</span>
                    </c:if>
                    <c:if test="${!(familyBalance gt 0) && !(familyBalance lt 0)}">
                        <span class="text-info">${familyBalance} ₽</span>
                    </c:if>
                </h5>
                <table class="table my-3"></table>
                <jsp:include page="include/progress.jsp"/>
                <table class="table my-3"></table>
                    <table class="table">
                        <tbody>
                        <tr>
                            <th style="width: 30%"><jsp:include page="charts/memberExpensesChart.jsp"/></th>
                            <th style="width: 40%"><jsp:include page="charts/categoryExpensesChart.jsp"/></th>
                            <th style="width: 30%"><jsp:include page="charts/memberBalanceChart.jsp"/></th>
                        </tr>
                        </tbody>
                    </table>
            </div>
        </main>
    </div>
</div>
<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/personalAccount.js"></script>
<script src="../resources/js/utils.js"></script>
<script src="../resources/js/chartsLoad.js"></script>
</body>
</html>