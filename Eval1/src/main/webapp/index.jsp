
<%@include file="/includes/layouts/default/top.jsp"%>

<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Test chart
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Chart
                    </li>
                    <li class="breadcrumb-item">
                        <span class="bullet bg-gray-400 w-5px h-2px"></span>
                    </li>
                    <li class="breadcrumb-item text-muted">
                        Chart
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--end::toolbar-->
    <!--begin::content-->
    <div class="app-content flex-column-fluid">
        <div class="app-container container-xxl">
            <!--begin::card-->
            <div class="card card-flush">
                <!--begin::card header-->
                <div class="card-header align-items-center py-0 gap-2">
                </div>
                <!--end::card header-->
                <!--begin::card body-->
                <div class="card-body pt-0">
                    <canvas id="bar-chart" class="mh-400px"></canvas>
                    <canvas id="line-chart" class="mh-400px"></canvas>
                    <canvas id="pie-chart" class="mh-400px"></canvas>
                    <canvas id="stacked-bar" class="mh-400px"></canvas>
                    <canvas id="radar-chart" class="mh-400px"></canvas>
                    <% if (session.getAttribute("admin") != null) { %>
                    0
                    <% } else { %>
                    1
                    <% } %>
                    <%@include file="/includes/scripts.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>
<%--bar chart--%>
<script>
    var barctx = document.getElementById('bar-chart');

    // Define fonts
    var fontFamily = KTUtil.getCssVariableValue('--bs-font-sans-serif');

    const array = [
        {id: 1, name: 'January', value: 10},
        {id: 2, name: 'February', value: 20},
        {id: 3, name: 'March', value: 30},
        {id: 4, name: 'April', value: 40},
        {id: 5, name: 'May', value: 50},
        {id: 6, name: 'June', value: 60},
        {id: 7, name: 'July', value: 70},
        {id: 8, name: 'August', value: 80},
        {id: 9, name: 'September', value: 90},
        {id: 10, name: 'October', value: 100},
        {id: 11, name: 'November', value: 110},
        {id: 12, name: 'December', value: 120}
    ]
    // Chart labels
    const barlabels = array.map(function (e) {
        return e.name;
    });


    // Chart data
    const bardata = {
        labels: barlabels,
        datasets: [
            {
                backgroundColor: '#0d6efd',
                label: 'Dataset 1',
                barPercentage: 0.9,
                barThickness: 20,
                maxBarThickness: 30,
                minBarLength: 2,
                data: array.map(function (e) {
                    return e.value;
                })
            },
            {
                backgroundColor: '#6c757d',
                label: 'Dataset 2',
                barPercentage: 0.9,
                barThickness: 20,
                maxBarThickness: 30,
                minBarLength: 2,
                data: [120, 110, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10]
            },
            {
                backgroundColor: '#dc3545',
                label: 'Dataset 3',
                barPercentage: 0.9,
                barThickness: 20,
                maxBarThickness: 30,
                minBarLength: 2,
                data: [12,90,130,34,56,78,90,12,34,56,78,90]
            }
        ]
    };

    // Chart config
    const barconfig = {
        type: 'bar',
        data: bardata,
        options: {
            plugins: {
                title: {
                    display: false,
                }
            },
            responsive: true,
            interaction: {
                intersect: false,
            },
            scales: {
                x: {
                    grid: {
                        offset: true
                    }
                },
                y: {
                    stacked: false
                }
            }
        },
        defaults:{
            global: {
                defaultFont: fontFamily
            }
        }
    };

    // Init ChartJS -- for more info, please visit: https://www.chartjs.org/docs/latest/
    var barChart = new Chart(barctx, barconfig);
</script>

<%--line chart--%>
<script>
    var linectx = document.getElementById('line-chart');


    // Chart labels
    const linelabels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

    // Chart data
    const linedata = {
        labels: linelabels,
        datasets: [
            {
                backgroundColor: '#6c757d',
                borderColor: '#6c757d',
                label: 'Dataset 1',
                barPercentage: 0.9,
                barThickness: 20,
                maxBarThickness: 30,
                minBarLength: 2,
                data: [120, 110, 100, 90, 80, 70, 60]
            }
        ]
    };

    // Chart config
    const lineconfig = {
        type: 'line',
        data: linedata,
        options: {
            plugins: {
                title: {
                    display: false,
                }
            },
            responsive: true,
        },
        defaults:{
            global: {
                defaultFont: fontFamily
            }
        }
    };

    // Init ChartJS -- for more info, please visit: https://www.chartjs.org/docs/latest/
    var lineChart = new Chart(linectx, lineconfig);
</script>

<%--pie chart--%>
<script>
    var piectx = document.getElementById('pie-chart');


    // Chart labels
    const pielabels = ['January', 'February', 'March', 'April', 'May'];

    // Chart data
    const piedata = {
        labels: pielabels,
        datasets: [
            {
                backgroundColor: ['#6c757d', '#0d6efd', '#dc3545', '#ffc107', '#198754'],
                borderColor: '#6c757d',
                label: 'Dataset 1',
                barPercentage: 0.9,
                barThickness: 20,
                maxBarThickness: 30,
                minBarLength: 2,
                data: [10,10,20,25,35]
            }
        ]
    };

    // Chart config
    const pieconfig = {
        type: 'pie',
        data: piedata,
        options: {
            plugins: {
                title: {
                    display: false,
                }
            },
            responsive: true,
        },
        defaults:{
            global: {
                defaultFont: fontFamily
            }
        }
    };

    // Init ChartJS -- for more info, please visit: https://www.chartjs.org/docs/latest/
    var pieChart = new Chart(piectx, pieconfig);
</script>
<%@include file="/includes/layouts/default/bottom.jsp"%>
