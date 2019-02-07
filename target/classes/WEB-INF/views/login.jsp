<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Payroll - Login</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.2/css/all.css"
	integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>



<body>
	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<!-- ******************************************         LOGIN       ***************************************************************************         -->
	<div id="login">
		<nav class="navbar navbar-dark bg-primary" id="nav">
			<div class="navbar-brand">Payroll Application</div>
		</nav>


		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<h3>Login</h3>
					<c:out value="${error}" />
					<form action="authenticate" method="post">
						<div class="container">

							<div class="form-group">
								<label for="username">Username</label> <input type="text"
									name="userName" class="form-control" value="${userName}"
									placeholder="Username" id="userName">
							</div>

							<div class="form-group">
								<label for="password">Password</label> <input type="text"
									name="password" class="form-control" placeholder="Password"
									id="password">
							</div>
							<!--   <input type="submit" class="btn btn-outline-primary btn-lg btn-block" value="Login"> -->
							<input type="button" onclick="authenticate()" class="btn btn-outline-primary btn-lg btn-block" value="login">
						</div>
					</form>
					
				</div>
			</div>

		</div>
	</div>


	<!--  **************************************************   EMPLOYEE-LIST  *********************************************************************     -->
	<div id="emplist-navbar">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="navbar-brand"> Payroll App</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarText" aria-controls="navbarText"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav mr-auto">

					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Employees</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Department</a>
					</li>
				</ul>
			</div>
		</nav>
	</div>




	<div id="emplist">
		<div class="container-fluid" style="padding-right: 0px; height: 100%;">
			<div class="row py-row" style="height: 100%;">


				<div class="col-sm-12 col-lg-1"></div>


				<div class="col-sm-12 col-lg-10" style="padding-right: 0px;">

					<h1>Employee List</h1>
					<div id="show-emplist" class="row py-row">
						
						
				<!-- 		<c:forEach items="${employees}" var="employee">

							<div class="col-lg-4" style="border: 1px solid black; background-color: #f2f2f2; border-collapse: none; padding-right: 0px; margin-top: 5px;">
								<div class="row py-row">
									

									

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
						</c:forEach>  -->
					</div>

				</div>
				<div class="col-sm-12 col-lg-1"></div>

			</div>

		</div>
	</div>
</body>

<!-- ********************************UpdateEmployee************************************************* -->

<div id="updateEmp">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="navbar-brand">Payroll App</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarText" aria-controls="navbarText"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav mr-auto">

					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Employees</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Department</a>
					</li>
				</ul>
			</div>
		</nav>
		<div class="updateEmpForm" id="updateEmpForm">
		<form action="authenticate" method="post"  id="form">
						<div class="container">
                            <div class="form-group">
								
								<input type="hidden"   id="employeeId">
							</div>
							<div class="form-group">
								<label for="username">Employee Name</label>
								<input type="text"  placeholder="name" id="name">
							</div>
							<div class="form-group">
								<label for="dob">Date of Birth</label>
								<input type="text" placeholder="dd/mm/yyyy" id="dob">
							</div>
							<div class="form-group">
								<label for="gender">Gender</label>
								<input type="radio"  id="gender" name="gender"value="M"> Male
  								<input type="radio"  id="gender" name="gender" value="F"> Female
							</div>
							<div class="form-group">
								<label for="salary">Salary</label>
								<input type="text" placeholder="$$$" id="salary">
							</div>
							<div class="form-group">
								<input type="hidden"  id="departmentId">
							</div>
							<div class="form-group">
								<label for="username">Department</label>
								<select id="departments" >
								</select>
							</div>
							<div class="form-group">
									<input type="hidden"  id="addressid">
							</div>
							<div class="form-group">
								<label for="addressline1">Address Line1</label>
								<input type="text"  placeholder="Address1" id="address1">
							</div>
							<div class="form-group">
								<label for="Addreddline2">Address Line2</label>
								<input type="text"  placeholder="Address2" id="address2">
							</div>
							<div class="form-group">
								<label for="locality">Locality</label>
								<input type="text"  placeholder="locality" id="locality">
							</div>
							<div class="form-group">
								<label for="pincode">Pincode</label>
								<input type="text" placeholder="pincode" id="pincode">
							</div>
							<div class="form-group">
							<input type="hidden"   id="skillId">
							</div>
							<div class="form-group">
								<label for="skills">Skills</label>
								<div id="skills"></div>
							</div>
							
							<input type="button" onclick="update()" class="btn btn-outline-primary btn-lg btn-block" value="Update">
						</div>
					</form>
		</div>
	</div>
	
	



