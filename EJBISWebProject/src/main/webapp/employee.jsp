<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import = "java.util.List, ims.ics.ejb.Employee" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>IMS - Employee</title>
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
	<h1>Employee</h1>
<div class="center">
  <table>
    <thead>
      <tr>
        <th>EmployeeID</th>
        <th>EmployeeName</th>
        <th>EmployeeAddress</th>
        <th>PhoneNumber</th>
      </tr>
    </thead>
    <tbody> 
      <c:forEach var="employee" items="${employees}">
        <tr>
          <td>${employee.employeeId}</td>
          <td>${employee.name}</td>
          <td>${employee.address}</td>
          <td>${employee.phoneNumber}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

	
	</main>
	<footer>
		<p>&copy; 2023 Group 20 Bumbibjornarna. All rights reserved.</p>
	</footer>
</body>
</html>