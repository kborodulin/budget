<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${percentOfExpenses gt 75}">
    <div class="progress">
        <div class="progress-bar bg-danger" role="progressbar"
             style="width: <c:out value="${percentOfExpenses}"/>%"
             aria-valuenow="${percentOfExpenses}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<c:if test="${percentOfExpenses gt 50 and percentOfExpenses lt 75}">
    <div class="progress">
        <div class="progress-bar bg-warning" role="progressbar"
             style="width: <c:out value="${percentOfExpenses}"/>%"
             aria-valuenow="${percentOfExpenses}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<c:if test="${percentOfExpenses gt 25 and percentOfExpenses lt 50}">
    <div class="progress">
        <div class="progress-bar bg-info" role="progressbar"
             style="width: <c:out value="${percentOfExpenses}"/>%"
             aria-valuenow="${percentOfExpenses}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<c:if test="${percentOfExpenses lt 25}">
    <div class="progress">
        <div class="progress-bar bg-success" role="progressbar"
             style="width: <c:out value="${percentOfExpenses}"/>%"
             aria-valuenow="${percentOfExpenses}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<table class="table my-0">
    <tbody>
    <tr>
        <th style="text-align: left">Расходы за ${month} <span style="font-style: italic" class="text-danger">-${summaryAllFamilyExpenses} ₽</span></th>
        <th style="text-align: right">Доходы за  ${month} <span style="font-style: italic" class="text-success">+${summaryAllFamilyIncome} ₽</span></th>
    </tr>
    </tbody>
</table>