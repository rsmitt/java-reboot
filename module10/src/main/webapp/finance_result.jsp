<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="style.css"%>
    </style>
    <title>finance</title>
</head>
<body>
<h1>Результат</h1>
<div class="form">
    <span>Итоговая сумма = <c:out value="${result}"/></span>
</div>
</body>
</html>