<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, ims.ics.ejb.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>IMS - Employee</title>
</head>
<body>
	<script src="js/employeeScripts.js"></script>
	<%@ include file="header.jsp"%>
	<%@ include file="sidebar.jsp"%>
	<main>
		<div class="main-content">
			<div class="search-form">
				<form action="EmployeeServlet" method="get" id="search-form"
					onsubmit="return validateSearchForm()">
					<div class="form-group">
						<input type="text" id="find-employee-id" name="find-employee-id"
							class="form-control" placeholder="Search...">
						<button type="submit" class="btn" name="action" id="search-btn"
							value="find-employee">Search</button>
					</div>
				</form>
			</div>
			<div class="table-container">
				<table id="employee-table">
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
						<div class="form-row">
							<label for="employee-id">Employee ID:</label> <input type="text"
								id="employee-id" name="employee-id"> <label
								for="employee-name">Name:</label> <input type="text"
								id="employee-name" name="employee-name">
						</div>
						<div class="form-row">
							<label for="employee-address">Address:</label> <input type="text"
								id="employee-address" name="employee-address"> <label
								for="employee-phone">Phone Number:</label> <input type="text"
								id="employee-phone" name="employee-phone">
						</div>
						<div class="button-container">
							<button type="submit" class="add-btn" name="action"
								value="add-employee">Add</button>
							<button type="submit" class="update-btn" name="action"
								value="update-employee">Update</button>
						</div>
						<div class="error-label" id="error-label">
							<!-- Error messages will be displayed here -->
							<p>${error}</p>
						</div>
					</fieldset>
				</div>
			</form>
		</div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>
