<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="robots" content="index,follow">
    <meta name="title" content="Оформление заказа || Alex Coffee">
    <title>Оформление заказа || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

<!-- CHECKOUT -->
<div class="container-fluid width">
    <section id="checkout">
        <div class="row checkout">
            <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
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

            <!-- PRODUCTS IN ORDER -->
            <jsp:include page="/WEB-INF/views/client/template/products_in_cart.jsp"/>
        </div>
    </section>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
