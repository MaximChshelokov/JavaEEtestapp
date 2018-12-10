<%@page contentType = "text/html;charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored = "false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:generic>
    <jsp:attribute name="title">News list title</jsp:attribute>
        <jsp:attribute name="content">
          <%--@elvariable id="newsList" type="java.util.List<News>"--%>
          <c:forEach items="${newsList}" var="news">
             <article class="expanded">
                <h2>${news.title}</h2>
                <div class="article-info">
                    Posted on
                    <fmt:formatDate dateStyle="SHORT" value="${news.date}"/>
                </div>
                <h5><c:out value="${news.brief}" /></h5>
                <p><c:out value="${news.content}" /></p>
                <c:url value="edit-news/${news.id}" var="editUrl" />
                <c:url value="delete-news/${news.id}" var="deleteUrl" />
                <c:url value="view-news/${news.id}" var="viewUrl" />
                <div class="leftstr">
                    <a href="${viewUrl}" class="button">View</a>
                </div>
                <div class="rightstr">
                    <a href="${editUrl}" class="button">Edit</a>
                    <a href="${deleteUrl}" class="button button-reversed">Delete</a>
                </div>
                 <div style="clear: left"></div>
             </article>
          </c:forEach>
        </jsp:attribute>
</t:generic>