<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/admin/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Редактирование категории ${category.title} || Alex Coffee">
    <title>Редактирование категории ${category.title} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/admin/template/admin_navbar.jsp"/>

<!-- EDIT CATEGORY -->
<div class="container-fluid width">
    <section id="category">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Редактирование категории </span>
                        <span class="color-green">"${category.title}"</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <form enctype="multipart/form-data" action="/admin/update_category" method="post">
                    <input type="hidden" name="id" value="${category.id}">
                    <table class="table">
                        <tr>
                            <th>Название:</th>
                            <td>
                                <input class="input-order" type="text" name="title"
                                       placeholder=" Введите название категории" value="${category.title}"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <th>URL:</th>
                            <td>
                                <input class="input-order" type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                       placeholder=" Введите URL, формат (a-z, 0-9, _)" value="${category.url}"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <th>Описание:</th>
                            <td>
                                <textarea class="input-order textarea" type="text" name="description"
                                          placeholder=" Введите описание категории"
                                          maxlength="500">${category.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>Изображение:</th>
                            <td>
                                <input type="hidden" name="photo_id" value="${category.photo.id}">
                                <input class="input-order" type="text" name="photo_title"
                                       placeholder="Введите название фото" value="${category.photo.title}"
                                       minlength="5" maxlength="100">
                                <br><input type="file" name="photo" accept="image/*">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit"
                                        title="Обновить информацию о категории">Сохранить</button>
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
