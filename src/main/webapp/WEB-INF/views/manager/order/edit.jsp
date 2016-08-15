<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Редактирование заказа ${order.number} || Alex Coffee">
    <title>Редактирование заказа ${order.number} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/manager_navbar.jsp"/>

<!-- EDIT ORDER -->
<div class="container-fluid width">
    <section id="order">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Редактирование заказа </span>
                        <span class="color-green">${order.number}</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <form action="/manager/update_order" method="post">
                    <input type="hidden" name="id" value="${order.id}">
                    <input type="hidden" name="auth_user" value="${auth_user.id}">
                    <table class="table">
                        <tr>
                            <td><b>Номер:</b></td>
                            <td>
                                <input class="input-order" type="text" name="number" pattern="[A-Za-z0-9]{3,10}"
                                       placeholder=" Введите номер, формат (A-Z, a-z, 0-9)"
                                       value="${order.number}" minlength="3" maxlength="10" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Статус:</b></td>
                            <td>
                                <select class="input-order" name="status">
                                    <option value="${order.status.id}">${order.status.description}</option>
                                    <c:forEach items="${statuses}" var="status">
                                        <c:if test="${status.id ne order.status.id}">
                                            <option value="${status.id}">${status.description}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Дата:</b></td>
                            <td>${order.date}</td>
                        </tr>
                        <tr>
                            <td><b>Клиент:</b></td>
                            <td>
                                <input class="input-order" type="text" name="name"
                                       placeholder=" Введите имя клиента" value="${order.client.name}"
                                       minlength="2" maxlength="50" required>
                                <br><input class="input-order" type="email" name="email"
                                           value="${order.client.email}"
                                           placeholder=" Введите email клиента, формат (A-Z, a-z, 0-9, _, ., @)"
                                           pattern="[A-Za-z0-9_.@]{5,50}" minlength="5" maxlength="50">
                                <br><input id="phone" class="input-order" type="text" name="phone"
                                           placeholder=" Введите телефон клиента" value="${order.client.phone}"
                                           required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Адрес доставки:</b></td>
                            <td>
                                <input class="input-order" type="text" name="shipping-address"
                                       placeholder=" Введите адрес доставки" value="${order.shippingAddress}"
                                       maxlength="100">
                            </td>
                        </tr>
                        <tr>
                            <td><b>Детали доставки:</b></td>
                            <td>
                                <input class="input-order" type="text" name="shipping-details"
                                       placeholder=" Введите детали даставки" value="${order.shippingDetails}"
                                       maxlength="100">
                            </td>
                        </tr>
                        <tr>
                            <td><b>Коментарии:</b></td>
                            <td>
                                <textarea class="input-order textarea" type="text" name="description"
                                          placeholder=" Коментарий" maxlength="250">${order.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Товары:</b></td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:length(salePositions) eq 0}">
                                        Cписок товаров пуст!
                                    </c:when>
                                    <c:when test="${fn:length(salePositions) gt 0}">
                                        <c:forEach items="${salePositions}" var="salePosition">
                                            <a href="/product_${salePosition.product.url}"
                                               title="Перейти к товару ${salePosition.product.title}">
                                                    ${salePosition.product.title}</a>, № ${salePosition.product.id},
                                            <br>${salePosition.number} x ${salePosition.product.price} грн
                                            <br>--------------<br>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Общая сумма:</b></td>
                            <td>${order_price} грн</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit"
                                        title="Обновить информацию о заказе">Сохранить
                                </button>
                                <button class="btn btn-info" type="reset" title="Сбросить введенные даные">Сброс
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>
