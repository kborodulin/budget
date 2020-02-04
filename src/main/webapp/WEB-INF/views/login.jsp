<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="RU">
<head>
    <meta charset="UTF-8">
    <title>Авторизация - Контроль расходов</title>
    <link rel="stylesheet" href="resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="resources/style.css" >
</head>
<body>

<form:form class="form-signin" id="authForm" method="post" name="userAuthForm">
    <fieldset>
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Контроль семейного бюджета</h1>
        </div>
        <div id="errors">
            <c:out value="${error.equals(\"true\")?\"Ошибка авторизации\":\"\"}"/>
        </div>
        <div class="form-label-group">
            <input type="login" name="login" class="form-control" placeholder="введите логин" required autofocus>
            <input type="password" name="password" class="form-control" placeholder="введите пароль" required>
            <button class="btn btn-lg btn-primary btn-block" id="buttonLogin" type="submit">Войти</button>
            <p><a id="regBtn" href="/registration">регистрация</a> для новых пользователей </p>
        </div>
    </fieldset>
</form:form>
<p class="mt-5 mb-3 text-muted text-center">&copy;2020</p>
</body>
</html>