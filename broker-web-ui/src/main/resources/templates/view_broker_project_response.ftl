<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2>Response rules for ${brokerProjectName}</h2>
    <br/>
    <div class="btn-toolbar float-right" role="toolbar" aria-label="Tool bar for response rules actions.">
        <div class="btn-group" role="group" aria-label="Go back">
            <a role="button" class="btn btn-outline-secondary" href="javascript:history.back()">Go Back</a>
        </div>
        &nbsp;&nbsp;
        <div class="btn-group" role="group" aria-label="Create response rule">
            <a role="button" href="/create_broker_project_response_rule?brokerProjectToken=${brokerProjectToken}" class="btn btn-primary">Create Response Rule</a>
        </div>
    </div>
    <br/><br/>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Url Trigger</th>
                <th>Response body</th>
                <th>Response headers</th>
                <th>Response code</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list rules as rule>
            <tr>
                <td>${rule.urlTrigger}</td>
                <td>${rule.body}</td>
                <td>${rule.headers}</td>
                <td>${rule.httpResponseCode}</td>
                <td>
                    <a href="/edit_broker_project_response_rule?brokerProjectRuleId=${rule.brokerProjectRuleId}">Edit rule</a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
    </div>
</div>
<#include "/general_page_script.ftl">
</body>
        </html>
