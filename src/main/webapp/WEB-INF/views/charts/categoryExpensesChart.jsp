<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="canvas-holder2" style="width:100%">
<div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
    <canvas id="chart-area2" style="display: block; width: 186px; height: 93px;" width="186" height="93" class="chartjs-render-monitor"></canvas>
</div>
<script>
    const config2 = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    <c:forEach var="categoryExpensesMap" items="${familyCategoryExpensesMap}">
                    <c:out value="${categoryExpensesMap.value}, "/>
                    </c:forEach>
                ],
                backgroundColor: [
                    <c:forEach var="categoryExpensesMap" items="${familyCategoryExpensesMap}">
                    getColor(),
                    </c:forEach>
                ],
                label: 'Dataset 1'
            }],
            labels: [
                <c:forEach var="categoryExpensesMap" items="${familyCategoryExpensesMap}">
                '<c:out value="${categoryExpensesMap.key}"/>',
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
                text: 'Траты по категориям за ${month}',
                position: 'top',
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };
</script>

