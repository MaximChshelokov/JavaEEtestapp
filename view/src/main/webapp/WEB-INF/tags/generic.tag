<%@tag description="Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="content" fragment="true" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8" />
    <title><spring:message code="application.title"/></title>
    <c:set var="base">
        ${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}
    </c:set>
    <spring:url value="/css/styles.css" var="mainCss" />
    <link rel="stylesheet" href="${mainCss}" type="text/css" />
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!--
    afflatus, a free CSS web template by ZyPOP (zypopwebtemplates.com/)

    Download: http://zypopwebtemplates.com/

    License: Creative Commons Attribution
    //-->
    <style>
        .error {
            font-size: 0.9em;
            color: red;
        }
        .leftstr, .rightstr {
            float: left;
            width: 50%;
        }
        .rightstr {
            text-align: right;
        }
    </style>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>

<body>

		<div id="sitename">
			<div class="width">
				<h1><a href="#"> <spring:message code="application.title"/> </a></h1>
				<nav>
					<ul>
					    <c:set var="localeCode" value="${pageContext.response.locale}"/>
        				<li ${localeCode=="ru"?"class='start selected'":"class='start'"}><a href="?lang=ru">Русский</a></li>
        	    		<li ${localeCode=="en"?"class='end selected'":"class='end'"}><a href="?lang=en">English</a></li>
        		    </ul>
				</nav>
				<div class="clear"></div>
			</div>
		</div>
		<header>
			<div class="width">
				<h2><jsp:invoke fragment="title"/></h2>
<!--				<div class="tagline">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique neque at tellus interdum euismod. Quisque suscipit quam luctus quam elementum a molestie diam sollicitudin. Nam vehicula placerat mi, in mollis elit imperdiet in. Sed volutpat tincidunt sem at auctor</p>
				</div>
				-->
			</div>
		</header>
		<section id="body" class="width clear">
			<aside id="sidebar" class="column-left">
				<ul>
                	<li>
						<h4><spring:message code="application.common.navigate" /></h4>
                        <ul class="blocklist">
                            <c:forEach items="${navigationMenu}" var="menuItem">
                                <spring:url value="${menuItem.url}" var="menuUrl" context="true"/>
                                <li ${fn:contains(pageContext.request.requestURL, menuItem.url)?"class='selected-item'":""}>
                                    <a href="${base}/${menuUrl}"><spring:message code="${menuItem.name}" /></a>
                                </li>
                            </c:forEach>
                        </ul>

					</li>
				</ul>
			</aside>
			<section id="content" class="column-right">

	            <jsp:invoke fragment="content"/>
		    </section>

	    </section>
		<footer class="clear">
			<div  class="width">
				<p class="left">&copy; 2018 <spring:message code="application.title"/>.</p>
				<p class="right"><a href="http://zypopwebtemplates.com/">Free CSS Templates</a> by ZyPOP</p>
			</div>
		</footer>
</body>
</html>
