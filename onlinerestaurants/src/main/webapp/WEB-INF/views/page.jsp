<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Restaurants</title>
</head>
<body>
<%--  ${contextRoot} - Says ${greeting} --%>
 
 <table align="center">
            <tr>
                <td><a href="login">Login</a>
                </td>
                <td><a href="register">Register</a>
                </td>
            </tr>
        </table>
</body>
</html>