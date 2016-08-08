<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
        <table class="table cart-table">
            <tr>
                <th>Название</th>
                <th>Кол.</th>
                <th>Фото</th>
                <th>Категория</th>
                <th>Стоимость</th>
            </tr>
            <c:forEach items="${sales}" var="sale">
                <tr>
                    <td>
                        <a href="/product_${sale.product.url}"
                           title="Перейти к ${sale.product.title}">
                                ${sale.product.title}</a>
                    </td>
                    <td>${sale.number}</td>
                    <td>
                        <img width="50px" height="50px"
                             src="/resources/img/${sale.product.photo.photoLinkShort}"
                             alt="${sale.product.title}">
                    </td>
                    <td>
                        <a href="/category_${sale.product.category.url}"
                           title="Перейти к категории ${sale.product.category.title}">
                                ${sale.product.category.title}</a>
                    </td>
                    <td>
                        <fmt:formatNumber type="number" value="${sale.product.price}"/> грн
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td style="text-align: right;"><strong>Итого:</strong></td>
                <td><b><fmt:formatNumber type="number" value="${price_of_cart}"/> грн</b></td>
            </tr>
        </table>
    </div>
</div>
