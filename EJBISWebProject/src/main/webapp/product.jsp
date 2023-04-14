<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, ims.ics.ejb.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>IMS - Product</title>
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
							<th>ProductID</th>
							<th>Product Name</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${products}">
							<tr>
								<td>${product.productId}</td>
								<td>${product.productName}</td>
								<td>${product.price}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-container">
				<div class="form-field">
					<label for="product-id">Product ID</label> <input type="text"
						id="product-id" name="product-id" />
				</div>
				<div class="form-field">
					<label for="product-name">Name</label> <input type="text"
						id="product-name" name="product-name" />
				</div>
				<div class="form-field">
					<label for="product-price">Price</label> <input type="text"
						id="product-price" name="product-price" />
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