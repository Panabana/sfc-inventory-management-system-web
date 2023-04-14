<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import = "java.util.List, ims.ics.ejb.Employee" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>IMS - Employee</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <%@ include file="sidebar.jsp" %>
    <main>
        <div class="main-content">
            <h1>Employee</h1>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Employee ID</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Phone Number</th>
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
        </div>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>
