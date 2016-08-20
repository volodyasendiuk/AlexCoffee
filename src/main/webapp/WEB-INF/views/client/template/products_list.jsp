<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <input type="hidden" name="url" value="/all_products">
                    <p class="text" title="Добавить ${product.title} в корзину">
                        <button class="btn btn-success" type="submit">Добавить в корзину</button>
                    </p>
                </form>
            </div>
        </div>
    </c:forEach>
</c:if>
