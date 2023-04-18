<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>IMS - REST</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
</script>
<script src=js/scripts.js> 
</script>
<body>
<div class="main">

 <form action="#">
  <label for="empId">Employee Id:</label>
  <input type="number" id="empId" name="empId"><br><br>
  <input type="submit" value="Find Employee" id="FindBtn">
</form> 
<button type="submit" class="findAllBtn" id="findAllBtn">Find All</button>
<table>
<caption>Employees</caption>
  <thead>
  <tr>
    <th>Employee name</th>
    <th>Employee Id</th>
    <th>Address</th>
    <th>Phone</th>
  </tr>
  </thead>
  <tbody>
  <tr>
     <td><span id="EmployeeName"></span></td>
     <td><span id="EmployeeId"></span></td>
     <td><span id="EmployeeAddress"></span></td>
     <td><span id="EmployeePhone"></span></td>
  </tr>
  </tbody>
</table>
</div>
</body>
</html>