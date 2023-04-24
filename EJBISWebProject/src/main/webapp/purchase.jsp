<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List, ims.ics.ejb.Purchase, ims.ics.ejb.Employee, ims.ics.ejb.Customer"%>
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
				<form action="PurchaseServlet" method="get" id="search-form">
					<div class="form-group">
						<input type="text" id="find-purchase-id" name="find-purchase-id" class="form-control" placeholder="Search...">
						<button type="submit" class="btn" name="action" id="search-btn" value="find-purchase">Search</button>
					</div>
				</form>
			</div>
			<div class="table-container">
				<table>
					<thead>
						<tr>
							<th>Purchase ID</th>
							<th>Customer</th>
							<th>Employee</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="purchase" items="${purchases}">
							<tr>
								<td>${purchase.purchaseId}</td>
								<td>${purchase.customer.customerId}-
									${purchase.customer.name}</td>
								<td>${purchase.employee.employeeId}-
									${purchase.employee.name}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<form action="PurchaseServlet" method="post">
			<div class="form-container">
				<fieldset>
					<legend>Purchase Information:</legend>
					<form>
						<div class="form-row">
							<label for="purchase-id">Purchase ID:</label> <select
								name="purchase-id" id="purchase-id">
								<option value="">Select a Purchase that you wish to
									update</option>
								<c:forEach var="purchase" items="${purchases}">
									<option value="${purchase.purchaseId}">${purchase.purchaseId}</option>
								</c:forEach>
							</select> <label for="employee-id"> Employee ID:</label> <select
								name="employee-id" id="employee-id">
								<option value="">Select an employee</option>
								<c:forEach var="employee" items="${employees}">
									<option value="${employee.employeeId}">${employee.employeeId}</option>
								</c:forEach>
							</select> <label for="customer-id"> Customer ID:</label> <select
								name="customer-id" id="customer-id">
								<option value="">Select a customer</option>
								<c:forEach var="purchase" items="${purchases}">
									<option value="${purchase.customer.customerId}">${purchase.customer.customerId}</option>
								</c:forEach>
							</select>

						</div>
						<div class="button-container">
							<button type="submit" class="add-btn" name="action"
								value="add-purchase">Add</button>
							<button type="submit" class="update-btn" name="action"
								value="update-purchase">Update</button>
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
	$(document).ready(function() {
		$('#search-btn').click(function() {
			$('#search-form').submit();
		});

		var purchaseId = '${purchaseId}';
		var employeeId = '${employeeId}';
		var customerId = '${customerId}';

		$('#purchase-id').val(purchaseId);
		$('#employee-id').val(employeeId);
		$('#customer-id').val(customerId);
	});
</script>
	<%@ include file="footer.jsp"%>
</body>
</html>