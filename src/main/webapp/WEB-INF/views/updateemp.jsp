<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Edit</title>
<link rel="stylesheet" type="text/css"	href="<c:url value="/resources/css/style.css"/>">
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<div class="header">Payroll Application</div>
	<!-- -<div >${user.userName}</div> -->
	<div class="menu">
		<div class="menu-item selected">
			<a href="">Employees</a>
		</div>
		<div class="menu-item">
			<a href="">Departments</a>
		</div>
		<div class="menu-item">
			<a href="">Skills</a>
		</div>

	</div>
	<div class="activity">
		<h1>Update Employee</h1>
		<c:out value="${error}" />
		<c:out value="${success}" />
		<form:form modelAttribute="employee" method="post" action="/payrollm1/app/employee/modify">
			<!--  	<form action="modifyemployee" method="POST" name="employeeForm">-->
			<table width="100%">
				<tr>
					<td class="lable">Name</td>
					<td class="lable">Date of Birth</td>
					<td class="lable">Gender</td>
					<td class="lable">Salary</td>
				</tr>
				<tr>
					<!--   	<td><input type="hidden" name="employeeId"
					value="${employee.id}">
					  <input type="hidden" name="addressId" value="${employee.address.id}"> -->

					<td><form:input type="hidden" path="id" />
					 <form:input type="hidden" path="address.id" /> 
					 <form:input type="dob" path="name" class="text-box" /></td>
					<td><form:input type="text" path="dob" class="text-box" /></td>
					<td><form:radiobutton path="gender" value="M" />Male
					 <form:radiobutton path="gender" value="F" />Female</td>
					<td><form:input type="text" path="salary" class="text-box" /></td>
				</tr>
				<tr>
					<td class="lable">Department</td>
					<td class="lable">Address Line 1</td>
					<td class="lable">Address Line 2</td>
					<td class="lable">Locality</td>
				</tr>
			
					<!--  	<td><select name="departmentId">
						<option>[Select Department]</option>
						<c:forEach items="${departments}" var="department">

							<c:if test="${employee.department.id== department.id}">
								<option value="${department.id}" selected>${department.name}</option>
							</c:if>
							<c:if test="${employee.department.id!=department.id }">
								<option value="${department.id}">${department.name}</option>
							</c:if>
						</c:forEach>
				</select></td>-->
						<tr>
					<td><form:select path="department.id">
							<form:option value="0" label="[Select Department]" />
							<form:options items="${departments}" itemLabel="name" itemValue="id" />
						</form:select></td></tr>
				<tr>
					<td><form:input type="text" path="address.address1" /></td>

					<td><form:input type="text" path="address.address2" /></td>

					<td><form:input type="text" path="address.locality" /></td>
				</tr>
				
				
				<tr>
				<td class="lable">Skills</td>
					<td class="lable">Pin code</td>
				</tr>
				<tr>
					<td><c:forEach items="${skills}" var="skill">
							<c:if
								test="${fn:containsIgnoreCase(employee.skills, skill.name)}">
								<input type="checkbox" name="skill" value="${skill.id}" checked>${skill.name}
					</c:if>
							<c:if
								test="${!fn:containsIgnoreCase(employee.skills, skill.name)}">
								<input type="checkbox" name="skill" value="${skill.id}">${skill.name}
					</c:if>
						</c:forEach></td>
					
						
						<td><form:input type="text" path="address.pincode" class="text-box"/></td>
				</tr>
			</table>
			<input type="submit" value="Save Employee" class="btn">
		</form:form>
	</div>
</body>
</html>