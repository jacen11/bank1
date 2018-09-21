<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Добавление нового клиента
    <p>${message?ifExists}</p>
    <@l.login "/registration" true/>
</@c.page>
