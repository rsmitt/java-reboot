<html>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <title>Deposit</title>
    <link rel="stylesheet" type="text/css" href="styleFin.css">
</head>

<body>

<h1>Результат</h1>

<p>Итоговая сумма
<%= String.format("%8.2f", Integer.parseInt(request.getParameter("sum")) *
                    (Math.pow(1.0 + (Integer.parseInt(request.getParameter("percentage")) / 100.0 / 12),
                            Integer.parseInt(request.getParameter("years")) * 12)))%> рублей</p>

</body>

</html>