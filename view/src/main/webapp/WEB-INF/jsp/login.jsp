<%@page contentType="text/html" language = "java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   <head>
      <title>Hello Spring MVC</title>
   </head>

   <body>
      <h2>Hello, <c:out value="${login}"/></h2>
      <p>Your password is ${password}</p>
   </body>
</html>