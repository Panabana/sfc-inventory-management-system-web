<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>IMS - Product</title>
</head>
<body>
	<header class="header">
		<div class="container">
			<a href="#" class="logo"><img src="images/logo.png" height="80"></a>
			<nav class="nav">
				<div class="nav-toggle"></div>
				<ul class="nav-menu">
					<li><a href="#" class="nav-link">Weather REST is here</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="sidebar" style="margin-top: 100px;">
		<div class="sidebar-header">
			<a href="ControllerServlet?action=home" class="sidebar-logo">My
				Website</a>
			<div class="sidebar-toggle"></div>
		</div>
		<ul class="sidebar-menu">
			<li class="sidebar-menu-item"><a href="#"
				class="sidebar-menu-link active"> <i class="fa fa-dashboard"></i>
					Dashboard
			</a></li>
			<li class="sidebar-menu-item"><a href="#"
				class="sidebar-menu-link"> <i class="fa fa-users"></i> Users
			</a></li>
			<li class="sidebar-menu-item"><a
				href="ControllerServlet?action=employee" class="sidebar-menu-link">
					<i class="fa fa-cog"></i> Settings
			</a></li>
			<li class="sidebar-menu-item"><a
				href="ControllerServlet?action=product" class="sidebar-menu-link">
					<i class="fa fa-sign-out"></i> Logout
			</a></li>
		</ul>
	</div>
	<main>
	  <h1>Product</h1>
	  <div class="center">
	  <table>
		  <tr>
		  	<th>ProductID</th>
		  	<th>ProductName</th>
		  	<th>Price</th>
		  </tr>
		  <tr>
		    <td>1</td>
		    <td>Duff Beer</td>
		    <td>80</td>
		  </tr>
		</table>
	  </div>
	</main>
	<footer>
	  <div class="center">
		  <p>&copy; 2023 Group 20 Bumbibjornarna. All rights reserved.</p>
	  </div>
		
	</footer>
</body>
</html>