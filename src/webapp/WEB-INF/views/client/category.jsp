<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="description" content="${category.description}"/>
    <meta name="keywords" content="${category.title}"/>
    <meta name="title" content="${category.title} || Alex Coffee">
    <title>${category.title} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/client_navbar.jsp"/>

<!-- COFFEE -->
<div class="container-fluid width">
    <section id="products_${category.url}">
        <div class="row products">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text label-categories">
                    <img id="icon7" src="/resources/img/${category.photo.photoLinkShort}" width="150px" height="150px"
                         alt="${category.title}">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">${category.title}</span>
                        <c:if test="${fn:length(products) eq 0}">
                            <span class="home-block-name color-red"> - список пуст!</span>
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
                                <input type="hidden" name="url" value="/category_${category.url}">
                                <p class="text" title="Добавить ${product.title} в корзину">
                                    <button class="btn btn-success" type="submit">Добавить в корзину</button>
                                </p>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <h4 class="text-all-products text-shadow">
                    <a href="/all_products" title="Перейти ко всем товарам">Весь ассортимент кофе</a>
                </h4>
            </div>
        </div>
    </section>
</div>

<!-- CATEGORY DESCRIPTION -->
<c:if test="${category.description ne ''}">
    <div class="container-fluid width">
        <section id="category-description">
            <div class="row category-description color-black">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <p><b>${category.title}</b></p>
                    <p>${category.description}</p>
                </div>
            </div>
        </section>
    </div>
</c:if>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>
