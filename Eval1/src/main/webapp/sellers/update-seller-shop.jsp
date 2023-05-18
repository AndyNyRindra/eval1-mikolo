
<%@ page import="com.eval1.models.screen.ScreenType" %>
<%@ page import="com.eval1.models.seller.Seller" %>
<%@ page import="com.eval1.models.shop.Shop" %>
<%@ page import="java.util.List" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    Seller seller = (Seller) request.getAttribute("seller");
    List<Shop> shops = (List<Shop>) request.getAttribute("shops");
%>
<head>
    <title>Mikolo - Utilisateurs</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Modification d'un utilisateur
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Utilisateur
                    </li>
                    <li class="breadcrumb-item">
                        <span class="bullet bg-gray-400 w-5px h-2px"></span>
                    </li>
                    <li class="breadcrumb-item text-muted">
                        Formulaire
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
                    <form id="form" method="post" >
                        <div class="mb-5">
                            <label>Magasin :</label>
                            <select name="shopId" class="form-select"
                                    data-control="select2" data-placeholder="Magasin"
                                    data-allow-clear="true">
                                <option value="" >--Magasins--</option>
                                <% for (Shop shop : shops
                                ) { %>
                                <option value="<%= shop.getId() %>"
                                        <% if (shop != null && seller.getShop() != null  && shop.getId().equals(seller.getShop().getId())) { %>
                                        selected
                                        <% } %>
                                > <%= shop.getName() %> </option>
                                <% } %>
                            </select>
                        </div>



                        <p>
                            <input type="reset" value="Réinitialiser" class="btn btn-reset">
                            <input type="submit" value="Modifier" class="btn btn-primary">
                        </p>
                    </form>
                    <%@include file="/includes/scripts.jsp"%>
                    <script>
                        const form = document.getElementById('form');

                        form.addEventListener('submit', function(evnt) {
                            evnt.preventDefault();
                            const formData = new FormData(form);
                            send(formData, "${pageContext.request.contextPath}/sellers/<%=seller.getId()%>", "${pageContext.request.contextPath}/sellers")
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../includes/layouts/default/bottom.jsp"%>
