<%@ page contentType="text/html;charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="messages">
    <t:generic>
        <jsp:attribute name="title"><fmt:message key="application.news-list.title"/></jsp:attribute>
        <jsp:attribute name="content">
          <%--@elvariable id="newsList" type="java.util.List<News>"--%>
            <c:forEach items="${newsList}" var="news"><%--@elvariable id="base" type="java.lang.String"--%>
                <article class="expanded">
                    <h2>${news.title}</h2>
                    <div class="article-info">
                        <fmt:message key="application.news-list.posted"/>
                        <fmt:formatDate dateStyle="SHORT" value="${news.date}"/>
                    </div>
                    <h5><c:out value="${news.brief}"/></h5>
                    <p><c:out value="${news.content}"/></p>
                    <c:url value="action/edit-news/${news.id}" var="editUrl"/>
                    <c:url value="action/delete-news/${news.id}" var="deleteUrl"/>
                    <c:url value="action/view-news/${news.id}" var="viewUrl"/>
                    <div class="leftstr">
                        <a href="${base}/${viewUrl}" class="button"><fmt:message
                            key="application.news-list.view"/></a>
                    </div>
                    <div class="rightstr">
                        <a href="${base}/${editUrl}" class="button"><fmt:message
                            key="application.news-list.edit"/></a>
                        <a href="${base}/${deleteUrl}" class="button button-reversed"><fmt:message
                            key="application.news-list.delete"/></a>
                    </div>
                    <div style="clear: left"></div>
                </article>
          </c:forEach>
        </jsp:attribute>
    </t:generic>
</fmt:bundle>