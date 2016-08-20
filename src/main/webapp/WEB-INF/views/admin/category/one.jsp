<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/admin/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Категория ${category.title} || Alex Coffee">
    <title>Категория ${category.title} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/admin/template/admin_navbar.jsp"/>

<!-- CATEGORY -->
<div class="container-fluid width">
    <section id="category">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Категория </span>
                        <span class="color-green">"${category.title}"</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <table class="table">
                    <tr>
                        <th>Название:</th>
                        <td>${category.title}</td>
                    </tr>
                    <tr>
                        <th>URl:</th>
                        <td>
                            <a href="/category_${category.url}"
                               title="Перейти к категории ${category.title}">
                               ${category.url}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>Описание:</th>
                        <td>${category.description}</td>
                    </tr>
                    <tr>
                        <th>Изображение:</th>
                        <td>${category.photo.title}
                            <br><img width="75px" height="75px"
                                     src="/resources/img/${category.photo.photoLinkShort}">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a href="/admin/edit_category_${category.id}" title="Редактировать категорию ${category.title}">
                                <button class="btn btn-success" type="submit">Редактировать</button>
                            </a>
                            <a href="/admin/delete_category_${category.id}" title="Удалить категорию ${category.title}">
                                <button class="btn btn-danger" type="submit">Удалить</button>
                            </a>
                            <a href="/admin/categories" title="Вернуться к списку категорий">
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
