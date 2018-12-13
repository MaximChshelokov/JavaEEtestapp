<%@ page contentType="text/html;charset = UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="messages">
    <t:generic>
        <jsp:attribute name="title"><fmt:message key="application.view-news.title"/></jsp:attribute>
        <jsp:attribute name="content"><%--@elvariable id="base" type="java.lang.String"--%>
            <article class="expanded">
                <h2><%--@elvariable id="news" type="com.epam.javaee.entity.News"--%>
                    <c:out value="${news.title}"/></h2>
                <div class="article-info">
                    <fmt:message key="application.news-list.posted"/>
                    <fmt:formatDate dateStyle="SHORT" value="${news.date}"/>
                </div>
                <h5><c:out value="${news.brief}"/></h5>
                <p><c:out value="${news.content}"/></p>
                <c:url value="edit-news/${news.id}" var="editUrl"/>
                <c:url value="delete-news/${news.id}" var="deleteUrl"/>
                <a href="${base}/${editUrl}" class="button"><fmt:message key="application.news-list.edit"/></a>
                <a href="${base}/${deleteUrl}" class="button button-reversed"><fmt:message
                        key="application.news-list.delete"/></a>
                <div style="clear: left"></div>
            </article>
        </jsp:attribute>
    </t:generic>
</fmt:bundle>