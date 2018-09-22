<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="app-title">
        <h1>Страница перевода денег</h1>
    </div>

<form method="post" action="/transfer">
    <input name="accountTo"  class="form-control mt-1" required pattern="[0-9]{1,10}" placeholder="Введите номер счета получателя (1-10 цифр)"/>
    <input required pattern="^[0-9]+$"  class="form-control mt-1" name="amount" placeholder="Введите сумму (любое положительное число)">
    <input name="comment"  class="form-control mt-1" placeholder="Введите сообщение получателю">
    <select class="custom-select mt-1" name="bankAccount">
    <#list bankAccounts! as bankAccount>
        <option>${bankAccount.nameAccount}</option>
    <#else>
        <option>Счетов нет</option>
    </#list>
    </select>
    <button type="submit" class="btn btn-primary btn-lg btn-block mt-1">Перевести</button>
</form>

<#--<style>-->
    <#--input.transfer {-->
        <#--width: 400px;-->
    <#--}-->
<#--</style>-->
<div> <p>${error?ifExists}</p></div>
</@c.page>