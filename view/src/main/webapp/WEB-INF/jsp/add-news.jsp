<%@ page contentType="text/html;charset = UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages">
    <t:generic>
        <jsp:attribute name="title"><fmt:message key="application.add-news.title"/></jsp:attribute>
        <jsp:attribute name="content">
            <article class="expanded">
                <fieldset>
                    <form method="POST" action="edit-news" modelAttribute="news">
                            <p>
                                <label for="title"><fmt:message key="application.common.title"/></label>
                                <input id="title" name="title"/>
                                <errors path="title" class="error"/>
                            </p>
                            <p>
                                <label for="date"><fmt:message key="application.common.date"/></label>
                                <input type="date" name="date" id="date"/>
                                <errors path="date" class="error"/>
                            </p>
                            <p>
                                <label for="brief"><fmt:message key="application.common.brief"/></label>
                                <textarea id="brief" name="brief" cols="60" rows="5"></textarea><br/>
                                <errors path="brief" class="error"/>
                            </p>
                            <p>
                                <label for="content"><fmt:message
                                        key="application.common.content"/></label>
                                <textarea id="content" name="content" cols="60" rows="10"></textarea><br/>
                                <errors path="content" class="error"/>
                            </p>
                            <p>
                                <fmt:message key="application.common.submit" var="submit"/>
                                <input type="submit" class="formbutton" value="${submit}"/>
                            </p>
                    </form>
                </fieldset>
            </article>
   </jsp:attribute>
    </t:generic>
</fmt:bundle>