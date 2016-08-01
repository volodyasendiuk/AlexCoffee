<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="index,follow">
    <meta name="title" content="Оформление заказа || Alex Coffee">
    <title>Оформление заказа || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/client_navbar.jsp"/>

<!-- CHECKOUT -->
<div class="container-fluid">
    <section id="checkout">
        <div class="row checkout">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="alert alert-info" role="alert">
                    <strong>${order.client.name}</strong>, cпасибо за заказ!<br><br>
                    Менеджер по продажам свяжется с Вами в течение часа!<br><br>
                    Номер заказа: <b>${order.number}</b><br><br>
                    Будем рады видеть Вас снова!<br><br>
                    Телефон для связи с нами:<br><br>
                    +38(063)73-99-290<br><br>
                    С уважением, команда <b>Alex Coffee</b>.<br>
                </div>
            </div>

            <!-- Products in shopping cart -->
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <table class="table cart-table">
                    <tr>
                        <td><b>Название</b></td>
                        <td><b>Фото</b></td>
                        <td class="hidden-xs"><b>Категория</b></td>
                        <td><b>Стоимость</b></td>
                    </tr>
                    <c:forEach items="${productsInCart}" var="product">
                        <tr>
                            <td>
                                <a href="/product_${product.url}"
                                   title="Перейти к ${product.title}">
                                        ${product.title}</a>
                            </td>
                            <td>
                                <img width="50px" height="50px" src="/resources/${product.photo.photoLinkShort}"
                                     alt="${product.title}">
                            </td>
                            <td class="hidden-xs">
                                <a href="/category_${product.category.url}"
                                   title="Перейти к категории ${product.category.title}">
                                        ${product.category.title}</a>
                            </td>
                            <td>
                                <fmt:formatNumber type="number" value="${product.price}"/> грн
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td class="hidden-xs"></td>
                        <td></td>
                        <td class="hidden-xs"></td>
                        <td style="text-align: right;"><strong>Итого:</strong></td>
                        <td>
                            <strong>
                                <fmt:formatNumber type="number" value="${priceOfProductsInCart}"/> грн
                            </strong>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </section>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>
