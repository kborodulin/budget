<%--
  Created by IntelliJ IDEA.
  User: sw
  Date: 21.01.2020
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Bootstrap core CSS -->
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="resources/style.css" >
    <!-- Favicons -->
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
    <link href="floating-labels.css" rel="stylesheet">
</head>
<body>
<form class="form-signin">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">контроль семейного бюджета</h1>
    </div>

    <div class="form-label-group" style="display: none;" id="registrationForm">
        <label id="labelEmail" for="inputEmail">email</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="введите email" >
        <label id="labelLogin" for="inputLogin">логин</label>
        <input type="login" id="inputLogin" class="form-control" placeholder="введите логин"  required autofocus>
        <label id="labelPassword" for="введите пароль">пароль</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="введите пароль" required>
        <label id="labelRepeatPassword" for="пароль повторно">пароль</label>
        <input type="password" id="inputRepeatPassword" class="form-control" placeholder="пароль повторно" required  >
        <button class="btn btn-lg btn-primary btn-block" id="buttonSignin" type="submit" >продолжить</button>
        <p>  <a id="authBtn" href="/">авторизация</a> для зарегистрированных пользователей </p>
    </div>
    <div class="form-label-group" id="authForm">
        <label id="labelLogin" for="inputLogin">логин</label>
        <input type="login" id="inputLogin" class="form-control" placeholder="введите логин" required autofocus>
        <label id="labelPassword" for="введите пароль">пароль</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="введите пароль" required>
        <button class="btn btn-lg btn-primary btn-block" id="buttonLogin" type="submit">продолжить</button>
        <p>  <a id="regBtn" href="/">регистрация</a> для новых пользователей </p>
    </div>
    <p class="mt-5 mb-3 text-muted text-center">&copy; 2020</p>
</form>
<script src="resources/js/main.js"></script>
</body>
</html>
