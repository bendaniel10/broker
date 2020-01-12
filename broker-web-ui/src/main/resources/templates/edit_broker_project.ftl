<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2 class="border-bottom">Edit Project (${brokerProjectName})</h2>
    <form method="post" action="/edit_broker_project">
        <input class="form-control" type="hidden" value="${brokerProjectToken}" name="brokerProjectToken"/>
        <div class="form-group">
            <label for="name">Project name</label>
            <input class="form-control" id="name" placeholder="Give your project a name" name="name" value="${brokerProjectName}" />
        </div>
        <div class="form-group">
            <label for="description">Project description</label>
            <textarea class="form-control" id="description" name="description" placeholder="An optional description" cols="40" rows="5">${brokerDescription}</textarea>
        </div>
        <div class="form-group">
            <label for="originalUrl">Original URL</label>
            <input class="form-control" id="originalUrl" placeholder="Your current base url" name="originalUrl" aria-describedby="originalUrlHelpBlock" value="${brokerOriginalUrl}"/>
            <small id="originalUrlHelpBlock" class="form-text text-muted">
                This is your base actual base url. For example: http://www.myproject.com
            </small>
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox"  id="brokerProjectEnabled" name="brokerProjectEnabled" value="true" <#if brokerProjectEnabled> checked </#if> />
                <label class="form-check-label" for="brokerProjectEnabled">
                    Enabled
                </label>
            </div>
        </div>
        <input class="btn btn-primary" type="submit" value="Update project"/>
        <a href="javascript:history.back()">Go Back</a>
    </form>
</div>
<#include "/general_page_script.ftl">
</body>
</html>