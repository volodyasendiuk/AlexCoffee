<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Редактирование продукта ${product.title} || Alex Coffee">
    <title>Редактирование продукта ${product.title} || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- EDIT PRODUCT -->
<div class="container-fluid">
    <section id="product">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Редактирование товара </span>
                        <span class="color-green">"${product.title}"</span>
                    </b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <form enctype="multipart/form-data" action="/admin/update_product" method="post">
                    <input type="hidden" name="id" value="${product.id}">
                    <table class="table">
                        <tr>
                            <td><b>Название:</b></td>
                            <td>
                                <input class="input-order" type="text" name="title"
                                       placeholder="Введите название товара" value="${product.title}"
                                       minlength="5" maxlength="100" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>URL:</b></td>
                            <td>
                                <input class="input-order" type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                       placeholder=" Введите URL, формат (a-z, 0-9, _)" value="${product.url}"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Категория:</b></td>
                            <td>
                                <select class="input-order" name="category">
                                    <option value="${product.category.id}">${product.category.title}</option>
                                    <c:forEach items="${categories}" var="category">
                                        <c:if test="${category.id ne product.category.id}">
                                            <option value="${category.id}">${category.title}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Параметры:</b></td>
                            <td>
                                <textarea class="input-order textarea" type="text" name="parameters"
                                          placeholder="Введите параметры товара"
                                          maxlength="500" required>${product.parameters}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Описание:</b></td>
                            <td>
                                <textarea class="input-order textarea" type="text" name="description"
                                          placeholder="Введите описание товара"
                                          maxlength="500">${product.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Изображение:</b></td>
                            <td>
                                <input type="hidden" name="photo_id" value="${product.photo.id}">
                                <input class="input-order" type="text" name="photo_title"
                                       placeholder="Введите название фото" value="${product.photo.title}"
                                       minlength="5" maxlength="100">
                                <br>Малое: <input type="file" name="small_photo" accept="image/*">
                                <br>Большое: <input type="file" name="big_photo" accept="image/*">
                            </td>
                        </tr>
                        <tr>
                            <td><b>Цена:</b></td>
                            <td>
                                <input class="input-order" type="text" name="price" min="0" max="99999" step="0.01"
                                       placeholder="Введите цену товара" value="${product.price}" required>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit"
                                        title="Обновить информацию о товаре">Сохранить</button>
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
