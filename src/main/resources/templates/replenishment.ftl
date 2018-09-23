<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div><h1>Пополнить баланс</h1></div>

<form action="replenishment" method="post">
    <div class="row">
         <div class="col">
            <input type="text" class="form-control mt-1" name="cash">
        </div>
        <div class="col">
            <select name="bankAccount" class="custom-select mt-1">
        <#list bankAccounts! as bankAccount>
            <option>${bankAccount.id}</option>
        <#else>
                <option>Счетов нет</option>
        </#list>
            </select>
        </div>
        <div class="col">
            <input type="submit" class="btn btn-primary btn-block mt-1" value="Пополнить"/>
        </div>
    </div>
</form>

</@c.page>