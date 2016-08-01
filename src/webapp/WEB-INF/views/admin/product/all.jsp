<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Кофе || Alex Coffee">
    <title>Кофе || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- PRODUCTS -->
<div class="container-fluid">
    <section id="products">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Товары</span>
                        <c:if test="${fn:length(products) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                            <br>
                            <a href="/admin/add_product" title="Добавить новый товар">
                                <button class="btn btn-success" type="submit">Добавить</button>
                            </a>
                        </c:if>
                    </b>
                </div>
            </div>

            <c:if test="${fn:length(products) gt 0}">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <td width="40%"><b>Название</b></td>
                            <td class="hidden-xs" width="15%"><b>Категория</b></td>
                            <td width="35%">
                                <b>Действие </b>
                                <a href="/admin/add_product" title="Добавить новый товар">
                                    <button class="btn btn-success" type="submit">Добавить</button>
                                </a>
                                <a href="/admin/delete_all_products" title="Удалить все товары">
                                    <button class="btn btn-danger" type="submit">Удалить ВСЕ</button>
                                </a>
                            </td>
                        </tr>

                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td>
                                    <a href="/product_${product.url}"
                                       title="Перейти к товару ${product.title}">
                                            ${product.title}</a>
                                </td>
                                <td class="hidden-xs">
                                    <a href="/admin/view_category_${product.category.id}"
                                       title="Смотреть категорию ${product.category.title}">
                                            ${product.category.title}</a>
                                </td>
                                <td>
                                    <a href="/admin/view_product_${product.id}"
                                       title="Смотреть товар ${product.title}">
                                        <button class="btn btn-info" type="submit">Смотреть</button>
                                    </a>
                                    <a href="/admin/edit_product_${product.id}"
                                       title="Редактировать товар ${product.title}">
                                        <button class="btn btn-success" type="submit">Редактировать</button>
                                    </a>
                                    <a href="/admin/delete_product_${product.id}"
                                       title="Удалить товар ${product.title}">
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
