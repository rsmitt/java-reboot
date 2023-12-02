<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<title>Калькулятор доходности вклада</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Калькулятор доходности вклада</h1>
<form method="post">
<table>
	<tr>
		<td id="one">Сумма на момент открытия</td>
		<td><input id="numb" type="number" step="any" name="sum"></td>
	</tr>
	<tr>
		<td>Процентная ставка</td>
		<td><input id="numb" type="number" step="any" name="percentage"></td>
	</tr>
	<tr>
		<td>Количество лет</td>
		<td><input id="numb" type="number" step="any" name="years"></td>
	</tr>
</table>
<br>
<input id="button" type="submit" value="Посчитать">
</form>
</body>
</html>