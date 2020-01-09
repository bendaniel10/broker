<html>
<head>
    <title>Broker</title>
</head>
<body>
<h1>Projects</h1>
<hr/>
<a href="/create_broker_project">Create Project >>></a>
<table>
    <tr>
        <th>Name</th>
        <th>Original url</th>
        <th>Token</th>
        <th>Enabled</th>
        <th>Rules</th>
    </tr>
    <#list brokerProjects as project>
    <tr>
        <td>${project.name}</td>
        <td>${project.originalUrl}</td>
        <td>${project.token}</td>
        <td>${project.enabled}</td>
        <td><a href="/view_broker_project_response?brokerProjectToken=${project.token}">View response rule</a></td>
    </tr>
</#list>
</table>
</body></html>