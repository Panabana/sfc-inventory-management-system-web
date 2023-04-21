<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, ims.ics.ejb.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IMS - Employee</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="sidebar.jsp"%>
	<main>
		<div class="main-content">
			<div class="search-form">
				<form action="EmployeeServlet" method="get" id="search-form">
					<div class="form-group">
						<input type="text" id="find-employee-id" name="find-employee-id"
							class="form-control" placeholder="Search...">
						<button type="submit" class="btn" name="action" id="search-btn"
							value="find-employee">Search</button>
					</div>
				</form>
			</div>
			<div class="table-container">
				<table>
					<thead>
						<tr>
							<th>Employee ID</th>
							<th>Name</th>
							<th>Address</th>
							<th>Phone Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${employees}">
							<tr>
								<td>${employee.employeeId}</td>
								<td>${employee.name}</td>
								<td>${employee.address}</td>
								<td>${employee.phoneNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<form action="EmployeeServlet" method="post"
				onsubmit="return validateForm()">
				<div class="form-container">
					<fieldset>
						<legend>Employee Information:</legend>
						<form>
							<div class="form-row">
								<label for="employee-id">Employee ID:</label> <select
									name="employee-id" id="employee-id">
									<option value="">Select an employee that you wish to update</option>
									<c:forEach var="employee" items="${employees}">
										<option value="${employee.employeeId}">${employee.employeeId}</option>
									</c:forEach>
								</select> <label for="employee-name"> Name:</label> <input type="text"
									id="employee-name" name="employee-name">
							</div>
							<div class="form-row">
								<label for="employee-address">Address:</label> <input
									type="text" id="employee-address" name="employee-address">
								<label for="employee-phone">Phone Number:</label> <input
									type="text" id="employee-phone" name="employee-phone">
							</div>
							<div class="button-container">
								<button type="submit" class="add-btn" name="action"
									value="add-employee">Add</button>
								<button type="submit" class="update-btn" name="action"
									value="update-employee">Update</button>
								<button type="submit" class="remove-btn">Remove</button>
							</div>
							<div class="error-label" id="error-label">
								<!-- Error messages will be displayed here -->
								<p>User messages will be displayed here</p>
							</div>
						</form>
					</fieldset>
				</div>
			</form>
		</div>
	</main>
	<script>
		$(document).ready(function() {
			$('#search-btn').click(function() {
				$('#search-form').submit();
			});
			var employeeId = '${employeeId}';
			var employeeName = '${employeeName}';
			var employeeAddress = '${employeeAddress}';
			var employeePhoneNumber = '${employeePhoneNumber}'

			$('#employee-id').val(employeeId);
			$('#employee-name').val(employeeName);
			$('#employee-address').val(employeeAddress);
			$('#employee-phone').val(employeePhoneNumber);
		});

		function validateForm() {
			//  var employeeId = document.getElementById("employee-id").value;
			var employeeName = document.getElementById("employee-name").value;
			var employeeAddress = document.getElementById("employee-address").value;
			var employeePhoneNumber = document.getElementById("employee-phone").value;
			var errorMessage = "";

			//if (employeeId === "") { 
			// errorMessage = "Employee ID is required.";
			// document.getElementById("error-label").innerHTML = errorMessage;
			// return false;
			if (employeeName === "") {
				errorMessage = "Please enter a Name."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else if (employeeAddress === "") {
				errorMessage = "Please enter an Address."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else if (employeePhoneNumber === "") {
				errorMessage = "Please enter a valid Phone Number"
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			}
			return true;
		}
	</script>
	<%@ include file="footer.jsp"%>
</body>
</html>
