
<head>
    <title>Mikolo</title>
    <!--begin::Fonts(mandatory for all pages)-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700">
    <!--end::Fonts-->

    <!--begin::Vendor Stylesheets(used for this page only)-->
    <link href="${pageContext.request.contextPath}/assets/plugins/custom/datatables/datatables.bundle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/plugins/custom/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css">
    <!--end::Vendor Stylesheets-->


    <!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
    <link href="${pageContext.request.contextPath}/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/css/style.bundle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/line_awesome.1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons-1.10.5/font/bootstrap-icons.css">
    <!--end::Global Stylesheets Bundle-->

    <!-- ckeditor -->
    <link href="${pageContext.request.contextPath}/assets/ckeditor/contents.css" rel="stylesheet" type="text/css">
    <%--end::ckeditor--%>
    <style>
        .scene-desc {
            display: flex;
        }
        .scene-desc-text {
            margin: auto 0;
            margin-left: 10px;
        }
        .search-container {
            position: relative;
        }
        .search-btn {
            width: unset !important;
            position: absolute;
            left: 100%;
            top: 28%;
            transform: translate(-120%, -28%);
        }
    </style>
</head>