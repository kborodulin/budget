<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-plus-circle"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line></svg>
                    </a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="#" style="color: green">
                            <span data-feather="credit-card"></span>
                            42151723423 (Сбер1)
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"style="color: red">
                            <span data-feather="credit-card"></span>
                            124124143 (Сбер2)
                        </a>
                    </li>
                </ul>
            </div>

        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">Счета</h3>
            </div>
            <form:form class="form-inline" name="transactionForm" action="/account/wallet/newTransaction">
                    <div class="form-group mx-sm-3">
                        <label for="inputSum" class="sr-only">Сумма перевода</label>
                        <input type="text" class="form-control" name="sum" id="inputSum" placeholder="Сумма">
                    </div>
                    <select class="custom-select mx-sm-3" name="currency">
                        <option value="1">руб.</option>
                        <option value="2">дол.</option>
                        <option value="3">евро</option>
                    </select>
                    <select class="custom-select mx-sm-3" name="walletOut">
                        <option selected>Счет отправления</option>
                        <option value="1">42151723423 (Сбер)</option>
                        <option value="2">124124143 (Сбер)</option>
                    </select>
                <select class="custom-select mx-sm-3" name="walletIn">
                    <option selected>Счет получения</option>
                    <option value="1">42151723423 (Сбер)</option>
                    <option value="2">124124143 (Сбер)</option>
                </select>
                    <div class="form-group mx-sm-3">
                        <label for="inputSum" class="sr-only">Комментарий</label>
                        <input type="text" maxlength="200" class="form-control" id="comments" name="comments" placeholder="Комментарий">
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
            </form:form>



            <table class="table my-5">
                <thead>
                <tr>
                    <td colspan="2">
                        <form:form name="pickDate" id="pickDate">
                            <div class="input-group m-3 w-25">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">Статистика на</span>
                                </div>
                                <input type="date" class="form-control" name="statDate" id="statDate" aria-describedby="basic-addon1">
                            </div>
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
<div class="modal fade bd-example-modal-sm" id="newWalletDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                    <input type="text" class="form-control" id="walletName" name="walletName" maxlength="50">
                </div>
                <div class="form-group">
                    <label for="walletNum" class="col-form-label">Номер счета</label><br>
                    <input type="text" class="form-control" id="walletNum" name="walletNum">
                    <small id="walletNumWarn" class="form-text text-muted text-danger"></small>
                </div>
                <div class="form-group">
                    <label for="walletCur" class="col-form-label">Валюта счета</label><br>
                    <select class="form-control" id="walletCur" name="walletCur">
                        <option value="1">Рубли</option>
                        <option value="2">Доллары</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="walletType" class="col-form-label">Тип счета</label><br>
                    <select class="form-control" id="walletType" name="walletType">
                        <option value="1">Наличные</option>
                        <option value="2">Банковский вклад</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </div>
    </div>
</div>


<script src="../resources/js/jquery/jquery.slim.min.js"></script>

<script src="../resources/js/wallets.js"></script>

<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/personalAccount.js"></script>
</body>
</html>