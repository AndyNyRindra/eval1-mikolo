<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>

<%@ page import="com.eval1.models.sale.Sale" %>
<%@ page import="com.eval1.models.sale.SaleFilter" %>
<%@ page import="com.eval1.models.shop.Shop" %>
<%@include file="../includes/layouts/default/top-seller.jsp"%>
<%
    ListResponse sales = (ListResponse) request.getAttribute("sales");
    List<Sale> saleList = (List<Sale>) sales.getElements();
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    SaleFilter saleFilter = (SaleFilter) request.getAttribute("saleFilter");
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
                    Ventes
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Ventes
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
                        <a href="${pageContext.request.contextPath}/sales/create" class="btn btn-success">
                            Ajouter une vente
                        </a>
                        <!--end::Add product-->
                    </div>
                </div>
                <!--end::card header-->
                <!--begin::card body-->
                <div class="card-body pt-0">
                    <div class="accordion-body">
                        <form method="get">
                            <div class="mb-5">
                                <input id="name" type="text" name="reference" class="form-control" placeholder="Reference..."
                                    <% if (saleFilter != null && saleFilter.getReference() != null) { %>
                                       value="<%=saleFilter.getReference().replace("%", "")%>"
                                    <% } %>
                                >
                            </div>

                            <div class="row">
                                <div class="mb-5 col-sm-6">
                                    <input id="min" type="text" name="amountMin" class="form-control" placeholder="Montant minimum..."
                                        <% if (saleFilter != null && saleFilter.getAmountMin() != null) { %>
                                           value="<%=saleFilter.getAmountMin()%>"
                                        <% } %>
                                    >
                                </div>
                                <div class="mb-5 col-sm-6">
                                    <input id="max" type="text" name="amountMax" class="form-control" placeholder="Montant maximum..."
                                        <% if (saleFilter != null && saleFilter.getAmountMax() != null) { %>
                                           value="<%=saleFilter.getAmountMax()%>"
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
                    <table class="table table-row-bordered gy-5" id="scenes-list">
                        <thead>
                        <tr class="fw-semibold fs-6 text-muted">
                            <th>Id</th>
                            <th>Référence</th>
                            <th>Date</th>
                            <th>Montant</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(Sale sale : saleList) { %>
                        <tr>
                            <td><%=sale.getId()%></td>
                            <td>
                                <%= sale.getReference() %>
                            </td>
                            <td>
                                <%= sale.getDateToStr() %>
                            </td>
                            <td>
                                <%= sale.getAmount() %> Ar
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/sales/<%= sale.getId() %>" >
                                    <i class="la la-eye text-primary fs-2x"></i>
                                </a>
                            </td>

                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <ul class="pagination">
                        <li
                                <% if (pageNumber == 1) { %>
                                class="page-item previous disabled"
                                <% } else { %>
                                class="page-item previous"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/sales?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/sales?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/sales?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
                    </ul>

                </div>
                <!--end::card body-->
            </div>
            <!--end::card-->
        </div>
    </div>
    <!--end:content-->
</div>
<%@include file="../includes/delete.jsp"%>

<!--end::main-->
<script src="${pageContext.request.contextPath}/assets/custom/elementDelete.js"></script>
<%@include file="../includes/layouts/default/bottom.jsp"%>
