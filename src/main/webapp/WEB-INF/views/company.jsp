<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>о программе - Контроль расходов</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/styleMain.css">


    <style>
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
        <%@include file="include/mainMenu.jsp" %>
        <main role="main" class="col-md-9 col-lg-10 px-4" style="margin-left: 190px">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">О программе</h3>
            </div>
            <div >
                <h6 class="h6"; style="position: relative; text-align:right;">Тратьте меньше, чем зарабатываете,- <br>вот вам и философский камень.<br>Б. Франклин
               &nbsp;
                </h6>
                <p><br></p>
                <p>Так зачем же фиксировать свои ежедневные, еженедельные или ежемесячные траты?</p>
                <p>Во-первых, для того, чтобы разобраться в своей текущей финансовой ситуации.<br>Во-вторых, эта информация нужна нам для того, чтобы изменить свои финансовые привычки.
                    <br>И, наконец, самое важное: мы считаем свои расходы для того, чтобы научиться тратить меньше, чем зарабатываем!
                </p>
                <p>
                    Но мало просто фиксировать расходы и доходы, важно уметь анализировать полученную информацию.
                </p>
                <p>
                    Наше приложение позволит Вам не только фиксировать расходы и доходы, но и наблюдать за ними в динамике с помощью диаграмм и графиков.
                    <br>
                    С нашим приложением Вы сможете анализировать свои траты!
                </p>
                <p>
                    Также основой приложения является участие всей семьи в данном процессе.
                    <br>
                    Но даже если в Вашей семье всего один человек, то Вы всё равно сможете успешно пользоваться всеми возможностями.
                </p>
                <p>
                    Присоединятесь к нам!
                </p>
                <div style="margin-left: 30px;">
                <h7 class="h7"; style="position: relative; text-align:right;"> Авторы:<br> </h7>
                <h7 class="h7"; style="position: relative; text-align:right;"> Никитин Алексей<br>"Порядок в финансах - порядок в семье!"</h7>
                <p><br></p>
                <h7 class="h7"; style="position: relative; text-align:right;"> Белолипецкая Екатерина<br> "Контролируйте свои расходы и становитесь ближе к своей мечте!"</h7>
                <p><br></p>
                <h7 class="h7"; style="position: relative; text-align:right;"> Бородулин Кирилл<br> "Я всегда трачу больше, чем зарабатываю. И мне это нравится!"</h7>
                </div>
                </div>
        </main>
    </div>
</div>


<script src="../resources/js/jquery/jquery.slim.min.js"></script>

<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>

</body>
</html>