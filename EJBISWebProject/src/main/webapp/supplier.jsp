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
<title>IMS - Suppliers</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="sidebar.jsp"%>
	<main>
		<div class="main-content">
			<div class="search-form">
				<form action="SupplierServlet", method="get" id="search-form">
					<div class="form-group">
						<input type="text" id="find-supplier-id" name="find-supplier-id" class="form-control" placeholder="Search...">
						<button type="submit" class="btn" name="action" id="search-btn" value="find-supplier">Search</button>
					</div>
				</form>
			</div>
			<div class="table-container">
				<table>
					<thead>
						<tr>
							<th>Supplier ID</th>
							<th>Name</th>
							<th>Address</th>
							<th>Phone Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="supplier" items="${suppliers}">
							<tr>
								<td>${supplier.supplierId}</td>
								<td>${supplier.supplierName}</td>
								<td>${supplier.supplierAddress}</td>
								<td>${supplier.phoneNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<form action="SupplierServlet" method="post" id="search-form">
			<div class="form-container">
				<fieldset>
					<legend>Supplier Information:</legend>
					<form>
						<div class="form-row">
							<label for="supplier-id">Supplier ID:</label> <input type="text"
								id="supplier-id" name="supplier-id"> <label
								for="supplier-name"> Name:</label> <input type="text"
								id="supplier-name" name="supplier-name">
						</div>
						<div class="form-row">
							<label for="supplier-address">Address:</label> <input type="text"
								id="supplier-address" name="supplier-address"> <label
								for="supplier-phone">Phone Number:</label> <input type="text"
								id="supplier-phone" name="supplier-phone">
						</div>
						<div class="button-container">
							<button type="submit" class="add-btn" name="action" value="add-supplier">Add</button>
							<button type="submit" class="update-btn" name="action" value="update-supplier">Update</button>
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
		var supplierId = '${supplierId}';
		var supplierName = '${supplierName}';
		var supplierAddress = '${supplierAddress}';
		var supplierPhoneNbr = '${supplierPhoneNumber}';
		
		$('#supplier-id').val(supplierId);
			$('#supplier-name').val(supplierName);
			$('#supplier-address').val(supplierAddress);
			$('#supplier-phone').val(supplierPhoneNbr);
	});
	</script>
	<%@ include file="footer.jsp"%>
</body>
</html>