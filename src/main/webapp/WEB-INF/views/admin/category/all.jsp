<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Категории кофе || Alex Coffee">
    <title>Категории кофе || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/admin_navbar.jsp"/>

<!-- CATEGORIES -->
<div class="container-fluid width">
    <section id="categories">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Категории</span>
                        <c:if test="${fn:length(categories) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                            <br>
                            <a href="/admin/add_category" title="Добавить новую категорию">
                                <button class="btn btn-success" type="submit">Добавить</button>
                            </a>
                        </c:if>
                    </b>
                </div>
            </div>

            <c:if test="${fn:length(categories) gt 0}">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <th>Название</th>
                            <td class="hidden-xs"><b>URL</th>
                            <td>
                                <b>Действие</b>
                                <a href="/admin/add_category" title="Добавить новую категорию">
                                    <button class="btn btn-success" type="submit">Добавить</button>
                                </a>
                                <a href="/admin/delete_all_categories" title="Удалить все категории">
                                    <button class="btn btn-danger" type="submit">Удалить ВСЕ</button>
                                </a>
                            </td>
                        </tr>

                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td>
                                    <a href="/category_${category.url}"
                                       title="Перейти к категории ${category.title}">
                                            ${category.title}</a>
                                </td>
                                <td class="hidden-xs">${category.url}</td>
                                <td>
                                    <a href="/admin/view_category_${category.id}"
                                       title="Смотреть категорию ${category.title}">
                                        <button class="btn btn-info" type="submit">Смотреть</button>
                                    </a>
                                    <a href="/admin/edit_category_${category.id}"
                                       title="Редактировать категорию ${category.title}">
                                        <button class="btn btn-success" type="submit">Редактировать</button>
                                    </a>
                                    <a href="/admin/delete_category_${category.id}"
                                       title="Удалить категорию ${category.title}">
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
