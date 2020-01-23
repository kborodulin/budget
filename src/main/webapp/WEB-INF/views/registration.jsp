
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="RU">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="resources/css/bs/bootstrap.min.css">
    <link rel="stylesheet" href="resources/style.css" >
</head>
<body>
<form class="form-signup" style="display: none;" id="registrationForm" method="post" name="userRegistrationForm">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Контроль семейного бюджета</h1>
    </div>
    <div class="form-label-group">
        <label for="inputEmail">email</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="введите email" name="email">
        <label for="inputLoginReg">логин</label>
        <input type="login" id="inputLoginReg" class="form-control" placeholder="введите логин" name="username" required autofocus>
        <label for="inputPasswordReg">пароль</label>
        <input type="password" id="inputPasswordReg" class="form-control" placeholder="введите пароль" name="password" required>
        <label for="inputRepeatPassword">пароль</label>
        <input type="password" id="inputRepeatPassword" class="form-control" placeholder="пароль повторно" required name="repeatPassword">
        <button class="btn btn-lg btn-primary btn-block" id="buttonSignin" type="submit" >Зарегистрироваться</button>
        <p><a id="authBtn" href="/">авторизация</a> для зарегистрированных пользователей </p>
    </div>
    <p class="mt-5 mb-3 text-muted text-center">&copy;2020</p>
</form>

<form class="form-signin" id="authForm" method="post" name="userAuthForm">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Контроль семейного бюджета</h1>
    </div>
    <div class="form-label-group">
        <label for="inputLogin">логин</label>
        <input type="login" id="inputLogin" class="form-control" placeholder="введите логин" required autofocus>
        <label for="inputPassword">пароль</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="введите пароль" required>
        <button class="btn btn-lg btn-primary btn-block" id="buttonLogin" type="submit">Войти</button>
        <p><a id="regBtn" href="/">регистрация</a> для новых пользователей </p>
    </div>
    <p class="mt-5 mb-3 text-muted text-center">&copy; 2020</p>
</form>
<script src="resources/js/main.js"></script>
</body>
</html>


