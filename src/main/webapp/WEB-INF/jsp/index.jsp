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

    <script src="/js/main.js" type="text/javascript"></script>
    <script src="/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="/js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="/js/jquery.timepicker.min.js" type="text/javascript"></script>
    <script src="/js/datepair.min.js" type="text/javascript"></script>
    <script src="/js/jquery.datepair.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap-datepicker.min.js" type="text/javascript"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<p><a href="${contextPath}/studies/add">Go to a study adding</a></p>
<p><a href="${contextPath}/patients/add">Go to a patient adding</a></p>
<table>
    <caption>Studies table</caption>
    <tr>
        <th>##</th>
        <th>Description</th>
        <th>Start time</th>
        <th>End time</th>
        <th>Doctor name</th>
        <th>Patient name</th>
        <th>Room name</th>
        <th>Status</th>
    </tr>
    <c:choose>
        <c:when test="${empty studies}">
            <tr>
                <td colspan="6">
                    <p>
                        Data not found.
                    </p>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${studies}" var="study" varStatus="count">
                <spring:url value="/studies/edit/${study.id}#" var="url"/>
                <tr onclick="window.location.href='${url}'">
                    <td><a href="${url}">${count.index + 1}</a></td>
                    <td>${study.description}</td>
                    <td>${study.startTime}</td>
                    <td>${study.endTime}</td>
                    <td>${study.doctorName}</td>
                    <td>${study.patientName}</td>
                    <td>${study.roomName}</td>
                    <td>${study.status}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<table>
    <caption>Patient table</caption>
    <tr>
        <th>##</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Date of birth</th>
    </tr>
    <c:choose>
        <c:when test="${empty patients}">
            <tr>
                <td colspan="6">
                    <p>
                        Data not found.
                    </p>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${patients}" var="patient" varStatus="count">
                <tr>
                    <td>${count.index + 1}</td>
                    <td>${patient.name}</td>
                    <td>${patient.gender}</td>
                    <td>${patient.dateOfBirth}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>