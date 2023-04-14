<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, ims.ics.ejb.Purchase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>IMS - Purchases</title>
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
							<th>Purchase ID</th>
							
							<th>Customer Name</th>
							<th>Employee Name</th>
							<th>Product Name</th>
							<th>Quantity</th>
							<th>Total Price</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="purchase" items="${purchases}">
							<tr>
								<td>${purchase.purchaseId}</td>
								<!--
								<td>${employee.name}</td>
								<td>${employee.address}</td>
								<td>${employee.phoneNumber}</td>
								-->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-container">
				<div class="form-field">
					<label for="purchase-id">Purchase ID</label> <input type="text"
						id="purchase-id" name="purchase-id" />
				</div>
				<div class="form-field">
					<label for="employee-id">Employee ID</label> <input type="text"
						id="employee-id" name="employee-id" />
				</div>
				<div class="form-field">
					<label for="customer-id">Customer ID</label> <input type="text"
						id="customer-id" name="customer-id" />
				</div>
				<!-- 
				<div class="form-field">
					<label for="employee-phone">Phone Number</label> <input type="text"
						id="employee-phone" name="employee-phone" />
				</div>
				-->
				<div class="button-group">
					<button type="submit" class="add-button">Add</button>
					<button type="submit" class="update-button">Update</button>
					<button type="submit" class="remove-button">Remove</button>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>