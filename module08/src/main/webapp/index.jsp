<html>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <title>Deposit</title>
    <link rel="stylesheet" type="text/css" href="styleFin.css">
</head>

<body>

<h1>Калькулятор доходности вклада</h1>

    <form method="POST" action="finance">
        <label>Сумма на момент открытия вклада: </label>
        <input type="text" name="sum"><br>
        <label>Процентная ставка:  </label>
        <input type="text" name="percentage"><br>
        <label>Количество лет:</label>
        <input type="text" name="years"><br>
        <hr>
        <input type="submit" value="Посчитать">
    </form>

</body>

</html>