<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container-fluid width">
    <section id="categories">
        <div class="row categories">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h3 class="intro-text">
                    <img id="label-category" src="/resources/img/section_icon_1.png" width="90px" height="90px"
                         alt="Alex Coffee || Лучший магазин кофе">
                    <div class="text-shadow">
                        <span class="home-block-name color-green">Категории</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                        <c:if test="${fn:length(categories) eq 0}">
                            <span class="color-red"> - список пуст!</span>
                        </c:if>
                    </div>
                </h3>
            </div>

            <c:if test="${fn:length(categories) gt 0}">
                <c:forEach items="${categories}" var="category">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                        <div class="category">
                            <a href="/category_${category.url}" title="Перейти к категории ${category.title}">
                                <img src="/resources/img/${category.photo.photoLinkShort}"
                                     class="img-thumbnail blink" width="150px" height="150px"
                                     alt="${category.title}">
                                <div class="text-shadow">${category.title}</div>
                            </a>
                        </div>
                    </div>
                </c:forEach>

                <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <h4 class="text-all-products text-shadow">
                        <a href="/all_products" title="Перейти ко всем товарам">Весь ассортимент кофе</a>
                    </h4>
                </div>
            </c:if>
        </div>
    </section>
</div>
