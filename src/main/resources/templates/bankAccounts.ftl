<#import "parts/common.ftl" as c>

<@c.page>

<p>${bankAccounts?ifExists}</p>
<p>${messages?ifExists}</p>

    <form action="/bankAccounts" method="post">
        <div><label> Название счета <input type="text" name="nameAccount"/> </label></div>
        <#--<div><label> Пароль: <input type="password" name="password"/> </label></div>-->
    <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
        <div><input type="submit" value="Создать"/></div>
    </form>

</@c.page>