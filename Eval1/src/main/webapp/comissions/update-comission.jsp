<%@ page import="com.eval1.models.comission.Comission" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    Comission comission = (Comission) request.getAttribute("comission");
%>
<head>
    <title>Mikolo - Comissions</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Modification de Comissions
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Comissions
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
                            <label>Valeur minimum :</label>
                            <input type="text" name="minValue" class="form-control" required
                                   value="<%= comission.getMinValue() %>"/>


                        </div>
                        <div class="mb-5">
                            <label>Valeur maximum :</label>
                            <input type="text" name="maxValue" class="form-control" required
                                    value="<%= comission.getMaxValue() %>"/>

                        </div>
                        <div class="mb-5">
                            <label>Pourcentage :</label>
                            <input type="text" name="percent" class="form-control" required
                                      value="<%= comission.getPercent() %>"/>

                        </div>
                        <p>
                            <input type="reset" value="Effacer" class="btn btn-reset">
                            <input type="submit" value="Ajouter" class="btn btn-primary">
                        </p>
                    </form>
                    <%@include file="/includes/scripts.jsp"%>
                    <script>
                        const form = document.getElementById('form');

                        form.addEventListener('submit', function(evnt) {
                            evnt.preventDefault();
                            const formData = new FormData(form);
                            send(formData, "${pageContext.request.contextPath}/comissions/<%=comission.getId()%>", "${pageContext.request.contextPath}/comissions")
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../includes/layouts/default/bottom.jsp"%>
