<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <h1 class="bordered-bottom">Response rules for ${brokerProjectName}</h1>

    <a href="/create_broker_project_response_rule?brokerProjectToken=${brokerProjectToken}" class="btn btn-primary float-right">Create Response Rule</a>
    <br/><br/>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Url Trigger</th>
            <th>Response body</th>
            <th>Response headers</th>
            <th>Response code</th>
        </tr>
        </thead>
        <tbody>
        <#list rules as rule>
        <tr>
            <td>${rule.urlTrigger}</td>
            <td>${rule.body}</td>
            <td>${rule.headers}</td>
            <td>${rule.httpResponseCode}</td>
        </tr>
        </#list>
    </tbody>
</table>
</div>
<#include "/general_page_script.ftl">
</body>
        </html>
