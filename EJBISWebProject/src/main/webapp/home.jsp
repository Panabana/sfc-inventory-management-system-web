<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>IMS - Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="sidebar.jsp"%>
	<main>
		<div class="main-content">
			<h1>Spongebob Fan Clubinator</h1>
			<div>
				<h2>Welcome to the Spongebob </h2>
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