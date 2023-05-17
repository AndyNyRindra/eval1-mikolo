<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.Role" %>
<%@ page import="com.eval1.models.shop.view.ShopInput" %>
<%@ page import="com.eval1.models.shop.Shop" %>
<%@ page import="com.eval1.models.brand.Brand" %>
<%@ page import="com.eval1.models.ram.RamType" %>
<%@ page import="com.eval1.models.screen.ScreenType" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    ScreenType screenType = (ScreenType) request.getAttribute("screenType");
%>
<head>
    <title>Mikolo - Ecrans</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Modification de type d'écran
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Type d'écran
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
                            <label>Nom :</label>
                            <input type="text" name="name" class="form-control" required
                                   value="<%=screenType.getName()%>"
                            >
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
                            send(formData, "${pageContext.request.contextPath}/screens/<%=screenType.getId()%>", "${pageContext.request.contextPath}/screens")
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../includes/layouts/default/bottom.jsp"%>
