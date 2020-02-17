<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Счета - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">


    <style>
        #nav-item-wallet {
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
                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>Cписок счетов</span>
                    <a class="d-flex align-items-center text-muted" id="addNewInvoice" href="#" aria-label="Новый счет">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                             class="feather feather-plus-circle">
                            <circle cx="12" cy="12" r="10"></circle>
                            <line x1="12" y1="8" x2="12" y2="16"></line>
                            <line x1="8" y1="12" x2="16" y2="12"></line>
                        </svg>
                    </a>
                </h6>
                <ul class="nav flex-column mb-2" id="accountsList">
                </ul>
            </div>

        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">Счета</h3>
                <a class="d-flex align-items-center text-muted" href="#">
                    <span style="text-decoration: underline;  color: #007bff; display: none" id="save-item-wallet">сохранить перевод&nbsp;</span>
                </a>
            </div>
            <form:form class="form-inline" name="transaction" action="/wallet/savetrans" id="transactionForm">
                    <div class="form-group">
                        <input type="number" step="10" min="0" max="999999999" class="form-control" id="inputSumWallet"
                               placeholder="Сумма" required name="outSum">
                        <div class="input-group-append">
                            <span class="input-group-text">₽</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <select class="form-control custom-select mx-sm-3" name="outAccountId" id="outAccount" required>
                            <option selected>Счет отправления</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <span style="margin:0 5px" class="form-control-plaintext">--></span>
                    </div>
                    <div class="form-group">
                        <select class="form-control custom-select mx-sm-3" name="inUserId" id="inUser" required>
                            <option selected disabled>Получатель</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control custom-select mx-sm-3" name="inAccountId" id="inAccount" required>
                            <option selected>Счет получателя</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="form-group mx-sm-3">
                            <label for="comments" class="sr-only">Комментарий</label>
                            <input type="text" maxlength="50" class="form-control" id="comments" name="comment"
                                   placeholder="Комментарий" required>
                        </div>
                    </div>
            </form:form>


            <table class="table my-5">
                <thead>
                <tr>
                    <td colspan="2">
                        <form:form name="pickDate" id="pickDate">
                            <jsp:include page="include/periodSelection.jsp"/>
                        </form:form>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="">со чета сбер1</div>
                        <div class="text-muted" style="font-size: x-small">перевел с одного счета на другой</div>
                    </td>
                    <td>
                        <div class="text-danger">-1000 руб.</div>
                        <div class="text-muted" style="font-size: x-small">14.02.2020</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="">на счет сбер2</div>
                        <div class="text-muted" style="font-size: x-small">перевел с одного счета на другой</div>
                    </td>
                    <td>
                        <div class="text-success">1000 руб.</div>
                        <div class="text-muted" style="font-size: x-small">14.02.2020</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </main>
    </div>
</div>


<!-- Modal -->
<form:form name="createNewAccountForm" action="/wallet/create" method="post">
    <div class="modal fade bd-example-modal-sm" id="newWalletDialog" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Новый счет</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="walletName" class="col-form-label">Название</label><br>
                        <input type="text" class="form-control" id="walletName" name="name" maxlength=20>
                    </div>
                    <div class="form-group">
                        <label for="walletType" class="col-form-label">Тип счета</label><br>
                        <select class="form-control" id="walletType" name="acctypeid"></select>
                    </div>
                    <input type="hidden" name="amount" value="0">
                    <input type="hidden" name="dateopen" value="">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </div>
        </div>
    </div>
</form:form>

<%@include file="include/modalDeleteAccount.jsp" %>
<%@include file="include/modalRecoverAccount.jsp" %>

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