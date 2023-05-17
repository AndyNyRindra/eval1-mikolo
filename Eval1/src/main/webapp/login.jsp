<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<!--begin::Head-->

<head>
    <title>Mikolo - Connexion</title>
    <meta charset="utf-8"/>
    <!--begin::Fonts(mandatory for all pages)-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700"/>
    <!--end::Fonts-->

    <!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
    <link href="${pageContext.request.contextPath}/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/style.bundle.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css">
    <!--end::Global Stylesheets Bundle-->

</head>
<!--end::Head-->

<!--begin::Body-->

<body id="kt_body" class="app-blank">

<!--begin::Root-->
<div class="d-flex flex-column flex-root" id="kt_app_root">

    <!--begin::Authentication - Sign-in -->
    <div class="d-flex flex-column flex-lg-row flex-column-fluid">
        <!--begin::Aside-->
        <div class="d-flex flex-column flex-column-fluid flex-center w-lg-50 p-10">
            <!--begin::Wrapper-->
            <div class="d-flex justify-content-between flex-column-fluid flex-column w-100 mw-450px">
                <!--begin::Header-->
                <div class="d-flex flex-stack py-2">
                    <!--begin::Back link-->
                    <div class="me-2">

                    </div>
                    <!--end::Back link-->

                    <div class="m-0">
<%--                            <span class="text-gray-400 fw-bold fs-5 me-2" data-kt-translate="sign-in-head-desc">--%>
<%--                                Vous n'avez pas de compte ?--%>
<%--                            </span>--%>

<%--                        <a href="#" class="link-primary fw-bold fs-5" data-kt-translate="sign-in-head-link">--%>
<%--                            S'inscrire maintenant--%>
<%--                        </a>--%>
                    </div>
                    <!--end::Sign Up link--->

                </div>
                <!--end::Header-->

                <!--begin::Body-->
                <div class="py-20">

                    <!--begin::Form-->
                    <form class="form w-100" novalidate="novalidate" id="kt_sign_in_form" method="post" action="${pageContext.request.contextPath}/login">
                        <!--begin::Body-->
                        <div class="card-body">
                            <!--begin::Heading-->
                            <div class="text-start mb-10">
                                <!--begin::Title-->
                                <h1 class="text-dark mb-3 fs-3x" data-kt-translate="sign-in-title">
                                    Connexion
                                </h1>
                                <!--end::Title-->
                            </div>
                            <!--begin::Input group--->
                            <div class="fv-row mb-8">
                                <!--begin::Email-->
                                <input type="text" placeholder="Email" name="email" autocomplete="off"
                                       class="form-control form-control-solid"
                                       value="<% if(request.getAttribute("email") != null) {
                                             out.print(request.getAttribute("email"));
                                       } else { out.print("andy.nyr.rak@gmail.com"); }%>"/>
                                <!--end::Email-->
                            </div>

                            <!--end::Input group--->
                            <div class="fv-row mb-7">
                                <!--begin::Password-->
                                <input type="password" placeholder="Mot de passe" name="password" autocomplete="off"
                                       class="form-control form-control-solid" value="1234"
                                />
                                <!--end::Password-->
                            </div>

                            <!--begin::Actions-->
                            <div>
                                <!--begin::Submit-->
                                <button
                                    class="btn btn-primary me-2 flex-shrink-0 w-100 mb-4 kt_sign_in_submit"
                                    type="submit"
                                >
                                    <!--begin::Indicator label-->
                                    <span class="indicator-label" data-kt-translate="sign-in-submit">Se connecter</span>
                                </button>

                                <!--end::Submit-->
<%--                                <div class="m-0 mt-5">--%>
<%--                            <span class="text-gray-400 fw-bold fs-5 me-2" data-kt-translate="sign-in-head-desc">--%>
<%--                                Mot de passe oublié ?--%>
<%--                            </span>--%>

<%--                                    <a href="#" class="link-primary fw-bold fs-5" data-kt-translate="sign-in-head-link">--%>
<%--                                        Tenter quelque chose--%>
<%--                                    </a>--%>
<%--                                </div>--%>
                            </div>
                            <!--end::Actions-->
                        </div>
                        <!--begin::Body-->
                    </form>
                    <!--end::Form-->


                </div>
                <!--end::Body-->
                <div class="m-0">
                </div>
            </div>
            <!--end::Wrapper-->
        </div>
        <!--end::Aside-->

        <!--begin::Body-->
        <div class="d-none d-lg-flex flex-lg-row-fluid w-50 bgi-size-cover bgi-position-y-center bgi-position-x-start bgi-no-repeat"
             style="background-image: url(${pageContext.request.contextPath}/assets/media/auth/bg.jpg); background-position:right;">
        </div>
        <!--begin::Body-->
    </div>
    <!--end::Authentication - Sign-in--></div>
<!--end::Root-->
<%@include file="/includes/scripts.jsp" %>
<script>

    // let form;
    //
    // function doLog (url) {
    //     form.setAttribute('action', url);
    //     form.submit();
    // }

    document.addEventListener('DOMContentLoaded', function () {
        // Elements
        form = document.querySelector('#kt_sign_in_form');
        let submitButton = document.querySelector('.kt_sign_in_submit');
        let validator = FormValidation.formValidation(
            form,
            {
                fields: {
                    'email': {
                        validators: {
                            regexp: {
                                regexp: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                                message: 'Vueillez saisir une adresse email valide'
                            },
                            notEmpty: {
                                message: 'Une adresse email est requise'
                            }
                        }
                    },
                    'password': {
                        validators: {
                            notEmpty: {
                                message: 'Un mot de passe est requis'
                            }
                        }
                    }
                },
                plugins: {
                    trigger: new FormValidation.plugins.Trigger(),
                    bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: '.fv-row',
                        eleInvalidClass: '',  // comment to enable invalid state icons
                        eleValidClass: '' // comment to enable valid state icons
                    })
                }
            }
        )

        form.addEventListener('submit', function (e) {
            // Validate form
            validator.validate().then(function (status) {
                if (status == 'Valid') {
                    // Disable button to avoid multiple click
                    console.log(submitButton);
                    submitButton.disabled = true;
                } else {
                    e.preventDefault();
                }
            });
        });
    });
</script>
<!--start:error message script-->
<script>
    let error = '<%= request.getAttribute("error") %>';
    if (error !== null && error !== '' && error !== "null") {
        Swal.fire({
            text: error,
            icon: 'error',
            confirmButtonText: 'OK'
        })
    }
</script>
<!--end:error message script-->
</body>
<!--end::Body-->

</html>