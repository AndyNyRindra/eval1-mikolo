<%@ page import="com.eval1.models.laptop.Laptop" %>
<%@ page import="java.util.List" %>
<%@include file="../includes/layouts/default/top.jsp"%>
<%
    List<Laptop> laptops = (List<Laptop>) request.getAttribute("laptops");

%>


<head>
    <title>Mikolo - Renvois</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Ajout Renvois
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Renvois
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
                            <label>Reference :</label>
                            <input type="text" name="reference" class="form-control" required
                            >
                        </div>
                        <div class="mb-5">
                            <label>Date :</label>
                            <input type="date" name="date1" class="form-control" required
                            >
                        </div>

                        <div id="data">
                            <div class="form-group">
                                <div data-repeater-list="data">
                                    <div data-repeater-item>
                                        <div class="mb-5 row">
                                            <div class="col-2">
                                                <label>Ordinateur :</label>
                                                <select name="laptops" class="form-select"
                                                        data-control="select2" data-placeholder="Ordinateur"
                                                        data-allow-clear="true">
                                                    <option value="" >--Ordinateurs--</option>
                                                    <% for (Laptop laptop : laptops) { %>
                                                    <option value="<%= laptop.getId()%>" > <%= laptop.getName() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <div class="col-4">
                                                <label>Quantités :</label>
                                                <input class="form-control" type="number" name="quantities">
                                            </div>
                                            <div class="col-2">
                                                <button data-repeater-delete class="btn btn-sm btn-light-danger mt-3 mt-md-8" style="margin-top:  20px !important;" type="button">
                                                    <i class="la la-trash-o"></i>Supprimer
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-2">
                                <button data-repeater-create id="add-btn" class="btn btn-light-primary" type="button" id="add-scene">
                                    <i class="la la-plus"></i>Add
                                </button>
                            </div>
                        </div>



                        <p>
                            <input type="reset" value="Effacer" class="btn btn-reset">
                            <input type="submit" value="Ajouter" class="btn btn-primary">
                        </p>
                    </form>
                    <%@include file="/includes/scripts.jsp"%>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.repeater/1.2.1/jquery.repeater.min.js" integrity="sha512-foIijUdV0fR0Zew7vmw98E6mOWd9gkGWQBWaoA1EOFAx+pY+N8FmmtIYAVj64R98KeD2wzZh1aHK0JSpKmRH8w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

                    <script>


                        $("#data").repeater({
                            initEmpty: false,
                            defaultValues: {
                                'text-input': 'foo'
                            },
                            show: function () {
                                $(this).slideDown();
                                // redonner aux inputs leur nom original
                                $('#data').find('[name]').each(function () {
                                    var name = $(this).attr('name');
                                    $(this).attr('name',name.match(/\w+(?=]$)/)[0])
                                });
                            },
                            hide: function (deleteElement) {
                                $(this).slideUp(deleteElement);
                            },
                        })
                    </script>
                    <script>
                        const form = document.getElementById('form');

                        form.addEventListener('submit', function(evnt) {
                            evnt.preventDefault();
                            const formData = new FormData(form);
                            send(formData, "${pageContext.request.contextPath}/transfers", null)
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../includes/layouts/default/bottom.jsp"%>