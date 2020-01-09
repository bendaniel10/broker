<html>
<head>
    <title>Broker</title>
</head>
<body>
<h1>Response rules for ${brokerProjectName}</h1>
<hr/>
<a href="/create_broker_project_response_rule?brokerProjectToken=${brokerProjectToken}">Create Response Rule >>></a>
<table>
    <tr>
        <th>Url Trigger</th>
        <th>Response body</th>
        <th>Response headers</th>
        <th>Response code</th>
    </tr>
    <#list rules as rule>
    <tr>
        <td>${rule.urlTrigger}</td>
        <td>${rule.body}</td>
        <td>${rule.headers}</td>
        <td>${rule.httpResponseCode}</td>
    </tr>
</#list>
</table>
</body>
        </html>
