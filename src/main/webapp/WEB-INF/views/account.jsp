<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Main</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="resources/styleMain.css">


    <style>
        #nav-item-settings {
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
    <link href="resources/css/dashboard.css" rel="stylesheet">
</head>
<body>
<%@include file="navBar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <%@include file="mainMenu.jsp" %>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">Личные данные</h3>
                <a class="d-flex align-items-center text-muted" href="#">
                    <span id="edition-account" style="text-decoration: underline; display:inherit; color: #007bff;">редактировать&nbsp;</span>
                    <span id="save-item-account" style="text-decoration: underline; display:none; color: red;">сохранить изменения&nbsp;</span>
                </a>
            </div>
            <form:form method="post" class="form-signup" id="setingForm" name="personalInfoForm"  action="account/saveinfo">
            <div style="white-space: nowrap;" class="col-md-6 mb-3">
                <input type="text" class="form-control set-control" id="Login" placeholder="Логин" value="${user.login}" required="" disabled>
                <input type="email" class="form-control" id="Email" placeholder="Email" value="${user.email}" required="" disabled>
            </div>
            <div style="white-space: nowrap;" class="col-md-6 mb-3">
                <input type="text" class="form-control set-control" id="firstName" placeholder="Имя" value="${famem.name}"  name="name" required="" disabled>
                <input type="text" class="form-control set-control" id="lastName" placeholder="Отчество" value="${famem.patronymic}" name="patronymic" required="" disabled>
                <input type="text" class="form-control set-control" id="family" placeholder="Фамилия" value="${famem.surname}" name="surname" required="" disabled>
            </div>
            <div style="white-space: nowrap;" class="col-md-6 mb-3">
                <input type="date" class="form-control set-control" id="DOB" placeholder="Дата рождения" value="${famem.datebirth}" name="datebirth" required="" disabled>
                (дата рождения)
            </div>
            </form:form>
            <jsp:include page="${famem.familyid!=null?\"familyExist.jsp\":\"familyNoExist.jsp\"}"/>
            <script src="resources/js/jquery/jquery.slim.min.js"></script>
            <script src="resources/js/bs/bootstrap.bundle.min.js"></script>
            <script src="resources/js/featherIcons/feather.min.js"></script>
            <script src="resources/js/Chart.js/Chart.min.js"></script>
            <script src="resources/js/dashboard.js"></script>
            <script src="resources/js/personalAccount.js"></script>
</body>
</html>