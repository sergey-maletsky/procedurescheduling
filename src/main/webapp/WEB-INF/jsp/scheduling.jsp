<%@ taglib prefix="scheduling" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<scheduling:includes/>

<p><a href="${contextPath}/">Go to the main page</a></p>
<p><a href="${contextPath}/patients/add">Go to a patient adding</a></p>

Study creation
<form id="study" class="entity-area" name="add_study" action="" method="post">
    <div>Description</div>
    <textarea id="description" name="description"></textarea>

    <p>
    <div>Patients</div>
    <select id="patient" name="patientName">
        <option value=""></option>
        <c:forEach items="${patients}" var="patient">
            <option value="${patient.name}">${patient.name}</option>
        </c:forEach>
    </select>
    </p>

    <p>
    <div>Doctors</div>
    <select id="doctor" name="doctorName">
        <option value=""></option>
        <c:forEach items="${doctors}" var="doctor">
            <option value="${doctor.name}">${doctor.name}</option>
        </c:forEach>
    </select>
    </p>

    <p>
    <div>Rooms</div>
    <select id="room" name="roomName">
        <option value=""></option>
        <c:forEach items="${rooms}" var="room">
            <option value="${room.name}">${room.name}</option>
        </c:forEach>
    </select>
    </p>

    <p id="study_time">
        Time: <input id="start_time" class="start time" type="text" name="startTime"/> to
        <input id="end_time" class="end time" type="text" name="endTime"/>
    </p>
    <p>
        <select name="status">
            <c:forEach items="${studyStatuses}" var="studyStatus">
                <option value="${studyStatus}">${studyStatus}</option>
            </c:forEach>
        </select>
    </p>
    <button id="study-submit">Add study</button>
</form>
<div id="error-field" class="red"></div>
</body>
</html>