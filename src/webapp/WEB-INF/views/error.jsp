<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Ошибка || Alex Coffee">
    <title>Ошибка || Alex Coffee</title>
</head>
<body class="background">

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/client_navbar.jsp"/>

<!-- ERROR -->
<div class="container-fluid width">
    <section id="error">
        <div class="row error text-shadow color-red">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                ${text_error}
            </div>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>
