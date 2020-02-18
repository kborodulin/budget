window.onload = function () {
    var ctx = document.getElementById('chart-area').getContext('2d');
    var ctx2 = document.getElementById('chart-area2').getContext('2d');
    var ctx3 = document.getElementById('chart-area3').getContext('2d');
    window.myDoughnut = new Chart(ctx, config);
    window.myDoughnut2 = new Chart(ctx2, config2);
    window.myDoughnut3 = new Chart(ctx3, config3);
};
