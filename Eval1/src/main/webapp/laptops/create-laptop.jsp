<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.Role" %>
<%@ page import="com.eval1.models.shop.view.ShopInput" %>
<%@ page import="com.eval1.models.laptop.view.LaptopForm" %>
<%@ page import="com.eval1.models.cpu.Cpu" %>
<%@ page import="com.eval1.models.ram.RamType" %>
<%@ page import="com.eval1.models.screen.ScreenType" %>
<%@ page import="com.eval1.models.brand.Brand" %>
<%@ page import="com.eval1.models.drive.DriveType" %>
<%@include file="../includes/layouts/default/top.jsp"%>

<%
  LaptopForm laptopForm = (LaptopForm) request.getAttribute("laptopForm");
%>

<head>
  <title>Mikolo - Ordinateurs</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
  <!--begin::toolbar-->
  <div class="app-toolbar py-3 py-lg-6">
    <div class="app-container container-xxl d-flex flex-stack">
      <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
        <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
          Ajout d'ordinateur
        </h1>
        <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
          <li class="breadcrumb-item text-muted">
            Type d'ordinateur
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
              <input type="text" name="name" class="form-control" required>
            </div>
            <div class="mb-5">
              <label>Reference :</label>
              <input type="text" name="reference" class="form-control" required>
            </div>
            <div class="mb-5">
              <label>Processeur :</label>
              <select name="cpuId" class="form-select"
                      data-control="select2" data-placeholder="Processeur"
                      data-allow-clear="true" required>
                <option value="" >--Processeurs--</option>
                <% for (Cpu cpu : laptopForm.getCpus()
                ) { %>
                <option value="<%= cpu.getId() %>"

                > <%= cpu.getName() %> - <%= cpu.getFrequency() %> GHz </option>
                <% } %>
              </select>
            </div>
            <div class="mb-5">
              <label>Marque :</label>
              <select name="brandId" class="form-select"
                      data-control="select2" data-placeholder="Marque"
                      data-allow-clear="true" required>
                <option value="" >--Marques--</option>
                <% for (Brand brand : laptopForm.getBrands()
                ) { %>
                <option value="<%= brand.getId() %>"

                > <%= brand.getName() %></option>
                <% } %>
              </select>
            </div>
            <div class="row">
              <div class="col-sm-6 mb-5">
                <label>Type de ram :</label>
                <select name="ramTypeId" class="form-select"
                        data-control="select2" data-placeholder="Type de ram"
                        data-allow-clear="true" required>
                  <option value="" >--Types de rams--</option>
                  <% for (RamType ramType : laptopForm.getRamTypes()
                  ) { %>
                  <option value="<%= ramType.getId() %>"

                  > <%= ramType.getName() %></option>
                  <% } %>
                </select>
              </div>
              <div class="col-sm-6 mb-5">
                <label>Taille ram (en Go) :</label>
                <input type="text" name="ramValue" class="form-control" required>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-6 mb-5">
                <label>Type d'écran :</label>
                <select name="screenTypeId" class="form-select"
                        data-control="select2" data-placeholder="Type d'écran"
                        data-allow-clear="true" required>
                  <option value="" >--Type d'écran--</option>
                  <% for (ScreenType screenType : laptopForm.getScreenTypes()
                  ) { %>
                  <option value="<%= screenType.getId() %>"

                  > <%= screenType.getName() %></option>
                  <% } %>
                </select>
              </div>
              <div class="col-sm-6 mb-5">
                <label>Taille écran (en pouces) :</label>
                <input type="text" name="screenSize" class="form-control" required>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-6 mb-5">
                <label>Type de disque :</label>
                <select name="driverTypeId" class="form-select"
                        data-control="select2" data-placeholder="Type de disque"
                        data-allow-clear="true" required>
                  <option value="" >--Type de disque--</option>
                  <% for (DriveType driveType : laptopForm.getDriveTypes()
                  ) { %>
                  <option value="<%= driveType.getId() %>"

                  > <%= driveType.getName() %></option>
                  <% } %>
                </select>
              </div>
              <div class="col-sm-6 mb-5">
                <label>Taille disque (en Mo) :</label>
                <input type="text" name="driverSize" class="form-control" required>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-6 mb-5">
                <label>Prix :</label>
                <input type="text" name="price" class="form-control" required>
              </div>
              <div class="col-sm-6 mb-5">
                <label>Prix de vente :</label>
                <input type="text" name="sellingPrice" class="form-control" required>
              </div>
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
              send(formData, "${pageContext.request.contextPath}/laptops", "${pageContext.request.contextPath}/laptops")
            });
          </script>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../includes/layouts/default/bottom.jsp"%>
