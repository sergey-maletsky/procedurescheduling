<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Procedure scheduling project</title>

    <link rel="stylesheet" type="text/css" media="all" href="/css/main.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/jquery-ui.structure.min.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/jquery-ui.theme.min.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/jquery.timepicker.min.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="/css/bootstrap-datepicker.min.css"/>

    <script src="/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="/js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="/js/jquery.timepicker.min.js" type="text/javascript"></script>
    <script src="/js/datepair.min.js" type="text/javascript"></script>
    <script src="/js/jquery.datepair.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <script src="/js/main.js" type="text/javascript"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<p><a href="${contextPath}/">Go to the main page</a></p>
<p><a href="${contextPath}/studies/add">Go to a study adding</a></p>

Patient creation
<form id="patient" class="entity-area" name="add_patient" action="" method="post">
    <div>Name</div>
    <textarea id="name" name="name"></textarea>
    <p>
        <select name="gender">
            <c:forEach items="${genders}" var="gender">
                <option value="${gender}">${gender}</option>
            </c:forEach>
        </select>
    </p>
    <div>Date of birth</div>
    <p>
        <input id="date" class="date" type="text" name="dateOfBirth"/>
    </p>
    <button id="patient-submit">Add patient</button>
</form>
<div id="error-field" class="red"></div>
</body>
</html>