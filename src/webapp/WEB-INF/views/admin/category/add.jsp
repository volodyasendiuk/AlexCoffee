<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Новая категория || Alex Coffee">
    <title>Новая категория || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- ADD CATEGORY -->
<div class="container-fluid width">
    <section id="category">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-green">Новая </span>
                        <span class="color-brown">категория</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <form enctype="multipart/form-data" action="/admin/save_category" method="post">
                    <table class="table">
                        <tr>
                            <td><b>Название:</b></td>
                            <td>
                                <input class="input-order" type="text" name="title"
                                       placeholder=" Введите название категории"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>URL:</b></td>
                            <td>
                                <input class="input-order" type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                       placeholder=" Введите URL, формат (a-z, 0-9, _)"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Описание:</b></td>
                            <td>
                                <textarea class="input-order textarea" type="text" name="description"
                                          placeholder=" Введите описание категории" maxlength="500"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Изображение:</b></td>
                            <td>
                                <input class="input-order" type="text" name="photo_title"
                                       placeholder="Введите название фото" minlength="5" maxlength="100" required>
                                <br><input type="file" name="photo" accept="image/*">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit">Добавить категорию</button>
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
