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
				<form action="#">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search...">
						<button type="submit" class="btn">Search</button>
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
			<form action="ControllerServlet?action=add" method="post">
			<div class="form-container">
				<div class="form-field">
					<label for="employee-id">Employee ID</label> <input type="text"
						id="employee-id" name="employee-id" />
				</div>
				<div class="form-field">
					<label for="employee-name">Name</label> <input type="text"
						id="employee-name" name="employee-name" />
				</div>
				<div class="form-field">
					<label for="employee-address">Address</label> <input type="text"
						id="employee-address" name="employee-address" />
				</div>
				<div class="form-field">
					<label for="employee-phone">Phone Number</label> <input type="text"
						id="employee-phone" name="employee-phone" />
				</div>
				<div class="button-group">
					<button type="submit" class="add-button">Add</button>
					<button type="submit" class="update-button">Update</button>
					<button type="submit" class="remove-button">Remove</button>
				</div>
			</div>
			</form>
		</div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>
