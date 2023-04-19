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
				<form action="ProductServlet" method="get" id="search-form">
					<div class="form-group">
						<input type="text" id="find-product-id" name="find-product-id" class="form-control" placeholder="Search...">
						<button type="submit" class="btn" name="action" id="search-button" value="find-product">Search</button>
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
			<form action="ProductServlet" method="post">
			<div class="form-container">
				<fieldset>
					<legend>Product Information:</legend>
					<form>
						<div class="form-row">
							<label for="product-id">Product ID:</label> <input type="text"
								id="product-id" name="product-id"> <label
								for="product-name"> Name:</label> <input type="text"
								id="product-name" name="product-name">
						</div>
						<div class="form-row">
							<label for="product-price">Price:</label> <input type="text"
								id="product-price" name="product-price">
						</div>
						<div class="button-container">
							<button type="submit" class="add-btn" name="action" value="add-product">Add</button>
							<button type="submit" class="update-btn" name="action" value="update-product">Update</button>
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
		var productId = '${productId}';
		var productName = '${productName}';
		var productPrice = '${productPrice}';
		
		$('#product-id').val(productId);
		$('#product-name').val(productName);
		$('#product-price').val(productPrice);
	});
	</script>
	<%@ include file="footer.jsp"%>
</body>
</html>