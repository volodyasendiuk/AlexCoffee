<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="width">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span><span class="icon-bar"></span>
                </button>
                <div class="navbar-brand text-shadow">
                    <a href="/">
                        <span class="nav-text-label color-green">Alex</span>
                        <span class="nav-text-label color-brown">Coffee</span>
                        <img class="nav-label" src="/resources/img/main_icon.png">
                    </a>
                </div>
            </div>

            <div id="navbar" class="navbar-collapse collapse">
                <div id="menu-product">
                    <ul class="nav navbar-nav">
                        <li id="nav-main"><a href="/#main">Главная</a></li>
                        <li id="nav-categories"><a href="/#categories">Категории</a></li>
                        <li id="nav-all-products"><a href="/#all-products">Товары</a></li>
                        <li id="nav-delivery" class="hidden-sm"><a href="/#delivery">Доставка</a></li>
                        <li id="nav-payments" class="hidden-sm"><a href="/#payments">Оплата</a></li>
                        <li id="nav-contacts" class="hidden-sm"><a href="/#contacts">Контакты</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li id="nav-cart">
                            <a href="/cart">
                                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                                Корзина (${cart_size})
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>
