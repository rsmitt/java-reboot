<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <style>
        <%@include file="style.css"%>
    </style>
    <title>finance</title>
</head>
<body>
<h1>Калькулятор доходности вклада</h1>
<div class="form">
    <form method="post" action="" modelAttribute="bank">
        Сумма на момент открытия: <input type="text" id="sum" name="sum"/>
        <br>
        Процентная ставка: <input type="text" id="percentage" name="percentage"/>
        <br>
        Количество лет: <input type="text" id="years" name="years"/>
        <br>
        <hr>
        <input type="submit" value="Посчитать">
    </form>
</div>
</body>
</html>