<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2>Edit response rule for (${brokerProjectName})</h2>
    <br/>
    <form method="post" action="/edit_broker_project_response_rule">
        <input class="form-control" type="hidden" value="${brokerProjectRuleId}" name="brokerProjectRuleId"/>
        <input class="form-control" type="hidden" value="${brokerProjectToken}" name="brokerProjectToken"/>
    <div class="form-group">
            <label for="urlTrigger">URL trigger</label>
            <input id="urlTrigger" placeholder="my/url/trigger" name="urlTrigger" class="form-control" value="${urlTrigger}"/>
            <small id="urlTriggerHelpBlock" class="form-text text-muted" aria-describedby="urlTriggerHelpBlock">
                This is the path after your base url, excluding the first /. E.g ${originalUrl}/<strong>change/me/to/set/trigger</strong>
            </small>
        </div>
        <div class="form-group">
            <label for="responseBody">Response body</label>
            <textarea class="form-control" id="responseBody" name="responseBody" placeholder="Optional response that should be returned whenever this URL is called." cols="40" rows="5">${responseBody}</textarea>
        </div>
        <div class="form-group">
            <label for="headers">Response body</label>
            <textarea class="form-control" id="headers" name="headers" placeholder="key=value" cols="40" rows="5" aria-describedby="headersHelpBlock">${headers}</textarea>
            <small id="headersHelpBlock" class="form-text text-muted" aria-describedby="urlTriggerHelpBlock">
                Each header must be in a new line in this format: key=value
            </small>
        </div>
        <div class="form-group">
            <label for="httpResponseCode">Response code</label>
            <input value="${httpResponseCode}" class="form-control" id="httpResponseCode" placeholder="A valid HTTP status code." name="httpResponseCode" type="number"/>
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox"  id="brokerProjectResponseRuleEnabled" name="brokerProjectResponseRuleEnabled" value="true" <#if brokerProjectResponseRuleEnabled> checked </#if> />
                <label class="form-check-label" for="brokerProjectResponseRuleEnabled">
                    Enabled
                </label>
            </div>
        </div>
        <div class="btn-toolbar" role="toolbar" aria-label="Tool bar for edit project response rule actions.">
            <div class="btn-group" role="group" aria-label="Go back">
                <a class="btn btn-outline-secondary" role="button" href="javascript:history.back()">Go Back</a>
            </div>
            &nbsp;&nbsp;
            <div class="btn-group" role="group" aria-label="edit project response rule">
                <input class="btn btn-primary" type="submit" value="Submit"/>
            </div>
        </div>
    </form>
    <#include "/general_page_script.ftl">
</div>
</body>
</html>