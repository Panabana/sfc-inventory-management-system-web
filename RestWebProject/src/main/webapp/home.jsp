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
$(document).ready(function() {
    $("#FindBtn").click(function() {
    	event.preventDefault();
        var strValue = $("#empId").val();
        if (strValue != "") {
            $.ajax({
                method: "GET",
                url: "http://localhost:8080/EJBISWebProject/RestServlet/" + strValue,
                error: ajaxRestReturn_Error,
                success: ajaxRestReturn_Success
            })

            function ajaxRestReturn_Success(result, status, xhr) {
                parseJsonFileEmployee(result);
            }

            function ajaxRestReturn_Error(result, status, xhr) {
                alert("Error in rest Service");
                console.log("Ajax-find Employee: " + status);
            }

            function parseJsonFileEmployee(result) {
                $("#EmployeeName").text(result.EmployeeName);
                $("#EmployeeId").text(result.EmployeeId);
                $("#EmployeeAddress").text(result.EmployeeAddress);
                $("#EmployeePhone").text(result.Phone);

            }
            function clearFields() {
                $("#EmployeeName").text("");
                $("#EmployeeId").text("");
                $("#EmployeeAddress").text("");
                $("#EmployeePhone").text("");
            }
        }
        //alert("strValue not set");
    });
});
</script>
<body>
<div class="main">

 <form action="#">
  <label for="empId">Employee Id:</label>
  <input type="number" id="empId" name="empId"><br><br>
  <input type="submit" value="Find Employee" id="FindBtn">
</form> 

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