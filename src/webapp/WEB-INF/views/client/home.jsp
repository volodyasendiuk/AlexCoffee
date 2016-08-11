<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
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
<jsp:include page="/WEB-INF/views/template/home_navbar.jsp"/>

<!-- MAIN PHOTO -->
<div class="container-fluid width">
    <section id="main">
        <div class="row main text-shadow">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <span class="main-text-label color-green">Alex</span>
                <span class="main-text-label color-brown">Coffee</span>
                <h1>
                    <!--
                    <img class="main-label-test label-main" src="/resources/img/main_big_icon_test.png"
                         alt="Alex Coffee || Лучший магазин кофе">
                     -->
                    <img class="main-label label-main" src="/resources/img/main_big_icon.png"
                         alt="Alex Coffee || Лучший магазин кофе">
                </h1>
                <span class="main-text-label color-green">Лучший магазин</span>
                <span class="main-text-label color-brown"> кофе</span>
            </div>
        </div>
    </section>
</div>

<!-- CATEGORIES -->
<div class="container-fluid width">
    <section id="categories">
        <div class="row categories">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-categories">
                    <img src="/resources/img/section_icon_1.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Категории</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                        <c:if test="${fn:length(categories) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                        </c:if>
                    </div>
                </h3>
            </div>

            <c:if test="${fn:length(categories) gt 0}">
                <c:forEach items="${categories}" var="category">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                        <div class="category">
                            <a href="/category_${category.url}" title="Перейти к категории ${category.title}">
                                <img src="/resources/img/${category.photo.photoLinkShort}"
                                     class="img-thumbnail blink" width="150px" height="150px"
                                     alt="${category.title}">
                                <div class="text-shadow">${category.title}</div>
                            </a>
                        </div>
                    </div>
                </c:forEach>

                <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <h4 class="text-all-products text-shadow">
                        <a href="/all_products" title="Перейти ко всем товарам">Весь ассортимент кофе</a>
                    </h4>
                </div>
            </c:if>
        </div>
    </section>
</div>

<!--SOME PRODUCTS-->
<div class="container-fluid width">
    <section id="all-products">
        <div class="row all-products">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-all-products">
                    <img src="/resources/img/section_icon_2.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Наши</span>
                        <span class="home-block-name color-brown"> товары</span>
                        <c:if test="${fn:length(products) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                        </c:if>
                    </div>
                </h3>
            </div>

            <c:if test="${fn:length(products) gt 0}">
                <c:forEach items="${products}" var="product">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                        <div class="product">
                            <a href="/product_${product.url}" title="Перейти к ${product.title}">
                                <img src="/resources/img/${product.photo.photoLinkShort}"
                                     class="img-thumbnail blink" width="185px" height="185px"
                                     alt="${product.title}">
                                <div class="text-shadow">${product.title}</div>

                                <p class="price-top">
                                    <fmt:formatNumber type="number" value="${product.price}"/> грн
                                </p>
                            </a>
                            <form action="/cart_add_quickly" method="post">
                                <input type="hidden" name="id" value="${product.id}">
                                <input type="hidden" name="url" value="/">
                                <p class="text" title="Добавить ${product.title} в корзину">
                                    <button class="btn btn-success" type="submit">Добавить в корзину</button>
                                </p>
                            </form>
                        </div>
                    </div>
                </c:forEach>

                <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <h4 class="text-all-products text-shadow">
                        <a href="/all_products" title="Перейти ко всем товарам">Весь ассортимент кофе</a>
                    </h4>
                </div>
            </c:if>
        </div>
    </section>
</div>

<!-- DELIVERY -->
<div class="container-fluid width">
    <section id="delivery">
        <div class="row delivery">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-delivery">
                    <img src="/resources/img/section_icon_3.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Доставка</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                    </div>
                </h3>
            </div>

            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-car fa-5x color-green" id="icon1"></i>
                <p class="icon-text">Курьер по адресу <br>Киев</p>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-truck fa-5x color-green" id="icon2"></i>
                <p class="icon-text">Новая Почта<br>Украина</p>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-shopping-cart fa-5x color-green" id="icon3"></i>
                <p class="icon-text">Самовывоз <br>Киев</p>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h4 class="text-delivery">
                    Стоимость курьевской доставки по Киеву и отправка Новой Почтой - 35 грн<br>
                    Мы находимся по адресу г.Киев, ул. Михаила Ломоносова 55
                </h4>
            </div>
        </div>
    </section>
</div>

<!-- PAYMENTS -->
<div class="container-fluid width">
    <section id="payments">
        <div class="row payments">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-payments">
                    <img src="/resources/img/section_icon_4.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Оплата</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                    </div>
                </h3>
            </div>

            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-money fa-5x color-green" id="icon4"></i>
                <p class="icon-text">Оплата курьеру</p>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-truck fa-5x color-green" id="icon5"></i>
                <p class="icon-text">При получении на <br>Новой Почте</p>
            </div>
            <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-cc-visa fa-5x color-green" id="icon6"></i>
                <p class="icon-text">Карта ПриватБанка</p>
            </div>
            <div class="col-xs-8 col-xs-offset-2 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
                <h4 class="text-payments">
                    Возврат приобретенных товаров осуществляется в случаях и согласно условиям, регламентированным
                    «Законом Украины о защите прав потребителей»
                </h4>
            </div>
        </div>
    </section>
</div>

<!-- GOOGLE EARTH -->
<div class="container-fluid width">
    <section id="contacts">
        <div class="row google-map intro-text label-cantacts">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3>
                    <img src="/resources/img/section_icon_5.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Контакты</span>
                    </div>
                </h3>

                <div id="google-map"></div>
            </div>
        </div>
    </section>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>
