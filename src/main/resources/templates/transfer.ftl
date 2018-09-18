<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="app-title">
        <h1>Страница перевода денег</h1>
    </div>

<form method="post" action="/transfer">
    <input name="accountTo" required pattern="[0-9]{1,10}" placeholder="Введите номер счета получателя (1-10 цифр)"
           class="transfer"/>
    <input required pattern="^[0-9]+$" name="amount" placeholder="Введите сумму (любое положительное число)"
           class="transfer">
    <input name="comment" placeholder="Введите сообщение получателю"
           class="transfer">
    <select name="bankAccount">
    <#list bankAccounts! as bankAccount>
        <option>${bankAccount.nameAccount}</option>
    <#else>
        <option>Счетов нет</option>
    </#list>
    </select>
    <button type="submit">Перевести</button>
</form>

<style>
    input.transfer {
        width: 400px;
    }
</style>
<div> <p>${error?ifExists}</p></div>
<div><a href="/generation">Получить Json со всеми транзакциями</a></div>
<div><a href="/bankAccounts">Перейти к управлению счетами</a></div>
<div>
    <@l.logout />
</div>
</@c.page>