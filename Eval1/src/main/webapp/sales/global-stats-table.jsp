<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.ram.RamType" %>
<%@ page import="com.eval1.models.ram.RamTypeFilter" %>
<%@ page import="com.eval1.models.sale.VGlobalSales" %>
<%@ page import="com.eval1.models.sale.VGlobalSalesFilter" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    ListResponse stats = (ListResponse) request.getAttribute("stats");
    List<VGlobalSales> statList = (List<VGlobalSales>) stats.getElements();
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    VGlobalSalesFilter saleFilter = (VGlobalSalesFilter) request.getAttribute("saleFilter");
    String filters = "";
    if (saleFilter != null) {
        filters = saleFilter.getFilterConditions();
    }
%>
<head>
    <title>Mikolo - Ventes</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Ventes par mois
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Ventes par mois
                    </li>
                    <li class="breadcrumb-item">
                        <span class="bullet bg-gray-400 w-5px h-2px"></span>
                    </li>
                    <li class="breadcrumb-item text-muted">
                        Liste
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
                    <div class="card-toolbar flex-row-fluid justify-content-end gap-5" data-select2-id="select2-data-123-mzxj">
                        <!--begin::Add product-->
                        <a  class="btn btn-success" onclick="window.print()">
<%--                        <a href="${pageContext.request.contextPath}/sales/stats/global/pdf" class="btn btn-success">--%>
                            Exporter en pdf
                        </a>
                        <!--end::Add product-->
                    </div>
                </div>
                <!--end::card header-->
                <!--begin::card body-->
                <div class="card-body pt-0">
                    <div class="accordion-body">
                        <form method="get">
                            <div class="row mb-5">
                                <div class="mb-5">
                                    <label>Année</label>
                                    <input type="number" name="year" class="form-control"
                                        <% if (saleFilter != null && saleFilter.getYear() != null) { %>
                                           value="<%=saleFilter.getYear()%>"
                                        <% } %>
                                    >
                                </div>
                            </div>

                            <button class="btn btn-primary" type="submit">
                                Filtrer
                            </button>

                        </form>
                    </div>
                    <!--begin::table-->
                    <table class="table table-row-bordered gy-5 mb-5" id="scenes-list">
                        <thead>
                        <tr class="fw-semibold fs-6 text-muted">
                            <th>Id</th>
                            <th>Mois</th>
                            <th>Nombre de ventes</th>
                            <th>Recettes</th>
                            <th>Comissions</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(VGlobalSales stat : statList) { %>
                        <tr>
                            <td><%=stat.getId()%></td>
                            <td><%= stat.getMonthToStr() %></td>
                            <td><%= stat.getNombreVentes() %></td>
                            <td><%= stat.getRecettes() %></td>
                            <td><%= stat.getComissions() %></td>
                            <td><%= stat.getRecettesFinal() %></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>

                    <canvas id="line-chart" class="mh-400px"></canvas>

                    <ul class="pagination">
                        <li
                                <% if (pageNumber == 1) { %>
                                class="page-item previous disabled"
                                <% } else { %>
                                class="page-item previous"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/sales/stats/global?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/sales/stats/global?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/sales/stats/global?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
                    </ul>

                </div>
                <!--end::card body-->
            </div>
            <!--end::card-->
        </div>
    </div>
    <!--end:content-->
</div>

<!--end::main-->
<script src="${pageContext.request.contextPath}/assets/custom/elementDelete.js"></script>
<%@include file="../includes/layouts/default/bottom.jsp"%>
<%@include file="../includes/delete.jsp"%>


<%--line chart--%>
<script>
    var fontFamily = KTUtil.getCssVariableValue('--bs-font-sans-serif');
    var linectx = document.getElementById('line-chart');


    // Chart labels
    const linelabels = [
        <% for(VGlobalSales stat : statList) { %>
        '<%= stat.getMonthToStr() %>',
        <% } %>
    ];

    // Chart data
    const linedata = {
        labels: linelabels,
        datasets: [
            {
                backgroundColor: '#0d6efd',
                borderColor: '#0d6efd',
                label: 'Ventes par mois',
                barPercentage: 0.9,
                barThickness: 20,
                maxBarThickness: 30,
                minBarLength: 2,
                data: [
                    <% for(VGlobalSales stat : statList) { %>
                    '<%= stat.getRecettesFinal() %>',
                    <% } %>
                ]
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

