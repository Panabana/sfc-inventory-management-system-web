/**
 * 
 */

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