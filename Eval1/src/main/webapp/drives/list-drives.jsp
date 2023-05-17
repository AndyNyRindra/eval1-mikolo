<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.shop.Shop" %>
<%@ page import="com.eval1.models.shop.ShopFilter" %>
<%@ page import="com.eval1.models.Role" %>
<%@ page import="com.eval1.models.brand.Brand" %>
<%@ page import="com.eval1.models.brand.BrandFilter" %>
<%@ page import="com.eval1.models.ram.RamType" %>
<%@ page import="com.eval1.models.ram.RamTypeFilter" %>
<%@ page import="com.eval1.models.drive.DriveType" %>
<%@ page import="com.eval1.models.drive.DriveFilter" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    ListResponse drives = (ListResponse) request.getAttribute("driveTypes");
    List<DriveType> driveTypes = (List<DriveType>) drives.getElements();
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    DriveFilter driveTypeFilter = (DriveFilter) request.getAttribute("driveFilter");
    String filters = "";
    if (driveTypeFilter != null) {
        filters = driveTypeFilter.getFilterConditions();
    }
%>
<head>
    <title>Mikolo - Disques</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Types de disques
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Types de disques
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
                        <a href="${pageContext.request.contextPath}/drives/create" class="btn btn-success">
                            Ajouter un type de disque
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
                                <input id="name" type="text" name="name" class="form-control" placeholder="Nom..."
                                    <% if (driveTypeFilter != null && driveTypeFilter.getName() != null) { %>
                                       value="<%=driveTypeFilter.getName().replace("%", "")%>"
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
                            <th>Nom</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(DriveType driveType : driveTypes) { %>
                        <tr>
                            <td><%=driveType.getId()%></td>
                            <td>
                                <%= driveType.getName() %>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/drives/update/<%= driveType.getId() %>" >
                                    <i class="la la-pencil text-warning fs-2x"></i>
                                </a>
                            </td>
                            <td>
                                <a href="#" onclick="onDeleteButtonClicked(<%= driveType.getId() %>, '<%= driveType.getName() %>', '${pageContext.request.contextPath}/drives/delete/<%=driveType.getId()%>', 'le type de disque')"
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
                        ><a href="${pageContext.request.contextPath}/drives?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/drives?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/drives?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
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
