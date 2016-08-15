<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="${user.name} | ${user.role.description} || Alex Coffee">
    <title>${user.name} | ${user.role.description} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- PERSON -->
<div class="container-fluid width">
    <section id="product">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">${user.name}</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <table class="table">
                    <tr>
                        <th>Имя:</th>
                        <td>${user.name}</td>
                    </tr>
                    <tr>
                        <th>Роль:</th>
                        <td>
                            <c:choose>
                                <c:when test="${user.role eq admin_role}">
                                    <span class="color-red">${user.role.description}</span>
                                </c:when>
                                <c:when test="${user.role eq manager_role}">
                                    <span class="color-green">${user.role.description}</span>
                                </c:when>
                                <c:otherwise>
                                    ${user.role.description}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>Логин:</th>
                        <td>${user.username}</td>
                    </tr>
                    <tr>
                        <th>Пароль:</th>
                        <td>${user.password}</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td>
                            <a href="mailto:${user.email}" title="Email">${user.email}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>Телефон:</th>
                        <td>${user.phone}</td>
                    </tr>
                    <c:if test="${(user.vkontakte ne null) and (user.vkontakte ne '')}">
                        <tr>
                            <th>ВКонтакте:</th>
                            <td>
                                <a href="https://${user.vkontakte}" title="ВКонтакте">${user.vkontakte}</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${(user.facebook ne null) and (user.facebook ne '')}">
                        <tr>
                            <th>Facebook:</th>
                            <td>
                                <a href="https://${user.facebook}" title="Facebook">${user.facebook}</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${(user.skype ne null) and (user.skype ne '')}">
                        <tr>
                            <th>Skype:</th>
                            <td>
                                <a href="skype:${user.skype}?call" title="Skype">${user.skype}</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${(user.description ne null) and (user.description ne '')}">
                        <tr>
                            <th>Описание:</th>
                            <td>${user.description}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <td></td>
                        <td>
                            <a href="/admin/edit_user_${user.id}"
                               title="Редактировать информацию о ${user.username}">
                                <button class="btn btn-success" type="submit">Редактировать</button>
                            </a>
                            <a href="/admin/delete_user_${user.id}"
                               title="Удалить информацию о ${user.username}">
                                <button class="btn btn-danger btn-mg" type="submit">Удалить</button>
                            </a>
                            <a href="/admin/users" title="Вернуться к списку пользователей">
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
