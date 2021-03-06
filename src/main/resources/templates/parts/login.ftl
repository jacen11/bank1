<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Номер паспорта (10 цифр):</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" required pattern="[0-9]{10}" placeholder="Логин" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Пароль:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Пароль" />
        </div>
    </div>
    <#if !isRegisterForm><a href="/registration">Добавить клиента</a></#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Создать<#else>Войти</#if></button>

</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <button class="btn btn-primary" type="submit">Sign Out</button>
</form>
</#macro>