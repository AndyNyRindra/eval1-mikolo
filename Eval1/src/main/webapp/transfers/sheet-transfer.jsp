<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>

<%@ page import="com.eval1.models.sale.Sale" %>
<%@ page import="com.eval1.models.sale.SaleFilter" %>
<%@ page import="com.eval1.models.shop.Shop" %>
<%@ page import="com.eval1.models.sale.SaleDetails" %>
<%@ page import="com.eval1.models.transfer.TransferDetails" %>
<%@ page import="com.eval1.models.transfer.Transfer" %>
<%@include file="../includes/layouts/default/top-seller.jsp"%>
<%
    ListResponse details = (ListResponse) request.getAttribute("details");
    List<TransferDetails> transferDetails = (List<TransferDetails>) details.getElements();
    Transfer transfer = (Transfer) request.getAttribute("transfer");
//    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
//    Integer pageNumber = (Integer) request.getAttribute("page");
//    SaleFilter saleFilter = (SaleFilter) request.getAttribute("saleFilter");
//    String filters = "";
//    if (saleFilter != null) {
//        filters = saleFilter.getFilterConditions();
//    }
%>
<head>
    <title>Mikolo - Transferts</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Détails Transferts
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Transferts
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
                            <h2><b class="card-text col-md-10 col-sm-10" style="margin-top: 20px;">Transfer <%=transfer.getReference()%></b></h2>

                        </div>

                    </div>
                    <div class="row">
                        <div class="row mb-5">
                            <div class="col-md-10 col-sm-10">
                                <div class="scene-desc">
                                    <div class="scene-desc-icon">

                                    </div>
                                    <div class="scene-desc-text">
                                        <b>Source: </b> <%=transfer.getShopSender().getName()%>
                                    </div>
                                </div>
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
                                        <b>Total:</b> <%=transfer.getAmount()%> Ar
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
                                        <b>Date:</b> <%=transfer.getDateToStr()%>
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
                    <form id="form" method="post">
                    <table class="table table-row-bordered gy-5" id="scenes-list">
                        <thead>
                        <tr class="fw-semibold fs-6 text-muted">
                            <th>Id</th>
                            <th>Ordinateur</th>
                            <th>Quantité envoyé</th>
                            <th>Quantité recu</th>
                            <th>Prix Unitaire</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(TransferDetails transferDetails1 : transferDetails) { %>
                        <tr>
                            <td><%=transferDetails1.getId()%></td>
                            <td>
                                <%= transferDetails1.getLaptop().getBrand().getName() %> - <%= transferDetails1.getLaptop().getName() %> - <%= transferDetails1.getLaptop().getReference() %>
                            </td>
                            <td>
                                <%= transferDetails1.getQuantity() %>
                            </td>
                            <td>
                                <input class="form-control" type="number" name="quantities">
                            </td>
                            <td>
                                <%= transferDetails1.getUnitPrice() %> Ar
                            </td>

                            <td>
                                <input type="hidden" name="transferDetails" value="<%=transferDetails1.getId()%>">
                            </td>


                        </tr>
                        <% } %>
                        <tr>
                            <td><input type="submit" value="Valider" class="btn btn-primary"></td>
                        </tr>
                        </tbody>
                    </table>
                    </form>
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

    <script>
        const form = document.getElementById('form');

        form.addEventListener('submit', function(evnt) {
            evnt.preventDefault();
            const formData = new FormData(form);
            send(formData, "${pageContext.request.contextPath}/transfer/<%=transfer.getId()%>/receipts", null)
        });
    </script>
