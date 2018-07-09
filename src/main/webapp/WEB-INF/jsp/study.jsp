<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
<p><a href="${contextPath}/patients/add">Go to a patient adding</a></p>

<form id="study-edit" name="study-edit" action="" method="put">
    <table>
        <caption>Studies table</caption>
        <tr>
            <th>Id</th>
            <th>Description</th>
            <th>Start time</th>
            <th>End time</th>
            <th>Doctor name</th>
            <th>Patient name</th>
            <th>Room name</th>
            <th>Status</th>
            <th></th>
        </tr>
        <tr>
            <td id="study_id">${study.id}</td>
            <td>${study.description}</td>
            <td>${study.startTime}</td>
            <td>${study.endTime}</td>
            <td>${study.doctorName}</td>
            <td>${study.patientName}</td>
            <td>${study.roomName}</td>
            <td>
                <select name="status">
                    <option value="" disabled selected class="select-placeholder">statuses</option>
                    <c:forEach items="${studyStatuses}" var="studyStatus">
                        <c:choose>
                            <c:when test="${study.status == studyStatus}">
                                <option selected value="${studyStatus}">${studyStatus}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${studyStatus}">${studyStatus}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td><button id="study-edited-submit">Edit</button></td>
        </tr>
    </table>
</form>

<div id="error-field" class="entity-area red"></div>
</body>
</html>