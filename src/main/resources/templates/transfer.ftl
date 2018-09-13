<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="app-title">
        <h1>Страница перевода денег</h1>
    </div>

<form method="post" action="/transfer">
    <input name="customerTo" required pattern="[0-9]{10}" placeholder="Введите номер паспорта получателя (10 цифр)"
           class="transfer"/>
    <input required pattern="[0-9]" name="amount" placeholder="Введите сумму (любое положительное число)"
           class="transfer">
    <select name="select">
        <option value="bank_1">Банк 1</option>
        <option value="bank_2">Банк 2</option>
    </select>
    <button type="submit">Перевести</button>
</form>

<style>
    input.transfer {
        width: 300px;
    }
</style>

<div>
    <@l.logout />
</div>
</@c.page>