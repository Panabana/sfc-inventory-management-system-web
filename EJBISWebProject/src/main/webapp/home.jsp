<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Is font-awesome still being used? -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#welcome").click(function() {
			$(this).hide();
		});
	});
</script>
<title>IMS - Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="sidebar.jsp"%>
	<main>
		<div class="main-content">
			<h1>Spongebob Fan Clubinator</h1>
			<div class="container" id="welcome" style="font-family: Arial">
				<h2 class="container">
					Welcome to the<br>Spongebob Inventory Management System
				</h2>
				<br> <br>
				<p>
					Esteemed customer, simplify your work!<br> <br> If you
					are new here and want a quick tutorial for this website, please
					read this short message.<br> You can dismiss it anytime if you
					like.<br> <br> This website will help you organize your
					company's inventory and database system.<br> We call this
					website the <b>Inventory Management System</b>. <b>IMS</b> for
					short.<br> <br> On the left-hand side, you will find the
					main menu to navigate the website.<br> We have five tables
					each with their own corresponding page. There you'll find the
					specific data for each table.<br> To change or update the data
					rows in a table, please utilize the corresponding text boxes and
					buttons (e.g. "Add" to add, "Delete" to delete).<br> At the
					top of the page, you will find the current weather.<br> <br>
					Thank you for choosing Spongebob Fan Club.<br>
				</p>
				<div class="container" id="welcome">
					<br>
					<button id="welcome">Dismiss</button>
				</div>
			</div>

			<br>
			<div class="stats-card-all">
				<div class="stats-card">
					<i class="fas fa-shopping-cart"></i>
					<h3>${countPurchases}</h3>
					<p>Total Purchases</p>
				</div>

				<div class="stats-card">
					<i class="fas fa-box"></i>
					<h3>${countProducts}</h3>
					<p>Total Products</p>
				</div>

				<div class="stats-card">
					<i class="fas fa-truck"></i>
					<h3>${countSuppliers}</h3>
					<p>Total Suppliers</p>
				</div>

				<div class="stats-card">
					<i class="fas fa-user"></i>
					<h3>${countCustomers}</h3>
					<p>Total Customers</p>
				</div>

				<div class="stats-card">
					<i class="fas fa-address-card"></i>
					<h3>${countEmployees}</h3>
					<p>Total Employees</p>
				</div>
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
						<!-- 
						<c:forEach var="customer" items="${customers}">
							<tr>
								<td>${customer.customerId}</td>
								<td>${customer.name}</td>
								<td>${customer.address}</td>
								<td>${customer.phoneNbr}</td>
							</tr>
						</c:forEach>
						-->
					</tbody>
				</table>
			</div>
		</div>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>