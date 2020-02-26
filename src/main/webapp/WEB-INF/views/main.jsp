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
        <%@include file="include/mainMenu.jsp" %>

        <main role="main" class="col-md-9 col-lg-10 px-4" style="margin-left: 190px">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">Главная</h3>
            </div>
            <c:if test="${family == null}">
                <div>
                    <h5>Добро пожаловать, <span style="font-style: italic"> ${user.login}</span>!</h5>
                    <div> Для полноценного использования приложения необходимо выполнить следующие действия:</div>
                    <ul>
                        <li>
                            заполнить данные о себе в разделе
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-user">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                            <span style="font-weight: 700"> Личный кабинет</span>;
                        </li>
                        <li>
                            создать свою семьи в разделе
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-user">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                            <span style="font-weight: 700"> Личный кабинет </span> или вступить в уже существующую по
                            приглашению;
                        </li>
                        <li>
                            создать счета в разделе
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-layers">
                                <polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
                                <polyline points="2 17 12 22 22 17"></polyline>
                                <polyline points="2 12 12 17 22 12"></polyline>
                            </svg>
                            ;
                            <span style="font-weight: 700"> Счета</span>;
                        </li>
                        <li>
                            вести учет расходов и доходов в соответствующих разделах приложенияю.
                        </li>
                    </ul>
                    <div> И, ура, вы сможете контролировать доходы и расходы семьи с помощью диаграмм и графиков легко и
                        просто!
                    </div>
                </div>
            </c:if>
            <c:if test="${family != null}">
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
                    <table class="table my-1 table-borderless" style="width: 40%">
                        <tbody>
                        <c:forEach var="familyMembersBalance" items="${familyMembersBalanceMap}">
                            <tr>
                                <td>
                                        ${familyMembersBalance.key}
                                </td>
                                <td>
                                    <c:if test="${familyMembersBalance.value gt 0}">
                                        <span class="text-success">${familyMembersBalance.value} ₽</span>
                                    </c:if>
                                    <c:if test="${familyMembersBalance.value lt 0}">
                                        <span class="text-danger">${familyMembersBalance.value} ₽</span>
                                    </c:if>
                                    <c:if test="${!(familyMembersBalance.value gt 0) && !(familyMembersBalance.value lt 0)}">
                                        <span>${familyMembersBalance.value} ₽</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <jsp:include page="include/progress.jsp"/>
                    <table class="table my-3"></table>
                    <c:if test="${summaryAllFamilyExpenses != 0}">
                        <table class="table my-3">
                            <tbody>
                            <tr>
                                <th style="width: 50%">
                                    <jsp:include page="charts/memberExpensesChart.jsp"/>
                                </th>
                                <th style="width: 50%">
                                    <jsp:include page="charts/categoryExpensesChart.jsp"/>
                                </th>
                            </tr>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${operationList != null}">
                        <h5>Последние совершенные операции</h5>
                        <table class="table my-3" style="width: 60%">
                           <tbody>
                           <c:forEach items="${operationList}" var="operation">
                           <tr>
                               <td>
                                   <div class="" style="font-size: large">${operation.account.famem.user.login} </div>
                                   <div class="text-muted" style="font-size: small">${operation.category.name}</div>
                               </td>

                               <td>
                                   <c:if test="${operation.typeoperationid == 1 || operation.typeoperationid == 4}">
                                       <div class="text-success" style="font-size: large">+${operation.amount} руб.</div>
                                   </c:if>
                                   <c:if test="${operation.typeoperationid == 2 || operation.typeoperationid == 3}">
                                       <div class="text-danger" style="font-size: large">-${operation.amount} руб.</div>
                                   </c:if>
                                   <div class="text-muted" style="font-size: small">${operation.dateoper}</div>
                               </td>
                           </tr>
                           </c:forEach>
                           </tbody>
                        </table>
                        </c:if>
                        <c:if test="${summaryAllFamilyExpenses == 0}">
                        <div class="text-muted" style="font-size: x-small">Для отображения информационных диаграм необходимо заполнить расходы семьи</div>
                        </c:if>
                </div>
            </c:if>
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