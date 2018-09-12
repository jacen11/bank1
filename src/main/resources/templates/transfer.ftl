<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>
    <@l.logout />
</div>
Страница перевода денег
<form method="post" action="/transfer">
    <input name="customerTo" required pattern="[0-9]{10}" placeholder="Введите номер паспорта получателя" />
    <input required pattern="[0-9]" name="amount" placeholder="Введите сумму">
    <button type="submit">Добавить</button>
</form>
</body>
</html>