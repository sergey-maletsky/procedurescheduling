<%@ taglib prefix="scheduling" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<scheduling:includes/>

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
            <td><button id="study-edited-submit">Apply</button></td>
        </tr>
    </table>
</form>

<div id="error-field" class="red"></div>
</body>
</html>