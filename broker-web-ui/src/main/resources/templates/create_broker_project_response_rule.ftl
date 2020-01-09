<html>
<head>
    <title>Broker</title>
</head>
<body>
<h1>Response rule for ${brokerProjectName}</h1>
<hr/>

<form method="post" action="/create_broker_project_response_rule">
    <input type="hidden" value="${brokerProjectToken}" name="brokerProjectToken"/>
    <input placeholder="Url Trigger" name="urlTrigger"/> <br/>
    <textarea name="responseBody" placeholder="Response body" cols="40" rows="5"></textarea> <br/>
    <small>Each header must be in a new line in this format: key=value</small> <br/>
    <textarea name="headers" placeholder="Response Headers" cols="40" rows="5"></textarea> <br/>
    <input placeholder="Response code" name="httpResponseCode" type="number"/> <br/>
    <input type="submit" value="Submit"/> <br/>
</form>
</body>
</html>