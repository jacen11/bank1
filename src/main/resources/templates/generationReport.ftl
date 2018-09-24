<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<h1>Генерация отчета</h1>
<form action="generationReport" method="post">
    <div class="row">
        <div class="col">
            <input type="date" class="form-control mt-1" name="fromString">
        </div>
        <div class="col">
            <input type="date" class="form-control mt-1" name="toString">
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
            <input type="submit" class="btn btn-primary btn-block mt-1" value="Сгенерировать"/>
        </div>
    </div>
</form>

</@c.page>
