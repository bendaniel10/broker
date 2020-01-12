<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2>Response rule for ${brokerProjectName}</h2>
    <br/>
    <form method="post" action="/create_broker_project_response_rule">
        <input class="form-control" type="hidden" value="${brokerProjectToken}" name="brokerProjectToken"/>
        <div class="form-group">
            <label for="urlTrigger">URL trigger</label>
            <input id="urlTrigger" placeholder="my/url/trigger" name="urlTrigger" class="form-control"/>
            <small id="urlTriggerHelpBlock" class="form-text text-muted" aria-describedby="urlTriggerHelpBlock">
                This is the path after your base url, excluding the first /. E.g ${originalUrl}/<strong>change/me/to/set/trigger</strong>
            </small>
        </div>
        <div class="form-group">
            <label for="responseBody">Response body</label>
            <textarea class="form-control" id="responseBody" name="responseBody" placeholder="Optional response that should be returned whenever this URL is called." cols="40" rows="5"></textarea>
        </div>
        <div class="form-group">
            <label for="headers">Response body</label>
            <textarea class="form-control" id="headers" name="headers" placeholder="key=value" cols="40" rows="5" aria-describedby="headersHelpBlock"></textarea>
            <small id="headersHelpBlock" class="form-text text-muted" aria-describedby="urlTriggerHelpBlock">
                Each header must be in a new line in this format: key=value
            </small>
        </div>
        <div class="form-group">
            <label for="httpResponseCode">Response code</label>
            <input class="form-control" id="httpResponseCode" placeholder="A valid HTTP status code." name="httpResponseCode" type="number"/>
        </div>
        <div class="btn-toolbar" role="toolbar" aria-label="Tool bar for create project response rule actions.">
            <div class="btn-group" role="group" aria-label="Go back">
                <a class="btn btn-outline-secondary" role="button" href="javascript:history.back()">Go Back</a>
            </div>
            &nbsp;&nbsp;
            <div class="btn-group" role="group" aria-label="Create project response rule">
                <input class="btn btn-primary" type="submit" value="Submit"/>
            </div>
        </div>
    </form>
    <#include "/general_page_script.ftl">
</div>
</body>
</html>