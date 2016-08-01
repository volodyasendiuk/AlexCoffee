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
          content="Вкусный и ароматный кофе для Вас и Вашех друзей. Кофе - любимый напиток цивилизованного мира."/>
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
<section id="main">
    <div class="container-fluid hidden-xs">
        <div class="row main">
            <div class="text-shadow">
                <span class="main-text-label color-green">Alex</span>
                <span class="main-text-label color-brown">Coffee</span>
                <h2>
                    <img class="main-label label-main" src="/resources/img/main_big_icon.png"
                         alt="Alex Coffee || Лучший магазин кофе">
                </h2>
                <span class="main-text-label color-green">Лучший магазин</span>
                <span class="main-text-label color-brown"> кофе</span>
            </div>
        </div>
    </div>

    <div class="container-fluid hidden-sm hidden-md hidden-lg hidden-xl">
        <div class="row main-mob">
            <div class="text-shadow">
                <span class="main-text-label-mob color-green">Alex</span>
                <span class="main-text-label-mob color-brown">Coffee</span>
                <h3>
                    <img class="main-label-mob label-main" src="/resources/img/main_icon.png"
                         alt="Alex Coffee || Лучший магазин кофе">
                </h3>
                <span class="main-text-label-mob color-green">Лучший магазин</span>
                <br><span class="main-text-label-mob color-brown"> кофе</span>
            </div>
        </div>
    </div>
</section>

<!-- CATEGORIES -->
<div class="container-fluid">
    <section id="categories">
        <div class="row categories">
            <div class="col-xs-12 col-sm-12 col-md-12  col-lg-12 col-xl-12">
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
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                        <div class="category">
                            <a href="/category_${category.url}" title="Перейти к категории ${category.title}">
                                <img src="/resources/${category.photo.photoLinkShort}"
                                     class="img-thumbnail blink" width="150px" height="150px"
                                     alt="${category.title}">
                                <div class="text-shadow">${category.title}</div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </section>
</div>

<!-- DELIVERY -->
<div class="container-fluid">
    <section id="delivery">
        <div class="row delivery">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-delivery">
                    <img src="/resources/img/section_icon_2.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Доставка</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                    </div>
                </h3>
            </div>

            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-car fa-5x color-green" id="icon1"></i>
                <p class="icon-text">Курьер по адресу <br>Киев</p>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-truck fa-5x color-green" id="icon2"></i>
                <p class="icon-text">Новая Почта<br>Украина</p>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
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
<div class="container-fluid">
    <section id="payments">
        <div class="row payments">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-payments">
                    <img src="/resources/img/section_icon_3.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Оплата</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                    </div>
                </h3>
            </div>

            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-money fa-5x color-green" id="icon4"></i>
                <p class="icon-text">Оплата курьеру</p>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-truck fa-5x color-green" id="icon5"></i>
                <p class="icon-text">При получении на <br>Новой Почте</p>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                <i class="fa fa-cc-visa fa-5x color-green" id="icon6"></i>
                <p class="icon-text">Карта ПриватБанка</p>
            </div>
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
                <h4 class="text-payments">
                    Возврат приобретенных товаров осуществляется в случаях и согласно условиям, регламентированным
                    «Законом Украины о защите прав потребителей»
                </h4>
            </div>
        </div>
    </section>
</div>

<!-- GOOGLE EARTH -->
<div class="container-fluid">
    <section id="contacts">
        <div class="row google-map">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-contacts">
                    <img src="/resources/img/section_icon_4.png" width="90px" height="90px"
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
