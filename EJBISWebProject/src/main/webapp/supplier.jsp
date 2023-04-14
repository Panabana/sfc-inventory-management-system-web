<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, ims.ics.ejb.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>IMS - Suppliers</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
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
			<div class="form-container">
				<div class="form-field">
					<label for="supplier-id">Supplier ID</label> <input type="text"
						id="supplier-id" name="supplier-id" />
				</div>
				<div class="form-field">
					<label for="supplier-name">Name</label> <input type="text"
						id="supplier-name" name="supplier-name" />
				</div>
				<div class="form-field">
					<label for="supplier-address">Address</label> <input type="text"
						id="supplier-address" name="supplier-address" />
				</div>
				<div class="form-field">
					<label for="supplier-phone">Phone Number</label> <input type="text"
						id="supplier-phone" name="supplier-phone" />
				</div>
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