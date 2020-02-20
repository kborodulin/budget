<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="canvas-holder" style="width:100%">
<div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
    <canvas id="chart-area" style="display: block; width: 186px; height: 93px;" width="186" height="93" class="chartjs-render-monitor"></canvas>
</div>
<script>
    const getColor = function() {
        return '#' + Math.floor(Math.random()*16777215).toString(16)
    };
    const config = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    <c:forEach var="memberExpenses" items="${familyMembersExpenses}">
                    <c:out value="${memberExpenses}, "/>
                    </c:forEach>
                ],
                backgroundColor: [
                    <c:forEach var="memberExpenses" items="${familyMembersExpenses}">
                    getColor(),
                    </c:forEach>
                ],
                label: 'Dataset 1'
            }],
            labels: [
                <c:forEach var="member" items="${familyMembers}">
                '<c:out value="${member.user.login}"/>',
                </c:forEach>
            ]
        },
        options: {
            responsive: true,
            legend: {
                position: 'bottom',
            },
            title: {
                display: true,
                text: 'Траты за ${month}',
                position: 'top',
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };
</script>

