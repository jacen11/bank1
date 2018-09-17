<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<p>${greeting?ifExists}</p>

    <#list bankAccounts! as bankAccount>
    <div>
        <b>${bankAccount.id}</b>
        <b>${bankAccount.balance}</b>
        <span>${bankAccount.nameAccount}</span>
        <span>${bankAccount.numberBankAccount}</span>
    </div>
    <#else>
        Счетов нет
    </#list>


    <form action="/bankAccounts" method="post">
        <div><label> Название счета <input type="text" name="nameAccount"/> </label> <input type="submit"
                                                                                            value="Создать"/></div>
    <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
    </form>

    <form action="/bankAccounts" method="post">
        <div><label> Удаление счета (введите id счета) <input type="text" required pattern="[0-9]{1,10}"
                                                              name="deleteNameAccount"/> </label> <input type="submit"
                                                                                                         value="Удалить"/>
        </div>
    <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
    </form>
<a href="/transfer">Сделать перевод</a>
<p>${success?ifExists}</p>

<div>
    <@l.logout />
</div>

</@c.page>