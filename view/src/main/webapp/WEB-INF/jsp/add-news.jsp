<%@page contentType = "text/html;charset = UTF-8" language = "java" pageEncoding="UTF-8" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="application.add-news.title"/></jsp:attribute>
        <jsp:attribute name="content">
            <article class="expanded">
                <fieldset>
<!--				<legend>Form legend</legend>-->
                      <form:form method = "POST" action = "./add-news" modelAttribute="news">
                            <p>
                                <form:label path = "title"><spring:message code="application.common.title"/></form:label>
                                <form:input path = "title" />
                                <form:errors path="title" class="error"/>
                            </p>
                            <p>
                               <form:label path = "date"><spring:message code="application.common.date"/></form:label>
                               <form:input type="date" path = "date" />
                               <form:errors path="date" class="error" />
                            </p>
                            <p>
                               <form:label path = "brief"><spring:message code="application.common.brief"/></form:label>
                               <form:textarea path = "brief" cols="60" rows = "5"/><br/>
                               <form:errors path="brief" class="error" />
                            </p>
                            <p>
                               <form:label path = "content"><spring:message code="application.common.content"/></form:label>
                               <form:textarea path = "content" cols="60" rows="10"/><br/>
                               <form:errors path="content" class="error"/>
                            </p>
                            <p>
                               <spring:message code="application.common.submit" var="submit"/>
                               <input type = "submit" class="formbutton" value = "${submit}"/>
                            </p>
                      </form:form>
       	        </fieldset>
              </article>
   </jsp:attribute>
</t:generic>