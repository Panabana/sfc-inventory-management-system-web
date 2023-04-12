<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>Insert title here</title>

</head>
<body>
<header class="header">
    <div class="container">
      <a href="#" class="logo"><img src="images/logo.png"  height="80"></a>
      <nav class="nav">
        <div class="nav-toggle"></div>
        <ul class="nav-menu">
          <li><a href="#" class="nav-link">Weather REST is here</a></li>
        </ul>
      </nav>
    </div>
  </header>
<div class="sidebar" style="margin-top:65px;">
  <ul class="sidebar-menu">
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=about" class="sidebar-menu-link active">
        <i class="fas fa-home"></i> Home </a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=test" class="sidebar-menu-link">
        <i class="fas fa-box"></i> Products </a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=employee" class="sidebar-menu-link">
        <i class="fas fa-truck"></i> Suppliers </a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=product" class="sidebar-menu-link">
        <i class="fas fa-shopping-cart"></i> Purchases </a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=product" class="sidebar-menu-link">
        <i class="fas fa-user"></i>Customers</a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=product" class="sidebar-menu-link">
        <i class="fas fa-address-card"></i>Employees</a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=product" class="sidebar-menu-link">
        <i class="fas fa-users"></i>About us</a>
    </li>
    <li class="sidebar-menu-item">
      <a href="ControllerServlet?action=product" class="sidebar-menu-link">
        <i class="fas fa-microscope"></i>Test</a>
    </li>
  </ul>
</div>

<div class="main-content">
	<main>
		<h1>Bumbibjornarna's Inventoryinator</h1>
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
	</main>
	<footer>
		<p>&copy; 2023 Group 20 Bumbibjornarna. All rights reserved.</p>
	</footer>
</body>
</html>