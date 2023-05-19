<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>

<%@ page import="com.eval1.models.drive.DriveType" %>
<%@ page import="com.eval1.models.drive.DriveFilter" %>
<%@ page import="com.eval1.models.comission.Comission" %>
<%@ page import="com.eval1.models.comission.ComissionFilter" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    ListResponse comissions = (ListResponse) request.getAttribute("comissions");
    List<Comission> comissionList = (List<Comission>) comissions.getElements();
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    ComissionFilter comissionFilter = (ComissionFilter) request.getAttribute("comissionFilter");
    String filters = "";
    if (comissionFilter != null) {
        filters = comissionFilter.getFilterConditions();
    }
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
                    Comissions
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Comissions
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
                        <a href="${pageContext.request.contextPath}/comissions/create" class="btn btn-success">
                            Ajouter une Comission
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
                                <label for="minValue">Valeur minimum</label>
                                <input id="minValue" type="text" name="minValue" class="form-control"
                                    <% if (comissionFilter != null && comissionFilter.getMinValue() != null) { %>
                                       value="<%=comissionFilter.getMinValue()%>"
                                    <% } %>
                                >
                            </div>
                            <div class="mb-5">
                                <label for="minValue">Valeur maximum</label>
                                <input id="maxValue" type="text" name="maxValue" class="form-control"
                                    <% if (comissionFilter != null && comissionFilter.getMaxValue() != null) { %>
                                       value="<%=comissionFilter.getMaxValue()%>"
                                    <% } %>
                                >
                            </div>
                            <div class="mb-5">
                                <label for="minPercent">Pourcentage minimum</label>
                                <input id="minPercent" type="text" name="minPercent" class="form-control"
                                    <% if (comissionFilter != null && comissionFilter.getMinPercent() != null) { %>
                                       value="<%=comissionFilter.getMinPercent()%>"
                                    <% } %>
                                >
                            </div>
                            <div class="mb-5">
                                <label for="maxPercent">Pourcentage minimum</label>
                                <input id="maxPercent" type="text" name="maxPercent" class="form-control"
                                    <% if (comissionFilter != null && comissionFilter.getMaxPercent() != null) { %>
                                       value="<%=comissionFilter.getMaxPercent()%>"
                                    <% } %>
                                >
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
                            <th>Valeur minimum</th>
                            <th>Valeur maximum</th>
                            <th>Pourcentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(Comission comission : comissionList) { %>
                        <tr>
                            <td><%=comission.getId()%></td>
                            <td>
                                <%= comission.getMinValue() %>
                            </td>
                            <td>
                                <%= comission.getMaxValue() %>
                            </td>
                            <td>
                                <%= comission.getPercent() %>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/comissions/update/<%= comission.getId() %>" >
                                    <i class="la la-pencil text-warning fs-2x"></i>
                                </a>
                            </td>
                            <td>
                                <a href="#" onclick="onDeleteButtonClicked(<%= comission.getId() %>, '', '${pageContext.request.contextPath}/comissions/delete/<%=comission.getId()%>', 'la comission')"
                                   data-bs-target="#delete-modal" data-bs-toggle="modal">
                                    <i class="la la-trash text-danger fs-2x"></i>
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
                        ><a href="${pageContext.request.contextPath}/comissions?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/comissions?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/comissions?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
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
