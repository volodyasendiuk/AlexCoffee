<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="robots" content="index,follow">
    <meta name="google-site-verification" content="qiZQeYKdNTO5NVZQisl_gpnbTUCB89tSrwzSo99-fNo"/>
    <meta name="description"
          content="Alex Coffee - магазин вкусного и ароматного кофе для Вас и Вашех друзей. Кофе - любимый напиток цивилизованного мира."/>
    <meta name="keywords"
          content="alexcoffee, alex coffee, интернет, магазин, вкусный, аромтный, кофе, купить, куплю, в Киеве, в Украине, Киев, Украина"/>
    <meta name="title" content="Alex Coffee || Лучший магазин кофе">
    <title>Alex Coffee || Лучший магазин кофе</title>
    <script src="/resources/bootstrap/js/google_maps.js"></script>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/home_navbar.jsp"/>

<!-- MAIN PHOTO -->
<jsp:include page="/WEB-INF/views/client/template/main_photo.jsp"/>

<!-- CATEGORIES -->
<jsp:include page="/WEB-INF/views/client/template/categories.jsp"/>

<!--SOME PRODUCTS-->
<jsp:include page="/WEB-INF/views/client/template/some_products.jsp"/>

<!-- DELIVERY -->
<jsp:include page="/WEB-INF/views/client/template/delivery.jsp"/>

<!-- PAYMENTS -->
<jsp:include page="/WEB-INF/views/client/template/payments.jsp"/>

<!-- CONTACTS -->
<jsp:include page="/WEB-INF/views/client/template/contacts.jsp"/>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
