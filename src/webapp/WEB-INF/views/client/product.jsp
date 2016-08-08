<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="index,follow">
    <meta name="description" content="${product.description}"/>
    <meta name="keywords" content="${product.title}"/>
    <meta name="title" content="${product.title} || Alex Coffee">
    <title>${product.title} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/client_navbar.jsp"/>

<!-- PRODUCT -->
<div class="container-fluid width">
    <section id="one-product">
        <div class="row one-product">
            <div class="col-lg-7 col-lg-offset-1 col-md-7 col-md-offset-1 col-sm-7 col-sm-offset-1 col-xs-12 col-xs-offset-0">
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-10 col-xs-offset-1">
                    <img src="/resources/img/${product.photo.photoLinkShort}" width="185px" height="185px"
                         alt="${product.title}">
                </div>

                <div class="col-xs-6 col-xs-offset-2 col-sm-6 col-sm-offset-2 col-md-6 col-md-offset-2 col-lg-6 col-lg-offset-2 col-xl-6 col-xl-offset-2">
                    <h3 class="text-shadow"><b>${product.title}</b></h3>
                    <h5>Артикул: ${product.article}</h5>
                    <h3>
                        <p class="price-product">
                            <fmt:formatNumber type="number" value="${product.price}"/> грн
                        </p>
                    </h3>
                    <form action="/cart_add" method=post>
                        <input type=hidden name="id" value="${product.id}">
                        <p class="text" title="Добавить ${product.title} в корзину">
                            <button class="btn btn-success" type="submit">Добавить в корзину</button>
                        </p>
                    </form>
                </div>

                <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <p><b>Характеристики товара:</b></p>
                    <p>${product.parameters}</p>

                    <c:if test="${(product.description ne null) and (product.description ne '')}">
                        <br>
                        <p><b>Описание товара:</b></p>
                        <p>${product.description}</p>
                    </c:if>

                    <c:if test="${(product.photo.photoLinkLong ne null) and (product.photo.photoLinkLong ne '')}">
                        <p><img src="/resources/img/${product.photo.photoLinkLong}" width="465px" height="465px"
                                class="hidden-xs hidden-sm" alt="${product.title}"></p>
                    </c:if>
                    <br>
                </div>
            </div>

            <c:if test="${fn:length(featured_products) gt 0}">
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 featured-products text-center">
                    <c:forEach items="${featured_products}" var="featured_product">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <div class="product">
                                <a href="/product_${featured_product.url}"
                                   title="Перейти к ${featured_product.title}">
                                    <img src="/resources/img/${featured_product.photo.photoLinkShort}"
                                         alt="${featured_product.title}"
                                         class="img-thumbnail blink" width="185px" height="185px">
                                    <div class="text-shadow">
                                            ${featured_product.title}
                                    </div>
                                    <p class="price-top">
                                        <fmt:formatNumber type="number" value="${featured_product.price}"/> грн
                                    </p>
                                </a>

                                <form action="/cart_add_quickly" method=post>
                                    <input type="hidden" name="id" value="${featured_product.id}">
                                    <input type="hidden" name="url" value="/product_${product.url}">
                                    <p class="text" title="Добавить ${featured_product.title} в корзину">
                                        <button class="btn btn-success" type="submit">Добавить в корзину</button>
                                    </p>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </section>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>
