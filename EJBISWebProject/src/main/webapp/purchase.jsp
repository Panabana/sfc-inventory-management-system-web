<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, ims.ics.ejb.Purchase, ims.ics.ejb.Employee, ims.ics.ejb.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    			
							</tr>
						</c:forEach>
						
						<c:forEach var="employee" items="${employees}">
							<tr>
								<td>${employee.employeeId}</td>
                    			
							</tr>
						</c:forEach>
					
			
			
						<c:forEach var="customer" items="${customers}">
							<tr>
								<td>${customer.customerId}</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
			
				
						
					
			
			<div class="form-container">
				<fieldset>
					<legend>Purchase Information:</legend>
					<form>
						<div class="form-row">
							<label for="purchase-id">Purchase ID:</label> <select
									name="purchase-id" id="purchase-id">
									<option value="">Select a Purchase that you wish to update</option>
									<c:forEach var="purchase" items="${purchases}">
										<option value="${purchase.purchaseId}">${purchase.purchaseId}</option>
									</c:forEach>
								</select>  
								<div class="form-row">
							<label for="employee-id"> Employee ID:</label> <select
								name="employee-id" id="employee-id">
								<option value="">Select an employee</option>
									<c:forEach var="employee" items="${employees}">
										<option value="${employee.employeeId}">${employee.employeeId}</option>
									</c:forEach>
							</select>
							</div>
							<label for="customer-id"> Customer ID:</label> <select
								name="customer-id" id="customer-id">
								<option value="">Select a customer</option>
									<c:forEach var="customer" items="${customers}">
										<option value="${customer.customerId}">${customer.customerId}</option>
									</c:forEach>
							</select>
							
						</div>
						<div class="button-container">
							<button type="submit" class="add-btn">Add</button>
							<button type="submit" class="update-btn">Update</button>
							<button type="submit" class="remove-btn">Remove</button>
						</div>
						<div class="error-label">
							<!-- Error messages will be displayed here -->
							<p>User messages will be displayed here</p>
						</div>
					</form>
				</fieldset>
			</div>
		</div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>