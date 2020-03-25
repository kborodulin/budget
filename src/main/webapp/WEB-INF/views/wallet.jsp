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
        <%@include file="include/mainMenu.jsp" %>
        <main role="main" class="col-md-9 col-lg-10 px-4" style="margin-left: 190px">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h3 class="h3">Счета</h3>
                <a class="d-flex align-items-center text-muted" id="addNewInvoice" href="#">
                    <span style="text-decoration: underline;  color: #007bff;">добавить счет</span>
                </a>
            </div>
            <c:if test="${err != null}">
                <div class="alert alert-danger" role="alert">
                    ${err}
                </div>
            </c:if>

            <table class="table my-5">
                <thead>
                <tr><th>Название</th><th>Тип</th><th>Владелец</th><th>Баланс</th><th></th><th></th></tr>
                </thead>
                <tbody>
                <c:forEach var="account" items="${accounts}">
                    <c:if test="${account.isclosesign<1||account.amount>0||account.amount<0}">
                        <tr data-accountid=<c:out value="${account.accountid}"/> >
                            <td class="nav-link <c:choose><c:when test="${account.isclosesign>0}">text-muted</c:when><c:when test="${account.amount>0}">text-success</c:when><c:when test="${account.amount<0}">text-danger</c:when></c:choose>">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card"><rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect><line x1="1" y1="10" x2="23" y2="10"></line></svg>
                                    <c:out value="${account.name}"/>
                            </td>
                            <td>
                                ${types.stream().filter(t->t.accounttypeid.equals(account.accounttype.accounttypeid)).findFirst().get().name}
                            </td>
                            <td>
                                ${users.stream().filter(u->u.getUserid().equals(famems.stream().filter(f->f.getFamemid().equals(account.famem.famemid)).findFirst().get().user.userid)).findFirst().get().login}
                            </td>
                            <td>${account.amount}₽</td>
                            <td>
                                <c:if test="${account.isclosesign<1&&account.famem.famemid.equals(myfamem.famemid)}">
                                    <a class="editWallet" href="#" data-accountid=<c:out value="${account.accountid}"/> data-accountname="${account.name}" data-accounttypeid="${account.accounttype.accounttypeid}" data-amount="${account.amount}">
                                        <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                                    </a>
                                </c:if>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.isclosesign<1&&account.famem.famemid.equals(myfamem.famemid)}">
                                        <a class="removeWallet" href="#" data-walletid="${account.accountid}" data-walletname="${account.name}" data-amount="${account.amount}" data-closed="${account.isclosesign}">
                                            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather text-danger"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                                        </a>
                                    </c:when>
                                    <c:when test="${account.famem.famemid.equals(myfamem.famemid)}">
                                        <a class="recoverWallet" href="#" data-walletid="${account.accountid}" data-walletname="${account.name}" data-amount="${account.amount}" data-closed="${account.isclosesign}">
                                            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="feather text-muted"><polyline points="1 4 1 10 7 10"></polyline><path d="M3.51 15a9 9 0 1 0 2.13-9.36L1 10"></path></svg>
                                        </a>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</div>

<%@include file="include/modalCreateAccount.jsp" %>
<%@include file="include/modalDeleteAccount.jsp" %>
<%@include file="include/modalRecoverAccount.jsp" %>
<%@include file="include/modalEditAccount.jsp" %>

<script src="../resources/js/jquery/jquery.slim.min.js"></script>
<script src="../resources/js/fillWallets.js"></script>
<script src="../resources/js/wallets.js"></script>

<script src="../resources/js/bs/bootstrap.bundle.min.js"></script>
<script src="../resources/js/featherIcons/feather.min.js"></script>
<script src="../resources/js/Chart.js/Chart.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
<script src="../resources/js/personalAccount.js"></script>

</body>
</html>