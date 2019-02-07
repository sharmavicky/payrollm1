<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Employee List</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.2/css/all.css"
	integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns"
	crossorigin="anonymous">

</head>


<body style="line-height: 1;">
	<nav class="navbar navbar-dark bg-primary">
		<div class="navbar-brand">Payroll Application</div>
      </nav>
	
	
	
	
	<div class="container-fluid" style="padding-right: 0px; height: 100%;">

		<!-- - 	<div class="header">  Payroll Application</div>-->
		<!--  	<div >${user.userName}</div>-->


		<div class="row py-row" style="height: 100%;">


			<div class="col-sm-12 col-lg-2 menu">
				<div class="menu-item selected">
					<a>Employee</a>
				</div>


				<div class="menu-item">
					<a href="">Department</a>
				</div>
				<div class="menu-item">
					<a href="">Skills</a>
				</div>
			</div>




			<div class="col-sm-12 col-lg-10" style="padding-right: 0px;">
				<h2 style="color: navy">Employee List</h2>
				<div class="row py-row">
					<c:forEach items="${employees}" var="employee">

						<div class="col-lg-4"
							style="border: 1px solid black; background-color: #f2f2f2; border-collapse: none; padding-right: 0px; margin-top: 5px;">
							<div class="row py-row">

								<!--  	<div class="col-sm-4">${employee.id}</div> -->

								<div class="col-4 col-sm-4 col-lg-4"
									style="padding-right: 0px; padding-left: 0px; margin: 0px;">
									<div>
										<img
											src="<c:url value="/resources/images/${employee.name}.png"/>"
											style="width: 100%; border-radius: 50%; padding: 10px">
									</div>
								</div>
								<div class="col-8 col-sm-8 col-lg-8"
									style="padding-right: 0px; padding-left: 0px; margin: 0px; width: 100%; height: 100%;">
									<div class="card-header"
										style="padding: 2px; background-color: #f2f2f2; line-height: 1.2; margin-bottom: 8px">


										<div style="font-size: 25px; font-weight: bold; color: navy;">${employee.name}
										</div>

										<div>
											<a class="card-subtitle"
												style="font-size: 14px; color: #6666cc; font-style: italic;">(${employee.department.name})
											</a>
										</div>
									</div>
									<div class="details-text">
										<i class="fas fa-envelope"> </i> xyz@gmail.com
									</div>

									<div class="details-text">
										<i class="fas fa-medal"></i>
										<c:forEach items="${employee.skillList}" var="skill">
						                      	${skill.name}, </c:forEach>
									</div>
									<div class="details-text">
										<i class="fas fa-map-marker-alt"></i>
										${employee.address.address1}, ${employee.address.address2},
										${employee.address.locality}, - ${employee.address.pincode}
									</div>

								</div>
							</div>

							<div class="row py-row">
								<div class="col-sm-12 col-lg-12"
									style="margin: 0px; padding-right: 0px; padding-left: 0px; width: 100%;">


									<div class="details-text"
										style="text-align: center; border-top: 2px solid navy">
										<i class="fas fa-genderless"></i> ${employee.gender} &nbsp; <i
											class="fas fa-birthday-cake"></i> ${employee.dob} &nbsp; <i
											class="fas fa-credit-card"></i> &#x20b9;
										<fmt:formatNumber value="${employee.salary}" />
										<a href="<c:url value="/employee/show/${employee.id}"/>"
											style="padding-right: 8px; font-size: 15px; float: right; color: grey;"><i
											class="fas fa-user-edit"></i></a>
									</div>
								</div>
							</div>

						</div>
					</c:forEach>
				</div>

			</div>
		</div>

	</div>


</body>

</html>
