<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Заказы || Alex Coffee">
    <title>Заказы || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- ORDERS -->
<div class="container-fluid">
    <section id="orders">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Заказы</span>
                        <c:if test="${fn:length(orders) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                        </c:if>
                    </b>
                </div>
            </div>

            <c:if test="${fn:length(orders) gt 0}">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <td><b>Номер</b></td>
                            <td><b>Статус</b></td>
                            <td class="hidden-xs"><b>Дата</b></td>
                            <td>
                                <b>Действие</b>
                                <a href="/admin/delete_all_orders" title="Удалить все заказы">
                                    <button class="btn btn-danger" type="submit">Удалить ВСЕ</button>
                                </a>
                            </td>
                        </tr>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.number}</td>
                                <td>${order.status.description}</td>
                                <td class="hidden-xs">${order.date}</td>
                                <td>
                                    <a href="/admin/view_order_${order.id}" title="Смотреть заказ ${order.number}">
                                        <button class="btn btn-info" type="submit">Смотреть</button>
                                    </a>
                                    <a href="/admin/edit_order_${order.id}" title="Редактировать заказ ${order.number}">
                                        <button class="btn btn-success" type="submit">Редактировать</button>
                                    </a>
                                    <a href="/admin/delete_order_${order.id}" title="Удалить заказ ${order.number}">
                                        <button class="btn btn-danger" type="submit">Удалить</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </section>
</div>
</body>
</html>
