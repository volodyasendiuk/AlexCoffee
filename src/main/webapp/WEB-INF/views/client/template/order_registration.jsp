<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <form action="/checkout" method="post">
        <div class="col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-2 col-lg-2 col-lg-offset-2 col-xl-2 col-xl-offset-2 input-padding text-center">
            <input class="input" type="text" name="user_name" placeholder=" Введите имя"
                   minlength="2" maxlength="50" required autofocus>
        </div>
        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
            <input id="phone" class="input" type="text" name="user_phone" placeholder=" Введите телефон"
                   required>
        </div>
        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
            <input class="input" type="email" name="user_email" placeholder=" Введите Email"
                   minlength="5" maxlength="50">
        </div>
        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
            <input type="submit" class="btn btn-success" value="Оформить заказ" width="80px">
        </div>
    </form>
</div>
