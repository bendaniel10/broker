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
                <td><a href="#" data-toggle="modal" data-target="#response_id_${rule.brokerProjectRuleId}">view response</a></td>
                <td>${rule.headers}</td>
                <td>${rule.httpResponseCode}</td>
                <td>
                    <a class="btn-link" href="/edit_broker_project_response_rule?brokerProjectRuleId=${rule.brokerProjectRuleId}">Edit</a>
                    &#124;
                    <form method="post" action="/delete_broker_project_response_rule" class="d-inline-block" id="rule_id_${rule.brokerProjectRuleId}">
                        <input type="hidden" name="brokerProjectRuleId" value="${rule.brokerProjectRuleId}" />
                        <input type="hidden" name="brokerProjectToken" value="${brokerProjectToken}" />
                        <a href="javascript:{}" onclick="if(confirm('Are you sure you want to delete ${rule.urlTrigger}?')) { document.getElementById('rule_id_${rule.brokerProjectRuleId}').submit()}; return false; ">
                        Delete
                        </a>
                    </form>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
    </div>

    <#list rules as rule>
        <div class="modal fade bd-example-modal-xl" id="response_id_${rule.brokerProjectRuleId}" tabindex="-1" role="dialog" aria-labelledby="title_${rule.brokerProjectRuleId}" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="title_${rule.brokerProjectRuleId}">${rule.urlTrigger}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <pre>${rule.body}</pre>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
<#include "/general_page_script.ftl">
</body>
        </html>
