/**
 * 
 */

 $(document).ready(function() {
    $(document).on("click", "#FindBtn", function(event) {
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
    
    $("#findAllBtn").click(function() {
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/EJBISWebProject/RestServlet/",
      success: function(result) {
        displayEmployees(result);
      },
      error: function(xhr, status, error) {
        console.error("Error in fetching employees:", error);
      }
    });
  });
  
  	
  	function displayEmployees(employees) {
    // Clear existing table rows
    $("#employeeTable tbody").empty();
    
    
  	 $.each(employees, function(index, employee) {
      var row = $("<tr>");
      row.append($("<td>").text(employee.EmployeeName));
      row.append($("<td>").text(employee.EmployeeId));
      row.append($("<td>").text(employee.EmployeeAddress));
      row.append($("<td>").text(employee.EmployeePhone));
      $("#employeeTable tbody").append(row);
    });
   }
   
   $("#delEmpBtn").click( function() {
	var strValue = $("#empId").val();
	if (strValue != "") {
	$.ajax({
	method: "DELETE",
	url: "http://localhost:8080/EJBISWebProject/RestServlet/"+strValue,
	error: ajaxDelReturnError,
	success: ajaxDelReturnSuccess
	})
	
	function ajaxDelReturnSuccess(result, status, xhr) {
	clearFields();
	$("#EmployeeName").attr("placeholder","Employee deleted" );
	}
	
	function ajaxDelReturnError(result, status, xhr) {
	alert("Error");
	console.log("Ajax-find Employee: "+status);
	}
	}
	})
   
   
   
});