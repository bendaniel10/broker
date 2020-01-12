<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2 class="bordered-bottom">Response rule for ${brokerProjectName}</h2>
    <form method="post" action="/create_broker_project_response_rule">
        <input class="form-control" type="hidden" value="${brokerProjectToken}" name="brokerProjectToken"/>
        <div class="form-group">
            <label for="urlTrigger">URL trigger</label>
            <input id="urlTrigger" placeholder="my/url/trigger" name="urlTrigger" class="form-control"/>
            <small id="urlTriggerHelpBlock" class="form-text text-muted" aria-describedby="urlTriggerHelpBlock">
                This is the path after your base url, excluding the first /. E.g ${originalUrl}/change/me/to/set/trigger
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
        <input class="btn btn-primary" type="submit" value="Submit"/>
        <a href="javascript:history.back()">Go Back</a>
    </form>
    <#include "/general_page_script.ftl">
</div>
</body>
</html>