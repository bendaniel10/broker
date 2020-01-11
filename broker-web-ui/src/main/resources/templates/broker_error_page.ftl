<!doctype html>
<html>
<head>
    <#include "/general_page_style.ftl">
    <title>Broker - Error</title>
</head>
<body>
    <div class="container">
        <div class="card text-center">
            <div class="card-body">
                <h5 class="card-title">An error has occured.</h5>
                <p class="card-text">${errorMessage}</p>
                <a href="/" class="btn btn-primary">Back to home</a>
            </div>
        </div>
    </div>
</body>
</html>