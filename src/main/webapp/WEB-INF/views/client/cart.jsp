<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="title" content="Корзина || Alex Coffee">
    <meta name="robots" content="index,follow">
    <title>Корзина || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

<!-- CART -->
<div class="container-fluid width">
    <section id="cart">
        <div class="row cart">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow color-brown">
                    <b>
                        <span class="color-brown">Корзина</span>
                        <!-- EMPTY CART -->
                        <c:if test="${fn:length(sale_positions) eq 0}">
                            <span class="color-green"> - список пуст!</span>
                        </c:if>
                    </b>
                </div>
            </div>

            <!-- FULL CART -->
            <c:if test="${fn:length(sale_positions) gt 0}">

                <!-- PRODUCTS IN CART -->
                <jsp:include page="/WEB-INF/views/client/template/products_in_cart.jsp"/>

                <!-- ORDER REGISTRATION -->
                <jsp:include page="/WEB-INF/views/client/template/order_registration.jsp"/>

                <div class="row">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 text-center">
                        <a href="/#categories">
                            <button class="btn btn-success">Продолжить покупки</button>
                        </a>
                        <a href="/cart_clear">
                            <button class="btn btn-success">Очистить корзину</button>
                        </a>
                    </div>
                </div>
            </c:if>
        </div>
    </section>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
