<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <a class="navbar-brand" href="/">Банк</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/generationReport">Отчеты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/transfer">Переводы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/bankAccounts">Счета</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/replenishment">Пополнить баланс</a>
            </li>

        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
    </div>
</nav>
