<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Страница для входа
    <@l.login "/login" />
<a href="/registration">Добавить нового пользователя</a>
</@c.page>
