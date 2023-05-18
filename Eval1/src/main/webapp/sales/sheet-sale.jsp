<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>

<%@ page import="com.eval1.models.sale.Sale" %>
<%@ page import="com.eval1.models.sale.SaleFilter" %>
<%@ page import="com.eval1.models.shop.Shop" %>
<%@ page import="com.eval1.models.sale.SaleDetails" %>
<%@include file="../includes/layouts/default/top-seller.jsp"%>
<%
    ListResponse details = (ListResponse) request.getAttribute("details");
    List<SaleDetails> saleDetails = (List<SaleDetails>) details.getElements();
    Sale sale = (Sale) request.getAttribute("sale");
//    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
//    Integer pageNumber = (Integer) request.getAttribute("page");
//    SaleFilter saleFilter = (SaleFilter) request.getAttribute("saleFilter");
//    String filters = "";
//    if (saleFilter != null) {
//        filters = saleFilter.getFilterConditions();
//    }
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
                    Détails Vente
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Vente
                    </li>
                    <li class="breadcrumb-item">
                        <span class="bullet bg-gray-400 w-5px h-2px"></span>
                    </li>
                    <li class="breadcrumb-item text-muted">
                        Détails
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--end::toolbar-->
    <!--begin::content-->
    <div class="app-content flex-column-fluid">
        <div class="app-container container-xxl">

            <div class="card mb-5">
                <div class="card-body pt-5 pb-1 px-1" style="margin-left: 50px">
                    <div class="row mb-5">
                        <div class="col-md-7 col-sm-7 mt-5">
                            <h2><b class="card-text col-md-10 col-sm-10" style="margin-top: 20px;">Vente <%=sale.getReference()%></b></h2>

                        </div>

                    </div>
                    <div class="row">
                        <div class="row mb-5">
                            <div class="col-md-10 col-sm-10">
                                <div class="scene-desc">
                                    <div class="scene-desc-icon">
                                        <i class="la la-dollar text-gray-900 fs-1"></i>
                                    </div>
                                    <div class="scene-desc-text">
                                        <b>Total:</b> <%=sale.getAmount()%> Ar
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-5">
                            <div class="col-md-10 col-sm-10">
                                <div class="scene-desc">
                                    <div class="scene-desc-icon">
                                        <i class="la la-calendar text-gray-900 fs-1"></i>
                                    </div>
                                    <div class="scene-desc-text">
                                        <b>Date:</b> <%=sale.getDateToStr()%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--begin::card-->
            <div class="card card-flush">
                <!--begin::card header-->
                <div class="card-header align-items-center py-0 gap-2">

                </div>
                <!--end::card header-->
                <!--begin::card body-->
                <div class="card-body pt-0">

                    <!--begin::table-->
                    <table class="table table-row-bordered gy-5" id="scenes-list">
                        <thead>
                        <tr class="fw-semibold fs-6 text-muted">
                            <th>Id</th>
                            <th>Ordinateur</th>
                            <th>Quantité</th>
                            <th>Prix Unitaire</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(SaleDetails saleDetails1 : saleDetails) { %>
                        <tr>
                            <td><%=saleDetails1.getId()%></td>
                            <td>
                                <%= saleDetails1.getLaptop().getBrand().getName() %> - <%= saleDetails1.getLaptop().getName() %> - <%= saleDetails1.getLaptop().getReference() %>
                            </td>
                            <td>
                                <%= saleDetails1.getQuantity() %>
                            </td>
                            <td>
                                <%= saleDetails1.getUnitPrice() %> Ar
                            </td>


                        </tr>
                        <% } %>
                        </tbody>
                    </table>
<%--                    <ul class="pagination">--%>
<%--                        <li--%>
<%--                                <% if (pageNumber == 1) { %>--%>
<%--                                class="page-item previous disabled"--%>
<%--                                <% } else { %>--%>
<%--                                class="page-item previous"--%>
<%--                                <% } %>--%>
<%--                        ><a href="${pageContext.request.contextPath}/sales?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>--%>
<%--                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>--%>
<%--                        <li--%>
<%--                                <% if (pageNumber == i) { %>--%>
<%--                                class="page-item active"--%>
<%--                                <% } else { %>--%>
<%--                                class="page-item"--%>
<%--                                <% } %>--%>
<%--                        ><a href="${pageContext.request.contextPath}/sales?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>--%>
<%--                        <% } %>--%>
<%--                        <li--%>
<%--                                <% if (pageNumber == requiredPages) { %>--%>
<%--                                class="page-item next disabled"--%>
<%--                                <% } else { %>--%>
<%--                                class="page-item next"--%>
<%--                                <% } %>--%>
<%--                        ><a href="${pageContext.request.contextPath}/sales?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>--%>
<%--                    </ul>--%>

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
