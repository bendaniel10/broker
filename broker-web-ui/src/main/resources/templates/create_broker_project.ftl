<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker</title>
</head>
<body>
<div class="container">
    <#include "/general_page_nav.ftl">
    <h2>New Project</h2>
    <br/>
    <form method="post" action="/create_broker_project">
        <div class="form-group">
            <label for="name">Project name</label>
            <input class="form-control" id="name" placeholder="Give your project a name" name="name" required/>
        </div>
        <div class="form-group">
            <label for="description">Project description</label>
            <textarea class="form-control" id="description" name="description" placeholder="An optional description" cols="40" rows="5"></textarea>
        </div>
        <div class="form-group">
            <label for="originalUrl">Original URL</label>
            <input class="form-control" id="originalUrl" placeholder="Your current base url" name="originalUrl" aria-describedby="originalUrlHelpBlock" required/>
            <small id="originalUrlHelpBlock" class="form-text text-muted">
                This is your base actual base url. For example: http://www.myproject.com
            </small>
        </div>
        <div class="btn-toolbar" role="toolbar" aria-label="Tool bar for create project actions.">
            <div class="btn-group" role="group" aria-label="Go back">
                <a class="btn btn-outline-secondary" role="button" href="javascript:history.back()">Go Back</a>
            </div>
            &nbsp;&nbsp;
            <div class="btn-group" role="group" aria-label="Create project">
                <input class="btn btn-primary" type="submit" value="Create Project"/>
            </div>
        </div>
    </form>
</div>
<#include "/general_page_script.ftl">
</body>
</html>