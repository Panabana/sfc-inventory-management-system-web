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
<script>
 $.ajax({
	method: "GET",
	url: "http://localhost:8080/EJBISWebProject/RestServlet",
	error: ajaxRestReturn_Error,
	success: ajaxRestReturn_Success
	})
	
	function ajaxRestReturn_Success(result, status, xhr) {
	
	parseJsonFileEmployee(result);
	 
	}
	
	function ajaxRestReturn_Error(result, status, xhr) {
	alert("Error in rest Service");
	console.log("Ajax-find Employee: "+status);
	}	
	
	function ParseJsonFileEmployee(result) {
		$("#EmployeeName").val(result.EmployeeName);
		$("#EmployeeId").val(result.EmployeeId);
		$("#EmployeeAddress").val(result.EmployeeAddress);
		$("#EmployeePhone").val(result.Phone);

		}
		function clearFields() {
			$("#EmployeeName").val("");
			$("#EmployeeId").val("");
			$("#EmployeeAddress").val("");
			$("#EmployeePhone").val("");
		}
	
</script>

<body>
<div class="main">
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
  <tr>
    <td><span id="EmployeeName"></span></td>
    <td><span id="EmployeeId"></span></td>
    <td><span id="EmployeeAddress"></span></td>
    <td><span id="EmployeePhone"></span></td>
  </tr>
</table>
</div>
</body>
</html>