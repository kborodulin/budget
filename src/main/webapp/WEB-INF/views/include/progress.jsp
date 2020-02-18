<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome gt 75}">
    <div class="progress">
        <div class="progress-bar bg-danger" role="progressbar"
             style="width: <c:out value="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}"/>%"
             aria-valuenow="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<c:if test="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome gt 50 and summaryAllFamilyExpenses*100/summaryAllFamilyIncome lt 75}">
    <div class="progress">
        <div class="progress-bar bg-warning" role="progressbar"
             style="width: <c:out value="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}"/>%"
             aria-valuenow="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<c:if test="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome gt 25 and summaryAllFamilyExpenses*100/summaryAllFamilyIncome lt 50}">
    <div class="progress">
        <div class="progress-bar bg-info" role="progressbar"
             style="width: <c:out value="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}"/>%"
             aria-valuenow="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<c:if test="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome lt 25}">
    <div class="progress">
        <div class="progress-bar bg-success" role="progressbar"
             style="width: <c:out value="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}"/>%"
             aria-valuenow="${summaryAllFamilyExpenses*100/summaryAllFamilyIncome}" aria-valuemin="0"
             aria-valuemax="100"></div>
    </div>
</c:if>
<table class="table my-0">
    <tbody>
    <tr>
        <th style="text-align: left">Расходы за месяц</th>
        <th style="text-align: right">Доходы за месяц</th>
    </tr>
    </tbody>
</table>