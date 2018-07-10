<%@ taglib prefix="scheduling" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<scheduling:includes/>

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
        <th></th>
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
                <tr onclick="window.location.href='${contextPath}/studies/edit/${study.id}#'">
                    <td><a href="${url}">${count.index + 1}</a></td>
                    <td>${study.description}</td>
                    <td>${study.startTime}</td>
                    <td>${study.endTime}</td>
                    <td>${study.doctorName}</td>
                    <td>${study.patientName}</td>
                    <td>${study.roomName}</td>
                    <td>${study.status}</td>
                    <td>Edit</td>
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