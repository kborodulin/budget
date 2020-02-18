<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Переводы - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">


    <style>
        #nav-item-transactions {
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
                <h3 class="h3">Переводы</h3>
                <a class="d-flex align-items-center text-muted" href="#">
                    <span style="text-decoration: underline;  color: #007bff;" id="save-item-wallet">сохранить перевод&nbsp;</span>
                </a>
            </div>
            <form:form class="form-inline" name="transaction" action="/transactions/savetrans" id="transactionForm">
                <div class="row">
                    <div class="form-group mb-3">
                        <input type="number" step="10" min="0" max="999999999" class="form-control" id="inputSumWallet"
                               placeholder="Сумма" required name="outSum" style="width: 8em">
                        <div class="input-group-append">
                            <span class="input-group-text">₽</span>
                        </div>
                    </div>
                    <div class="form-group mb-3">
                        <select class="form-control custom-select mx-sm-3" name="outAccountId" id="outAccount" required>
                            <option selected>Счет отправления</option>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <input type="date" class="form-control" class="mydate" name="dateOper" id="theDate" required>
                    </div>
                    <div class="form-group mb-3">
                        <select class="form-control custom-select mx-sm-3" name="inUserId" id="inUser" required>
                            <option selected disabled>Получатель</option>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <select class="form-control custom-select mx-sm-3" name="inAccountId" id="inAccount" required>
                            <option selected>Счет получателя</option>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <div class="form-group mx-sm-3">
                            <input type="text" maxlength="50" class="form-control" id="comments" name="comment"
                                   placeholder="Комментарий" required>
                        </div>
                    </div>
                </div>

            </form:form>


            <table class="table my-5">
                <thead>
                <tr>
                    <td colspan="2">
                        <form:form name="pickDate" id="pickDate">
                            <jsp:include page="include/walletPeriodSelection.jsp"/>
                        </form:form>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th colspan="2">Исходящий счет/имя</th>
                    <th colspan="2">Входящий счет/имя</th>
                    <th>Сумма</th>
                    <th>Дата</th>
                    <th>Комментарий</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="transa" items="${transactions}">
                    <tr>
                        <td>${transa.outName}</td>
                        <td>${transa.outOwner}</td>
                        <td>${transa.inName}</td>
                        <td>${transa.inOwner}</td>
                        <td>${transa.outSum}</td>
                        <td>${transa.dateOper}</td>
                        <td>${transa.comment}</td>
                        <td>
                            <a class="editTransfer" href="#">
                                <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                            </a>
                        </td>
                        <td>
                            <a class="removeTransfer" href="#">
                                <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather text-danger"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                            </a>
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
                                <a href="/transactions/filter/${page}">${page}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/transactions/${page}">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </th>
                </c:forEach>
            </table>
        </main>
    </div>
</div>


<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/fillAccounts.js"></script>
<script src="../resources/js/wallets.js"></script>

<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/personalAccount.js"></script>

</body>
</html>