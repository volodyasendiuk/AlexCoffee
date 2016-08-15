<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Персонал || Alex Coffee">
    <title>Персонал || Alex Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/template/manager_navbar.jsp"/>

<!-- PERSONS -->
<div class="container-fluid width">
    <section id="persons">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b>
                        <span class="color-brown">Персонал</span>
                        <c:if test="${fn:length(users) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                        </c:if>
                    </b>
                </div>
            </div>

            <c:if test="${fn:length(users) gt 0}">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <td><b>Роль</b></td>
                            <td><b>Имя</b></td>
                            <td class="hidden-xs"><b>Телефон</b></td>
                            <td><b>Действие</b></td>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.role eq admin_role}">
                                            <b><span class="color-red">${user.role.description}</span></b>
                                        </c:when>
                                        <c:when test="${user.role eq manager_role}">
                                            <span class="color-green">${user.role.description}</span>
                                        </c:when>
                                        <c:otherwise>
                                            ${user.role.description}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.name}</td>
                                <td class="hidden-xs">${user.phone}</td>
                                <td>
                                    <a href="/manager/view_user_${user.id}"
                                       title="Смотреть информацию ${user.username}">
                                        <button class="btn btn-info btn-mg" type="submit">Смотреть</button>
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
