<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2 class="border-bottom">New Project</h2>
    <form method="post" action="/create_broker_project">
        <div class="form-group">
            <label for="name">Project name</label>
            <input class="form-control" id="name" placeholder="Give your project a name" name="name" />
        </div>
        <div class="form-group">
            <label for="description">Project description</label>
            <textarea class="form-control" id="description" name="description" placeholder="An optional description" cols="40" rows="5"></textarea>
        </div>
        <div class="form-group">
            <label for="originalUrl">Original URL</label>
            <input class="form-control" id="originalUrl" placeholder="Your current base url" name="originalUrl" aria-describedby="originalUrlHelpBlock"/>
            <small id="originalUrlHelpBlock" class="form-text text-muted">
                This is your base actual base url. For example: http://www.myproject.com
            </small>
        </div>
        <input class="btn btn-primary" type="submit" value="Create Project"/>
        <a href="javascript:history.back()">Go Back</a>
    </form>
</div>
<#include "/general_page_script.ftl">
</body>
</html>