<!--  *********************************************************  script *************************************  -->

<script>
	function authenticate() {

		$.ajax({
		            url : '<c:url value="/app/rest/authenticate"/>',
					type : 'POST',
					dataType : 'json',
					data : {
						userName : $("#userName").val(),
						password : $("#password").val(),
					},
					success : function(data) {

						if (data.authenticated) {
							alert("Authentication successful.");
							$
									.ajax({
										url : '<c:url value="/app/rest/employee/list"/>',
										type : 'GET',
										dataType : 'json',

										success : function(list) {
											alert(list[0].name);
											var html = '';
											for (i = 0; i < list.length; i++) {

												html += getCardHtml(list[i]);
											}
											html += '';

											$("#show-emplist").html(html);
										},

										error : function(req, status, error) {
											if (req.status == 500) {
												alert("Internal System Error. Please retry or contact administrator.")
											} else if (req.status == 400) {
												alert(req.responseJSON.errors[0].defaultMessage);
											}
										}

									});
							$("#login").hide();
							$("#emplist-navbar").show();
							$("#emplist").show();
							
							
						} else {
							alert("Authentication failed.");
						}
					},
					error : function(req, status, error) {
						if (req.status == 500) {
							alert("Internal System Error. Please retry or contact administrator.")
						} else if (req.status == 400) {
							alert(req.responseJSON.errors[0].defaultMessage);
						}
					}
				});
	}
	
	
	
    /*function getCardHtml(list) {

		var html = "";
		html = '<div class="card">';
		//html+="<ul>";
		html += "<li>" + list.name + "</li>";
		html += "<li>" + list.department.name + "</li>";
		html += "<li>" + "employee@email.com" + "</li>";

		html += "<li>";

		for (j = 0; j < list.skillList.length; j++) {
			html += list.skillList[j].name + ",";
		}

		html += "</li>";
		html += "<li>" + list.address.address1 + "</li>";
		html += "<li>" + list.address.address2 + "</li>";
		html += "<li>" + list.address.locality + "</li>";
		html += "<li>" + list.address.pincode + "</li>";
		html += "<li>" + list.gender + "</li>";
		html += "<li>" + list.dob + "</li>";
		html += "<li>" + list.salary + "</li>";

		html += "</div>";*/
		 function getCardHtml(employee) {
			var emplist = '<div class="col-lg-4"'+
			'								style="border: 1px solid black; background-color: #f2f2f2; border-collapse: none; padding-right: 0px; margin-top: 5px;">'+
			'								<div class="row py-row">'+
			'									<div class="col-4 col-sm-4 col-lg-4"'+
			'										style="padding-right: 0px; padding-left: 0px; margin: 0px;">'+
			'										<div>'+
			'											<img'+
			'												src="<c:url value="/resources/images/'+employee.name+'.png"/>"'+
			'												style="width: 100%; border-radius: 50%; padding: 10px">'+
			'										</div>'+
			'									</div>'+
			'									<div class="col-8 col-sm-8 col-lg-8"'+
			'										style="padding-right: 40px; padding-left: 0px; margin: 0px; width: 100%; height: 100%;">'+
			''+
			'										<div class="card-header"'+
			'											style="padding: 2px; background-color: #f2f2f2; line-height: 1.2; margin-bottom: 8px">'+
			'											<div style="font-size: 25px; font-weight: bold; color: navy;">'+employee.name+
			'											</div>'+
			'											<div>'+
			'												<a class="card-subtitle"'+
			'													style="font-size: 14px; color: #6666cc; font-style: italic;">'+
															employee.department.name+'</a>'+
			'											</div>'+
			'										</div>'+
			'										<div class="details-text">'+
			'											<i class="fas fa-envelope"> </i>'+'xyz@gmail.com'+
			'										</div>'+
			''+
			'										<div class="details-text">'+
			'											<i class="fas fa-medal"></i>'+employee.skills+
			'											<c:forEach items="${employee.skillsList}" var="skills">'+
			'							${skills.name}, </c:forEach>'+
			'										</div>'+
			'										<div class="details-text">'+
			'											<i class="fas fa-map-marker-alt"></i>'+
														employee.address.address1+employee.address.address2+
														employee.address.locality+employee.address.pincode+
			'										</div>'+
			''+
			''+
			'									</div>'+
			'								</div>'+
			'								<div class="row py-row">'+
			'									<div class="col-sm-12 col-lg-12"'+
			'										style="margin: 0px; padding-right: 13px; padding-left: 0px; width: 100%;">'+
			'										<div class="details-text"'+
			'											style="text-align: center; border-top: 1px solid #6666cc; padding-right: 10px;">'+
			'											<i class="fas fa-genderless"></i>'+employee.gender+'<i'+
			'												class="fas fa-birthday-cake"></i>'+employee.dob+'<i	class="fas fa-credit-card"></i>'+employee.salary+'<button type="button" onclick="showEmployee('+employee.id+')" class="btn btn-link" style="padding-right: 8px; font-size: 15px; float: right; color: grey;"><i'
			+'	class="fas fa-user-edit" style="color: light-grey;">Edit</i></button>' 
			+ '										</div>'
			+ '									</div>'
			+ '								</div>'
			+ '' + '							</div>';

	   return emplist;
}
		
		 function showEmployee(employeeId){
				$("#emplist").hide();
				$("#updateEmp").show();
				$("#emplist-navbar").hide();
				$("#updateEmpForm").show();
				alert(employeeId);
				$.ajax({
					url : '<c:url value="/app/rest/employee/show/'+employeeId+'"/>',
					type : 'GET',
					dataType : 'json',

					success : function(list) {
						$("#id").val(list.employee.id);
						$("#name").val(list.employee.name);
						$("#dob").val(list.employee.dob);
						$("#address1").val(list.employee.address.address1);
						$("#address2").val(list.employee.address.address2);
						$("#locality").val(list.employee.address.locality);
						$("#pincode").val(list.employee.address.pincode);
						$("#salary").val(list.employee.salary);
						var gender = list.employee.gender;
						$('input[name=gender][value='+gender+']').attr('checked', 'checked');
						var empDepartment = list.employee.department.id;
						$.each(list.depatrtments, function (index, value) {
			                    $('#departments').append('<option value="'+ value.id + '">' + value.name + '</option>');
			                    $('select').val(empDepartment);
			                });
						
						 for(i=0;i<list.skills.length;i++){
						     	var check=false;
							for(j=0;j<list.employee.skillList.length;j++){
								if(list.employee.skillList[j].id==list.skills[i].id){
									check=true;
								
								}
							}
							if(check){
			                    	$('#skills').append('<input type="checkbox" value="' + list.skills[i].id + '" checked>' + list.skills[i].name + '<br/>');
								}else{
									$('#skills').append('<input type="checkbox" value="' + list.skills[i].id + '">' + list.skills[i].name + '<br/>');
							}
							}	
					},
				        error : function(req, status, error) {
						if (req.status == 500) {
							alert("Internal System Error. Please retry or contact administrator.")
						} else if (req.status == 400) {
							alert(req.responseJSON.errors[0].defaultMessage);
						}
					}
				});
			}
		
	
</script>
</html>