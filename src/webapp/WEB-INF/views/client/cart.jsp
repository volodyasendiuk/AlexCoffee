<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="title" content="Корзина || Alex Coffee">
    <meta name="robots" content="index,follow">
    <title>Корзина || Alex Coffee</title>
</head>
<body class="background">

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/client_navbar.jsp"/>

<!-- CART -->
<div class="container-fluid width">
    <section id="cart">
        <div class="row cart">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow color-brown">
                    <b>
                        <span class="color-brown">Корзина</span>
                        <!-- EMPTY CART -->
                        <c:if test="${fn:length(products) eq 0}">
                            <span class="color-green"> - список пуст!</span>
                        </c:if>
                    </b>
                </div>
            </div>

            <!-- FULL CART -->
            <c:if test="${fn:length(products) gt 0}">
                <div class="row">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                        <table class="table cart-table">
                            <tr>
                                <td><b>Название</b></td>
                                <td><b>Фото</b></td>
                                <td class="hidden-xs"><b>Категория</b></td>
                                <td><b>Стоимость</b></td>
                            </tr>
                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <td>
                                        <a href="/product_${product.url}"
                                           title="Перейти к ${product.title}">
                                                ${product.title}</a>
                                    </td>
                                    <td>
                                        <img width="50px" height="50px" src="/resources/${product.photo.photoLinkShort}"
                                             alt="${product.title}">
                                    </td>
                                    <td class="hidden-xs">
                                        <a href="/category_${product.category.url}"
                                           title="Перейти к категории ${product.category.title}">
                                                ${product.category.title}</a>
                                    </td>
                                    <td>
                                        <fmt:formatNumber type="number" value="${product.price}"/> грн
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td class="hidden-xs"></td>
                                <td></td>
                                <td class="hidden-xs"></td>
                                <td style="text-align: right;"><strong>Итого:</strong></td>
                                <td>
                                    <strong>
                                        <fmt:formatNumber type="number" value="${price_of_cart}"/> грн
                                    </strong>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>

                <!-- ORDER REGISTRATION -->
                <div class="row">
                    <form action="/checkout" method="post">
                        <div class="col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-2 col-lg-2 col-lg-offset-2 col-xl-2 col-xl-offset-2 input-padding text-center">
                            <input class="input" type="text" name="user_name" placeholder=" Введите имя"
                                   minlength="2" maxlength="50" required autofocus>
                        </div>
                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
                            <input id="phone" class="input" type="text" name="user_phone" placeholder=" Введите телефон"
                                   required>
                        </div>
                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
                            <input class="input" type="email" name="user_email" placeholder=" Введите Email"
                                   minlength="5" maxlength="50">
                        </div>
                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
                            <input type="submit" class="btn btn-success" value="Оформить заказ" width="80px">
                        </div>
                    </form>
                </div>

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
<jsp:include page="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>