
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

<body id="kt_body" class="app-blank">

<%@include file="/includes/scripts.jsp" %>
<script>
    Swal.fire({
      text: "Vous n'avez pas les droits pour accéder à cette page, veuillez vous connecter",
      icon: 'error',
      confirmButtonText: 'OK',
      showCancelButton: true,
      cancelButtonText: 'Annuler',
      allowEscapeKey: false,
      allowOutsideClick: false,
      allowEnterKey: false,
    }).then((result) => {
      if (result.isConfirmed) {
        window.location.href = "${pageContext.request.contextPath}/login";
      }
      else {
        history.back();
      }
    });
</script>

<!--end:error message script-->
</body>
<!--end::Body-->

</html>