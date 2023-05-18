<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.laptop.Laptop" %>
<%@ page import="com.eval1.models.laptop.LaptopFilter" %>
<%@ page import="com.eval1.models.laptop.view.LaptopForm" %>
<%@ page import="com.eval1.models.brand.Brand" %>
<%@ page import="com.eval1.models.cpu.Cpu" %>
<%@ page import="com.eval1.models.drive.DriveType" %>
<%@ page import="com.eval1.models.ram.RamType" %>
<%@ page import="com.eval1.models.screen.ScreenType" %>

<%@include file="../includes/layouts/default/top.jsp"%>
<%
    ListResponse laptops = (ListResponse) request.getAttribute("laptops");
    List<Laptop> laptopList = (List<Laptop>) laptops.getElements();
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    LaptopForm laptopForm = (LaptopForm) request.getAttribute("laptopForm");
    LaptopFilter laptopFilter = (LaptopFilter) request.getAttribute("laptopFilter");
    String filters = "";
    if (laptopFilter != null) {
        filters = laptopFilter.getFilterConditions();
    }
%>

<style>
    .modal-dialog {
        max-width: 900px !important;
    }
</style>

<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Ordinateurs
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Ordinateurs
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
            <form id="search-form" method="get">
                <div class="card card-flush mb-5">


                    <!--begin::card header-->
                    <div class="card-header align-items-center py-0 gap-2">
                        <div class="card-toolbar flex-row-fluid justify-content-end gap-5" data-select2-id="select2-data-123-mzxj">
                            <!--begin::Add product-->
                            <a href="${pageContext.request.contextPath}/laptops/create" class="btn btn-success">
                                Ajouter un ordinateur
                            </a>
                            <!--end::Add product-->
                        </div>
                    </div>
                    <!--end::card header-->

                    <!--begin::card body-->

                    <div class="card-body pt-0">
                        <div class="accordion" id="accordion-1">

                            <div class="mb-5">

                                <div class="row">
                                    <div class="col-md-10">
                                        <div class="row search-container">
                                            <div class="col-md-12 col-sm-8 mb-5">
                                                <input type="text" name="keyWord" class="form-control" placeholder="Recherche..."
                                                    <% if(laptopFilter != null && laptopFilter.getKeyWord() != null) { %>
                                                       value="<%=laptopFilter.getKeyWord()%>"
                                                    <% } %>
                                                >
                                            </div>
                                            <button style="border: none; background-color: transparent; font-size: 20px !important;" class="bi bi-search fs-2qx search-btn" type="submit">
                                            </button>
                                        </div>
                                    </div>

                                    <div class="col-md-2 col-sm-2">
                                        <button type="button" class="btn btn-primary w-100" data-bs-target="#search-modal" data-bs-toggle="modal">Plus</button>
                                    </div>
                                </div>

                            </div>



                            <div class="mb-5">
                                <div class="row">
                                    <div class="col-md-5 col-sm-12">
                                        <select name="field" class="form-select"
                                                data-control="select2" data-placeholder="Colonne"
                                                data-allow-clear="true">
                                            <option value="">--Colonne--</option>
                                            <% for (String field: laptopForm.getFields().keySet()) { %>
                                            <option value="<%=field%>"
                                                    <% if(laptopFilter != null && laptopFilter.getField() != null && laptopFilter.getField().equals(field)) { %>
                                                    selected
                                                    <% } %>
                                            ><%=laptopForm.getFields().get(field)%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="col-md-5 col-sm-12">
                                        <select name="method" class="form-select"
                                                data-control="select2" data-placeholder="Ordre"
                                                data-allow-clear="true">
                                            <option value="">--Ordre--</option>
                                            <% for (String method: laptopForm.getMethods().keySet()) { %>
                                            <option value="<%=method%>"
                                                    <% if(laptopFilter != null && laptopFilter.getMethod() != null && laptopFilter.getMethod().toString() == method) { %>
                                                    selected
                                                    <% } %>
                                            ><%=laptopForm.getMethods().get(method)%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <button class="btn btn-primary w-100" type="submit">
                                            Valider
                                        </button>
                                    </div>
                                </div>

                            </div>
                            <!--begin::table-->


                        </div>
                        <!--end::card body-->
                    </div>
                    <!--end::card-->
                </div>

                <div class="modal fade" tabindex="-1" id="search-modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Filtrer</h3>

                                <!--begin::Close-->
                                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
                                    <i class="fa-solid fa-xmark fs-1"></i>
                                </div>
                                <!--end::Close-->
                            </div>

                            <div class="modal-body">

                                <div class="row">
                                    <label class="mb-5">Marques :</label>

                                        <% for (Brand brand: laptopForm.getBrands()) { %>
                                    <div class="col-sm-3 mb-5">
                                        <input class="form-check-input" type="checkbox" value="<%=brand.getId()%>" id="brand<%=brand.getId()%>" name="brands"
                                                <% if(laptopFilter != null && laptopFilter.getBrands() != null) { %>
                                                <% for (Long brandFilter: laptopFilter.getBrands()) { %>
                                                <% if(brandFilter == brand.getId()) { %>
                                               checked
                                                <% } %>
                                                <% } %>
                                                <% } %>
                                        />
                                        <label class="form-check-label" for="brand<%=brand.getId()%>" >
                                            <%=brand.getName()%>
                                        </label>
                                    </div>
                                        <% } %>

                                </div>

                                <div class="row">
                                    <label class="mb-5">Processeurs :</label>

                                    <% for (Cpu cpu: laptopForm.getCpus()) { %>
                                    <div class="col-sm-3 mb-5">
                                        <input class="form-check-input" type="checkbox" value="<%=cpu.getId()%>" id="cpu<%=cpu.getId()%>" name="cpus"
                                                <% if(laptopFilter != null && laptopFilter.getCpus() != null) { %>
                                                <% for (Long cpuFilter: laptopFilter.getCpus()) { %>
                                                <% if(cpuFilter == cpu.getId()) { %>
                                               checked
                                                <% } %>
                                                <% } %>
                                                <% } %>
                                        />
                                        <label class="form-check-label" for="cpu<%=cpu.getId()%>" >
                                            <%=cpu.getName()%>
                                        </label>
                                    </div>
                                    <% } %>

                                </div>

                                <div class="row">
                                    <label class="mb-5">Stockages :</label>

                                    <% for (DriveType driveType: laptopForm.getDriveTypes()) { %>
                                    <div class="col-sm-3 mb-5">
                                        <input class="form-check-input" type="checkbox" value="<%=driveType.getId()%>" id="drive<%=driveType.getId()%>" name="drives"
                                                <% if(laptopFilter != null && laptopFilter.getDrives() != null) { %>
                                                <% for (Long driveFilter: laptopFilter.getDrives()) { %>
                                                <% if(driveFilter == driveType.getId()) { %>
                                               checked
                                                <% } %>
                                                <% } %>
                                                <% } %>
                                        />
                                        <label class="form-check-label" for="drive<%=driveType.getId()%>" >
                                            <%=driveType.getName()%>
                                        </label>
                                    </div>
                                    <% } %>

                                </div>

                                <div class="row">
                                    <label class="mb-5">Rams :</label>

                                    <% for (RamType ramType: laptopForm.getRamTypes()) { %>
                                    <div class="col-sm-3 mb-5">
                                        <input class="form-check-input" type="checkbox" value="<%=ramType.getId()%>" id="ram<%=ramType.getId()%>" name="rams"
                                                <% if(laptopFilter != null && laptopFilter.getRams() != null) { %>
                                                <% for (Long ramFilter: laptopFilter.getRams()) { %>
                                                <% if(ramFilter == ramType.getId()) { %>
                                               checked
                                                <% } %>
                                                <% } %>
                                                <% } %>
                                        />
                                        <label class="form-check-label" for="ram<%=ramType.getId()%>" >
                                            <%=ramType.getName()%>
                                        </label>
                                    </div>
                                    <% } %>

                                </div>

                                <div class="row">
                                    <label class="mb-5">Ecrans :</label>

                                    <% for (ScreenType screenType: laptopForm.getScreenTypes()) { %>
                                    <div class="col-sm-3 mb-5">
                                        <input class="form-check-input" type="checkbox" value="<%=screenType.getId()%>" id="screen<%=screenType.getId()%>" name="screens"
                                                <% if(laptopFilter != null && laptopFilter.getScreens() != null) { %>
                                                <% for (Long screenFilter: laptopFilter.getScreens()) { %>
                                                <% if(screenFilter == screenType.getId()) { %>
                                               checked
                                                <% } %>
                                                <% } %>
                                                <% } %>
                                        />
                                        <label class="form-check-label" for="screen<%=screenType.getId()%>" >
                                            <%=screenType.getName()%>
                                        </label>
                                    </div>
                                    <% } %>

                                </div>


                                <div class="row">
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Ram minimum :</label>
                                        <input type="text" name="minRam" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMinRam() != null) { %>
                                               value="<%=laptopFilter.getMinRam()%>"
                                            <% } %>
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Ram maximum :</label>
                                        <input type="text" name="maxRam" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMaxRam() != null) { %>
                                               value="<%=laptopFilter.getMaxRam()%>"
                                            <% } %>
                                        >
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Ecran minimum :</label>
                                        <input type="text" name="minScreen" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMinScreen() != null) { %>
                                               value="<%=laptopFilter.getMinScreen()%>"
                                            <% } %>
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Ecran maximum :</label>
                                        <input type="text" name="maxScreen" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMaxScreen() != null) { %>
                                               value="<%=laptopFilter.getMaxScreen()%>"
                                            <% } %>
                                        >
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Stockage minimum :</label>
                                        <input type="text" name="minDrive" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMinDrive() != null) { %>
                                               value="<%=laptopFilter.getMinDrive()%>"
                                            <% } %>
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Stockage maximum :</label>
                                        <input type="text" name="maxDrive" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMaxDrive() != null) { %>
                                               value="<%=laptopFilter.getMaxDrive()%>"
                                            <% } %>
                                        >
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Frequence CPU minimum :</label>
                                        <input type="text" name="minCpu" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMinCpu() != null) { %>
                                               value="<%=laptopFilter.getMinCpu()%>"
                                            <% } %>
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Frequence CPU maximum :</label>
                                        <input type="text" name="maxCpu" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMaxCpu() != null) { %>
                                               value="<%=laptopFilter.getMaxCpu()%>"
                                            <% } %>
                                        >
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Prix d'achat minimum :</label>
                                        <input type="text" name="mineq_price" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMineq_price() != null) { %>
                                               value="<%=laptopFilter.getMineq_price()%>"
                                            <% } %>
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Prix d'achat maximum :</label>
                                        <input type="text" name="maxCpu" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMaxeq_price() != null) { %>
                                               value="<%=laptopFilter.getMaxeq_price()%>"
                                            <% } %>
                                        >
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Prix de vente minimum :</label>
                                        <input type="text" name="mineq_sellingPrice" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMineq_sellingPrice() != null) { %>
                                               value="<%=laptopFilter.getMineq_sellingPrice()%>"
                                            <% } %>
                                        >
                                    </div>
                                    <div class="col-md-6 col-sm-12 mb-5">
                                        <label>Prix de vente maximum :</label>
                                        <input type="text" name="maxeq_sellingPrice" class="form-control"
                                            <% if(laptopFilter != null && laptopFilter.getMaxeq_sellingPrice() != null) { %>
                                               value="<%=laptopFilter.getMaxeq_sellingPrice()%>"
                                            <% } %>
                                        >
                                    </div>
                                </div>
                                <button class="btn btn-primary" type="submit">
                                    Filtrer
                                </button>

                            </div>


                        </div>
                    </div>
                </div>
            </form>
            <% for(Laptop laptop : laptopList) { %>
            <div class="card mb-5">
                <div class="card-body pt-5 pb-1 px-1" style="margin-left: 50px">
                    <div class="row">
                        <div class="col-md-7 col-sm-7 mt-5">
                            <h2><b class="card-text col-md-10 col-sm-10" style="margin-top: 20px; color: green"><%=laptop.getBrand().getName()%></b> - <%=laptop.getName()%> - <%=laptop.getReference()%></h2>

                        </div>

                        <div class="col-md-1 col-sm-2" style="position: absolute; top:0 ; right : 0" >
                            <button type="button" style="border: none; background-color: transparent;margin-top: 20px" class="la la-ellipsis-v fs-2x" data-kt-menu-trigger="click" data-kt-menu-placement="bottom-start" >

                            </button>
                            <div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg-light-primary fw-semibold w-200px" data-kt-menu="true">
                                <!--begin::Menu item-->

                                <div class="menu-item px-3">
                                    <a href="${pageContext.request.contextPath}/laptops/update/<%=laptop.getId()%>" class="menu-link px-3">
                                        Modifier
                                    </a>
                                </div>
                                <div class="menu-item px-3">
                                    <a href="#" class="menu-link px-3" data-bs-target="#delete-modal" data-bs-toggle="modal" onclick="onDeleteButtonClicked(<%=laptop.getId()%>, '<%=laptop.getReference()%>', '${pageContext.request.contextPath}/laptops/delete/<%=laptop.getId()%>', 'l\'ordinateur')">
                                        Supprimer
                                    </a>
                                </div>

<%--                                <div class="menu-item px-3">--%>
<%--                                    <a href="${pageContext.request.contextPath}/scene/<%=scene.getId()%>/actions" class="menu-link px-3">--%>
<%--                                        Voir details--%>
<%--                                    </a>--%>
<%--                                </div>--%>
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
                                        <b>Acheté à:</b> <%=laptop.getPrice()%> Ar - <b>Vendu à:</b> <%=laptop.getSellingPrice()%> Ar
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-10 col-sm-10 mt-5">

                            <div class="row mb-5">
                                <div class="col-md-10 col-sm-10">
                                    <div class="scene-desc">
                                        <div class="scene-desc-icon">
                                            <i class="la la-sim-card text-gray-900 fs-1"></i>
                                        </div>
                                        <div class="scene-desc-text">
                                            <b>CPU:</b> <%=laptop.getCpu().getName()%> - <%=laptop.getCpu().getFrequency()%> GHz
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-5">
                                <div class="col-md-10 col-sm-10">
                                    <div class="scene-desc">
                                        <div class="scene-desc-icon">
                                            <i class="la la-memory text-gray-900 fs-1"></i>
                                        </div>
                                        <div class="scene-desc-text">
                                            <b>Ram: </b> <%=laptop.getRamType().getName()%> - <%=laptop.getRamValue()%> Go
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-5 ">
                                <div class="col-md-10 col-sm-10">
                                    <div class="scene-desc">
                                        <div class="scene-desc-icon">
                                            <i class="la la-television text-gray-900 fs-1"></i>
                                        </div>
                                        <div class="scene-desc-text">
                                            <b>Ecran: </b> <%=laptop.getScreenType().getName()%> - <%=laptop.getScreenSize()%> "
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-5">
                                <div class="col-md-10 col-sm-10">
                                    <div class="scene-desc">
                                        <div class="scene-desc-icon">
                                            <i class="la la-compact-disc text-gray-900 fs-1"></i>
                                        </div>
                                        <div class="scene-desc-text">
                                            <b>Stockage: </b> <%=laptop.getDriverType().getName()%> - <%=laptop.getDriverSize()%> Go
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <% } %>




            <!--end::table-->
            <ul class="pagination">
                <li
                        <% if (pageNumber == 1) { %>
                        class="page-item previous disabled"
                        <% } else { %>
                        class="page-item previous"
                        <% } %>
                ><a href="${pageContext.request.contextPath}/laptops?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                <li
                        <% if (pageNumber == i) { %>
                        class="page-item active"
                        <% } else { %>
                        class="page-item"
                        <% } %>
                ><a href="${pageContext.request.contextPath}/laptops?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                <% } %>
                <li
                        <% if (pageNumber == requiredPages || requiredPages == 0) { %>
                        class="page-item next disabled"
                        <% } else { %>
                        class="page-item next"
                        <% } %>
                ><a href="${pageContext.request.contextPath}/laptops?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
            </ul>

        </div>
        <!--end:content-->
    </div>

    <%@include file="/includes/scripts.jsp"%>

    <script>

        const error = "<%= request.getParameter("error") %>";
        console.log(error);

        if (error !== null && error !== '' && error!=="null") {

            Swal.fire({
                text: error,
                icon: 'error',
                confirmButtonText: 'OK'
            })
        }
    </script>
    <script>

        $(document).ready(function() {
            $("#status").select2({
                dropdownParent: $("#search-modal .modal-content")
            });
            $("#movieSets").select2({
                dropdownParent: $("#search-modal .modal-content")
            });
            $("#authors").select2({
                dropdownParent: $("#search-modal .modal-content")
            });
        });

    </script>
    <!--end::main-->
    <%@include file="../includes/delete.jsp"%>   <!--end::main-->
    <script src="${serverUrl}/assets/custom/elementDelete.js"></script>
    <%@include file="../includes/layouts/default/bottom.jsp"%>
