<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="canvas-holder2" style="width:60%"><div class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand"><div class=""></div></div><div class="chartjs-size-monitor-shrink"><div class=""></div></div></div>
    <canvas id="chart-area2" style="display: block; width: 186px; height: 93px;" width="186" height="93" class="chartjs-render-monitor"></canvas>
</div>
<script>
    /*const getColor = function () {
        const number = Math.round(Math.random() * 10);
        if (number === 0) return window.chartColors.red;
        if (number === 1) return window.chartColors.orange;
        if (number === 2) return window.chartColors.yellow;
        if (number === 3) return window.chartColors.lightgreen;
        if (number === 4) return window.chartColors.blue;
        if (number === 5) return window.chartColors.lemon;
        if (number === 6) return window.chartColors.darkgreen;
        if (number === 7) return window.chartColors.darkpink;
        if (number === 8) return window.chartColors.green;
        if (number === 9) return window.chartColors.pink;
        if (number === 10) return window.chartColors.purple;
    };*/
    const config2 = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    <c:forEach var="categoryExpenses" items="${familyCategoryExpenses}">
                    <c:out value="${categoryExpenses}, "/>
                    </c:forEach>
                ],
                backgroundColor: [
                    <c:forEach var="categoryExpenses" items="${familyCategoryExpenses}">
                    getColor(),
                    </c:forEach>
                ],
                label: 'Dataset 1'
            }],
            labels: [
                <c:forEach var="category" items="${categoryList}">
                '<c:out value="${category.name}"/>',
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
                text: 'Траты за месяц по категориям',
                position: 'bottom',
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };
/*    window.onload = function () {
        var ctx2 = document.getElementById('chart-area2').getContext('2d');
        window.myDoughnut2 = new Chart(ctx2, config2);
    };*/
</script>

