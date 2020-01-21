<%--
  Created by IntelliJ IDEA.
  User: sw
  Date: 21.01.2020
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Личный кабинет</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/styleMain.css" >


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0"id="heading" href="#">Контроль расходов</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Выход</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
                            <span data-feather="home"></span>
                            Операции <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file"></span>
                            Доходы
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="shopping-cart"></span>
                            Расходы
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="users"></span>
                            Пользователи
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="bar-chart-2"></span>
                            Отчеты
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="layers"></span>
                            Счета
                        </a>
                    </li>
                </ul>

                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>Счета</span>
                    <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                        <span data-feather="plus-circle"></span>
                    </a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Егор <span>1000$</span><span class="fall">&#9660</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Мария <span>1000$</span><span class="rise">&#9650</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Настенька <span>1000$</span><span class="rise">&#9650</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            Костян <span>1000$</span><span class="fall">&#9660</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            ИТОГО <span>4000$</span><span class="rise">&#9650</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Операции</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Поделиться</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Экспорт</button>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                        Неделя
                    </button>
                </div>
            </div>

            <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>

            <h2>Последние операции</h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Дата</th>
                        <th>Наименование</th>
                        <th>Тип</th>
                        <th>Счёт</th>
                        <th>Сумма</th>
                        <th>Баланс</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>01.01.2020</td>
                        <td>молоко</td>
                        <td>продукты</td>
                        <td>Егор</td>
                        <td>20</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>01.01.2020</td>
                        <td>соль</td>
                        <td>продукты</td>
                        <td>Мария</td>
                        <td>5</td>
                        <td>elit</td>
                    </tr>
                    <tr>
                        <td>01.01.2020</td>
                        <td>автооомобиль</td>
                        <td>крупные расходы</td>
                        <td>Егор</td>
                        <td>2 000 000</td>
                        <td>Praesent</td>
                    </tr>
                    <tr>
                        <td>01.01.2020</td>
                        <td>не помню на что</td>
                        <td>????</td>
                        <td>Мария</td>
                        <td>cursus</td>
                        <td>ante</td>
                    </tr>
                    <tr>
                        <td>01.01.2020</td>
                        <td>ту да же</td>
                        <td>????</td>
                        <td>Егор</td>
                        <td>Sed</td>
                        <td>nisi</td>
                    </tr>
                    <tr>
                        <td>01.01.2020</td>
                        <td>молоко</td>
                        <td>продукты</td>
                        <td>Костян</td>
                        <td>sem</td>
                        <td>at</td>
                    </tr>
                    <tr>
                        <td>01.01.2020</td>
                        <td>игрушка</td>
                        <td>игрушки</td>
                        <td>Егор</td>
                        <td>imperdiet</td>
                        <td>Duis</td>
                    </tr>
                    <tr>
                        <td>08.01.2020</td>
                        <td>бизнес ланч</td>
                        <td>еда</td>
                        <td>Егор</td>
                        <td>Praesent</td>
                        <td>mauris</td>
                    </tr>
                    <tr>
                        <td>09.01.2020</td>
                        <td>яблоко</td>
                        <td>продукты</td>
                        <td>Егор</td>
                        <td>tellus</td>
                        <td>sed</td>
                    </tr>
                    <tr>
                        <td>10.01.2020</td>
                        <td>туфли</td>
                        <td>одежда</td>
                        <td>Мария</td>
                        <td>porta</td>
                        <td>Mauris</td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
<script src="js/jquery/jquery.slim.min.js" ></script>
<script src="js/bs/bootstrap.bundle.min.js"></script>
<script src="js/featherIcons/feather.min.js"></script>
<script src="js/Chart.js/Chart.min.js"></script>
<script src="js/dashboard.js"></script></body>
</html>
