<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored = "false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:generic>
    <jsp:attribute name="title"><spring:message code="application.news-list.title"/></jsp:attribute>
        <jsp:attribute name="content">
          <c:forEach items="${newsList}" var="news">
             <article class="expanded">
                <h2>${news.title}</h2>
                <div class="article-info">
                    <spring:message code="application.news-list.posted" />
                    <fmt:formatDate dateStyle="SHORT" value="${news.date}"/>
                </div>
                <h5><c:out value="${news.brief}" /></h5>
                <p><c:out value="${news.content}" /></p>
                <spring:url value="edit-news/${news.id}" var="editUrl" />
                <spring:url value="delete-news/${news.id}" var="deleteUrl" />
                <spring:url value="view-news/${news.id}" var="viewUrl" />
                <div class="leftstr">
                    <a href="${viewUrl}" class="button"><spring:message code="application.news-list.view" /></a>
                </div>
                <div class="rightstr">
                    <a href="${editUrl}" class="button"><spring:message code="application.news-list.edit" /></a>
                    <a href="${deleteUrl}" class="button button-reversed"><spring:message code="application.news-list.delete" /></a>
                </div>
                <div style="clear: left"/>
             </article>
          </c:forEach>
        </jsp:attribute>
</t:generic>