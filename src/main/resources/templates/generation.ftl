
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

Генерация отчета
<form action="generation" method="post">
    <input type="date" name="from">
    <input type="date" name="to">
    <select name="bankAccount">
    <#list bankAccounts! as bankAccount>
            <option>${bankAccount.nameAccount}</option>
        <#else>
            <option>Счетов нет</option>
        </#list>
        </select>
     <input type="submit" value="Сгенерировать"/>
</form>

</@c.page>
