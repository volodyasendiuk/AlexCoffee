<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Новый работник || Alex Coffee">
    <title>Новый работник || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- ADD PERSON -->
<div class="container-fluid width">
    <section id="user">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-green">Новый </span>
                        <span class="color-brown">работник</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <form action="/admin/save_user" method="post">
                    <table class="table">
                        <tr>
                            <td><b>Имя:</b></td>
                            <td>
                                <input class="input-order" type="text" name="name"
                                       placeholder="Введите имя"
                                       minlength="2" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Роль:</b></td>
                            <td>
                                <select class="input-order" name="role">
                                    <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}">${role.description}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Логин:</b></td>
                            <td>
                                <input class="input-order" type="text" name="username" pattern="[A-Za-z0-9_]{5,50}"
                                       placeholder="Введите логин, формат (A-Z, a-z, 0-9, _)"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Пароль:</b></td>
                            <td>
                                <input class="input-order" type="text" name="password" pattern="[A-Za-z0-9]{6,50}"
                                       placeholder=" Введите пароль, формат (A-Z, a-z, 0-9)"
                                       minlength="6" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Email:</b></td>
                            <td>
                                <input class="input-order" type="email" name="email" pattern="[A-Za-z0-9_.@]{5,50}"
                                       placeholder=" Введите электронную почту, формат (A-Z, a-z, 0-9, _, ., @)"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Телефон:</b></td>
                            <td>
                                <input id="phone" class="input-order" type="text" name="phone"
                                       placeholder=" Введите телефон" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>ВКонтакте:</b></td>
                            <td>
                                <input class="input-order" type="text" name="vkontakte" pattern="[a-z0-9_/.]{5,50}"
                                       placeholder=" Введите адрес ВКонтакте, формат (a-z, 0-9, _, /, .)"
                                       minlength="5" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <td><b>Facebook:</b></td>
                            <td>
                                <input class="input-order" type="text" name="facebook" pattern="[a-z0-9_/.]{5,50}"
                                       placeholder=" Введите адрес Facebook, формат (a-z, 0-9, _, /, .)"
                                       minlength="5" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <td><b>Skype:</b></td>
                            <td>
                                <input class="input-order" type="text" name="skype" pattern="[A-Za-z0-9_.]{5,50}"
                                       placeholder=" Введите логин Skype, формат (A-Z, a-z, 0-9, _, .)"
                                       minlength="5" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <td><b>Описание:</b></td>
                            <td>
                                <textarea class="input-order textarea" type="text" name="description"
                                          placeholder=" Введите описание работника"
                                          maxlength="500"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit">Добавить пользователя</button>
                                <button class="btn btn-info" type="reset">Сброс</button>
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
