<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Заказ ${order.number} || Alex Coffee">
    <title>Заказ ${order.number} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- ORDER -->
<div class="container-fluid">
    <section id="order">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Заказ </span>
                        <span class="color-green">${order.number}</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <table class="table">
                    <tr>
                        <td><b>Номер:</b></td>
                        <td>${order.number}</td>
                    </tr>
                    <tr>
                        <td><b>Статус:</b></td>
                        <td>${order.status.description}</td>
                    </tr>
                    <tr>
                        <td><b>Дата:</b></td>
                        <td>${order.date}</td>
                    </tr>
                    <tr>
                        <td><b>Клиент:</b></td>
                        <td>
                            Имя: ${order.client.name}
                            <br>Email: ${order.client.email}
                            <br>Телефон: ${order.client.phone}
                        </td>
                    </tr>
                    <tr>
                        <td><b>Адрес доставки:</b></td>
                        <td>
                            <c:choose>
                                <c:when test="${(order.shippingAddress eq null) or (order.shippingAddress eq '')}">
                                    -
                                </c:when>
                                <c:when test="${order.shippingAddress ne null}">
                                    ${order.shippingAddress}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Детали доставки:</b></td>
                        <td>
                            <c:choose>
                                <c:when test="${(order.shippingDetails eq null) or (order.shippingDetails eq '')}">
                                    -
                                </c:when>
                                <c:when test="${order.shippingDetails ne null}">
                                    ${order.shippingDetails}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Коментарии:</b></td>
                        <td>
                            <c:choose>
                                <c:when test="${(order.description eq null) or (order.description eq '')}">
                                    -
                                </c:when>
                                <c:when test="${order.description ne null}">
                                    ${order.description}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Товары:</b></td>
                        <td>
                            <c:choose>
                                <c:when test="${fn:length(products) eq 0}">
                                    Cписок товаров пуст!
                                </c:when>
                                <c:when test="${fn:length(products) gt 0}">
                                    <c:forEach items="${products}" var="product">
                                        <a href="/product_${product.url}"
                                           title="Перейти к товару ${product.title}">
                                                ${product.title}</a>
                                        <br>№ ${product.id}, ${product.price} грн
                                        <br>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Общая сумма:</b></td>
                        <td>${priceOfAllProducts} грн</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a href="/admin/edit_order_${order.id}" title="Редактировать заказ ${order.number}">
                                <button class="btn btn-success" type="submit">Редактировать</button>
                            </a>
                            <a href="/admin/delete_order_${order.id}" title="Удалить заказ ${order.number}">
                                <button class="btn btn-danger" type="submit">Удалить</button>
                            </a>
                            <a href="/admin/orders" title="Вернуться к списку заказов">
                                <button class="btn btn-info" type="submit">Назад</button>
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </section>
</div>
</body>
</html>
