<%@ page import="com.eval1.models.LoggedUser" %>

<%
    LoggedUser user = (LoggedUser) session.getAttribute("connected");
%>

<div id="kt_app_header" class="app-header " >

    <!--begin::Header container-->
    <div class="app-container  container-fluid d-flex align-items-stretch justify-content-between "
         id="kt_app_header_container" >
        <div class="d-flex align-items-center d-lg-none ms-n3 me-1 me-md-2" title="Show sidebar menu">
            <div class="btn btn-icon btn-active-color-primary w-35px h-35px" id="kt_app_sidebar_mobile_toggle">
                <i class="fa-solid fa-bars fs-2 fs-md-1"></i>
            </div>
        </div>
        <!--begin::Header wrapper-->
        <div class="d-flex align-items-stretch justify-content-between flex-lg-grow-1" id="kt_app_header_wrapper"
             >
            <!--begin::Menu wrapper-->
            <div class="app-header-menu app-header-mobile-drawer align-items-stretch" data-kt-drawer="true"
                 data-kt-drawer-name="app-header-menu" data-kt-drawer-activate="{default: true, lg: false}"
                 data-kt-drawer-overlay="true" data-kt-drawer-width="250px" data-kt-drawer-direction="end"
                 data-kt-drawer-toggle="#kt_app_header_menu_toggle" data-kt-swapper="true"
                 data-kt-swapper-mode="{default: 'append', lg: 'prepend'}"
                 data-kt-swapper-parent="{default: '#kt_app_body', lg: '#kt_app_header_wrapper'}" >
                <!--begin::Menu-->
                <div class="menu menu-rounded menu-column menu-lg-row my-5 my-lg-0 align-items-stretch fw-semibold px-2 px-lg-0"
                     id="kt_app_header_menu" data-kt-menu="true">
                    <!--begin:Menu item-->
                    <div class="menu-item me-0 me-lg-2">
                        <!--begin:Menu link-->
                        <a href="${pageContext.request.contextPath}/" class="menu-link"><span class="menu-title">Accueil</span>
                        </a>
                        <!--begin:Menu item-->
                    </div>
                </div>
                <!--end::Menu-->
            </div>
                <!--end::Menu wrapper-->

                <!--begin::Navbar-->
                <div class="app-navbar flex-shrink-0" >

                    <!--begin::User menu-->
                    <div class="app-navbar-item ms-1 ms-md-3" id="kt_header_user_menu_toggle" >
                        <span style="margin-right: 10px" ><%= user.getName() %></span>
                        <!--begin::Menu wrapper-->
                        <div class="cursor-pointer symbol symbol-30px symbol-md-40px"
                             data-kt-menu-trigger="{default: 'click', lg: 'hover'}" data-kt-menu-attach="parent"
                             data-kt-menu-placement="bottom-end" >
                            <img src="https://icon-library.com/images/avatar-icon-images/avatar-icon-images-4.jpg" alt="user">
                        </div>
                        <!--end::Menu wrapper-->
                    </div>
                    <!--end::User menu-->
                </div>
                <!--end::Navbar-->
            </div>
            <!--end::Header wrapper-->
    </div>
</div>