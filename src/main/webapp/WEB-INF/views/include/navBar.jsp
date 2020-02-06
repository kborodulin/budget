<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0"id="heading" href="#">Контроль расходов</a>

    <ul class="navbar-nav ml-md-auto px-3">
        <li class="nav-item dropdown">
            <a class="nav-item nav-link dropdown-toggle mr-md-2" href="#" id="nav-account" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                42151723423 (Сбер1)
            </a>
            <div class="dropdown-menu dropdown-menu-md-right" aria-labelledby="nav-account">
                <a class="dropdown-item active" href="#">42151723423 (Сбер1)</a>
                <a class="dropdown-item" href="#">124124143 (Сбер2)</a>
            </div>
        </li>
        <li class="nav-item">
            <span id="head-currentBalance" class="nav-link">1000/40000</span>
        </li>
        <li class="nav-item">
            <c:if test="${alert!=null}">
                <a class="nav-link p-2" href="#" data-toggle="modal" data-target="#exampleModal">
                    <svg id="bellAlertActive" class="bi bi-bell-fill" width="2em" height="2em" viewBox="0 0 20 20" fill="red"
                         xmlns="http://www.w3.org/2000/svg">
                        <path d="M10 18a2 2 0 002-2H8a2 2 0 002 2zm.995-14.901a1 1 0 10-1.99 0A5.002 5.002 0 005 8c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"></path>
                    </svg>
                </a>
            </c:if>
            <c:if test="${alert==null}">
            <div class="nav-link p-2">
                <svg id="bellAlertDisabled" class="bi bi-bell-fill" width="1em" height="1em" viewBox="0 0 20 20"
                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path d="M10 18a2 2 0 002-2H8a2 2 0 002 2zm.995-14.901a1 1 0 10-1.99 0A5.002 5.002 0 005 8c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"></path>
                </svg>
            </div>
            </c:if>
        </li>
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Выход</a>
        </li>
    </ul>
</nav>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Приглашение для вступления в семью</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Вас приглашают вступить в семью "${invitingFamily.name}".
            </div>
            <form:form method="post" class="form-signup" id="alertInfoAccept" name="alertInfo" action="/alertproc/accept" style="display:none;">
                    <input type="text" name="alertid" value="${alert.alertid}" required="">
            </form:form>

            <form:form method="post" class="form-signup" id="alertInfoDenied" name="alertInfo" action="/alertproc/denied" style="display:none;">
                <input type="text" name="alertid" value="${alert.alertid}" required="">
            </form:form>
            <div class="modal-footer">
                <button id="deniedAlert" type="button" class="btn btn-secondary" data-dismiss="modal">Отклонить приглашение</button>
                <button id="acceptAlert" type="button" class="btn btn-primary" data-dismiss="modal">Вступить в семью</button>
            </div>
        </div>
    </div>
</div>