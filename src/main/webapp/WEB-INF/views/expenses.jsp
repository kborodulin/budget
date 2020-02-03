<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Main</title>

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
            </div>
            <form:form class="form-inline" name="expensesForm" action="/account/expenses/newExpenses">
                <div class="form-group mx-sm-3">
                    <label for="inputSum" class="sr-only">Сумма</label>
                    <input type="text" class="form-control" name="sum" id="inputSum" placeholder="Сумма">
                </div>
                <select class="custom-select mx-sm-3" name="currency">
                    <option value="1">руб.</option>
                    <option value="2">дол.</option>
                    <option value="3">евро</option>
                </select>
                <select class="custom-select mx-sm-3" name="wallet">
                    <option selected>Счет</option>
                    <option value="1">42151723423 (Сбер)</option>
                    <option value="2">124124143 (Сбер)</option>
                </select>
                <select class="custom-select mx-sm-3" name="сategory">
                    <option selected>Категория</option>
                    <option value="1">Работа</option>
                    <option value="2">Кредит</option>
                    <option value="3">Депозит</option>
                    <option value="4">Коммуналка</option>
                    <option value="5">Телефон</option>
                </select>
                <div class="form-group mx-sm-3">
                    <label for="inputSum" class="sr-only">Комментарий</label>
                    <input type="text" maxlength="200" class="form-control" id="comments" name="comments"
                           placeholder="Комментарий">
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
                                <input type="date" class="form-control" name="statDate" id="statDate"
                                       aria-describedby="basic-addon1">
                            </div>
                        </form:form>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="">Коммуналка</div>
                        <div class="text-muted" style="font-size: x-small">за уборку подъезда</div>
                    </td>
                    <td>
                        <div class="text-danger">-1000 руб.</div>
                        <div class="text-muted" style="font-size: x-small">12.12.2020</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="">Одежда</div>
                        <div class="text-muted" style="font-size: x-small">D&G</div>
                    </td>
                    <td>
                        <div class="text-danger">-20000</div>
                        <div class="text-muted" style="font-size: x-small">14.02.2020</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="">Кино</div>
                        <div class="text-muted" style="font-size: x-small">Холоп</div>
                    </td>
                    <td>
                        <div class="text-danger">-500</div>
                        <div class="text-muted" style="font-size: x-small">14.01.2020</div>
                    </td>
                </tr>
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
<script src="../resources/js/personalAccount.js"></script>
</body>
</html>