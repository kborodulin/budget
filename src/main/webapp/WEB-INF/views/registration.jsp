<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="RU">
<head>
    <meta charset="UTF-8">
    <title>Регистрация - Контроль расходов</title>
    <link rel="stylesheet" href="resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="resources/style.css" >
</head>
<body>

<form:form method="post" class="form-signup" id="registrationForm" name="userRegistrationForm">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Контроль семейного бюджета</h1>
    </div>
    <div id="errors">
        <c:forEach var="err" items="${result.getAllErrors()}">
            <c:out value="${err.getDefaultMessage()}"/> <br>
        </c:forEach>
    </div>
    <div class="form-label-group">
        <input type="email" id="inputEmail" class="form-control" placeholder="введите email" name="email" value="${regUser.email}" maxlength="50" required="required">
        <input type="login" id="inputLoginReg" class="form-control" placeholder="введите логин" name="login" value="${regUser.login}" maxlength="15" required="required" autofocus>
        <input type="password" id="inputPasswordReg" class="form-control" placeholder="введите пароль" name="password" maxlength="15" required="required">
        <input type="password" id="inputRepeatPassword" class="form-control" placeholder="пароль повторно" maxlength="15" required="required" name="repeatPassword">
        <button class="btn btn-lg btn-primary btn-block" id="buttonSignin" type="submit" >Зарегистрироваться</button>
        <p><a id="authBtn" href="/login">авторизация</a> для зарегистрированных пользователей </p>
    </div>
</form:form>

<p class="mt-5 mb-3 text-muted text-center">&copy;2020</p>
<script src="/resources/js/checkreg.js"></script>
</body>
</html>

