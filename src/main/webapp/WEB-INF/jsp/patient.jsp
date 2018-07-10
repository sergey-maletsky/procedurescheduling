<%@ taglib prefix="scheduling" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<scheduling:includes/>

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