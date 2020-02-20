<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="canvas-holder3" style="width:100%">
<div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
    <canvas id="chart-area3" style="display: block; width: 186px; height: 93px;" width="186" height="93" class="chartjs-render-monitor"></canvas>
</div>
<script>
    const config3 = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    <c:forEach var="memberBalance" items="${familyMembersBalance}">
                    <c:out value="${memberBalance}, "/>
                    </c:forEach>
                ],
                backgroundColor: [
                    <c:forEach var="memberBalance" items="${familyMembersBalance}">
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
                text: 'Текущий баланс членов семьи',
                position: 'top',
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };
</script>

