<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="description" content="Весь ассортимент товаров интернет магазина кофе Alex Coffee"/>
    <meta name="keywords" content="Ассортимент кофе магазина Alex Coffee"/>
    <meta name="title" content="Весь ассортимент кофе || Alex Coffee">
    <title>Весь ассортимент кофе || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

<!-- All PRODUCTS -->
<jsp:include page="/WEB-INF/views/client/template/some_products.jsp"/>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
