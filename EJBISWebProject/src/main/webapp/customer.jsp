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
<title>IMS - Customers</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="sidebar.jsp"%>
	<main>
		<div class="main-content">
			<div class="search-form">
				<form action="CustomerServlet" method="get" id="search-form">
					<div class="form-group">
						<input type="text" id="find-customer-id" name="find-customer-id" class="form-control" placeholder="Search...">
						<button type="submit" class="btn" name="action" id="search-btn" value="find-customer">Search</button>
					</div>
				</form>
			</div>
			<div class="table-container">
				<table>
					<thead>
						<tr>
							<th>Customer ID</th>
							<th>Name</th>
							<th>Address</th>
							<th>Phone Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="customer" items="${customers}">
							<tr>
								<td>${customer.customerId}</td>
								<td>${customer.name}</td>
								<td>${customer.address}</td>
								<td>${customer.phoneNbr}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<form action="CustomerServlet" method="post">
			<div class="form-container">
				<fieldset>
					<legend>Customer Information:</legend>
					<form>
						<div class="form-row">
							<label for="customer-id">Customer ID:</label> <input type="text"
								id="customer-id" name="customer-id"> <label for="customer-name">
								Name:</label> <input type="text" id="customer-name" name="customer-name">
						</div>
						<div class="form-row">
							<label for="customer-address">Address:</label> <input type="text"
								id="customer-address" name="customer-address"> <label for="customer-phone">Phone Number:</label>
							<input type="text" id="customer-phone" name="customer-phone">
						</div>
						<div class="button-container">
							<button type="submit" class="add-btn" name="action" value="add-customer">Add</button>
							<button type="submit" class="update-btn" name="action" value="update-customer">Update</button>
							<button type="submit" class="remove-btn">Remove</button>
						</div>
						<div class="error-label">
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
	$(document).ready(function(){
		$('#search-btn').click(function(){
			$('search-form').submit()
			});
		var customerId = '${customerId}';
		var customerName = '${customerName}';
		var customerAddress = '${customerAddress}';
		var customerPhoneNbr = '${customerPhoneNumber}';
		
		$('#customer-id').val(customerId);
			$('#customer-name').val(customerName);
			$('#customer-address').val(customerAddress);
			$('#customer-phone').val(customerPhoneNbr);
	});
	</script>
	<%@ include file="footer.jsp"%>
</body>
</html>