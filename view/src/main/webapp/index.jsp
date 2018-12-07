<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored = "false" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="./login.do?login=Max&password=123">Login</a>
<a href="./news">List</a>

<c:if test="${newsList == null}">
    Injection failed
</c:if>
<c:forEach items="${newsList}" var="news">
    <article class="expanded">
        <h2>${news.title}</h2>
        <div class="article-info">
            Posted on
            <fmt:formatDate dateStyle="SHORT" value="${news.date}"/>
        </div>
        <h5><c:out value="${news.brief}" /></h5>
        <p><c:out value="${news.content}" /></p>
    </article>
</c:forEach>
</body>
</html>
