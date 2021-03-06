<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout />
    <span><a href="/user">UserList</a> </span>
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение" />
        <input type="text" name="tag" placeholder="Тэг">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">add</button>
    </form>
</div>
<div>List of messages</div>
<form method="get" action="/main">
    <input type="text" name="filter">
    <button type="submit">Find</button>
</form>
    <#list messages as message>
<div>
    <b>${message.id!}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
</div>
    <#else>
No message
    </#list>
</@c.page>