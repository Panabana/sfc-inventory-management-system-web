
	$(document).ready(function() {
				$('#search-btn').click(function() {
					$('#search-form').submit();
				});
				var employeeId = '${employeeId}';
				var employeeName = '${employeeName}';
				var employeeAddress = '${employeeAddress}';
				var employeePhoneNumber = '${employeePhoneNumber}'
	
				$('#employee-id').val(employeeId);
				$('#employee-name').val(employeeName);
				$('#employee-address').val(employeeAddress);
				$('#employee-phone').val(employeePhoneNumber);
			});
	
			function validateForm() {
				var employeeName = document.getElementById("employee-name").value;
				var employeeAddress = document.getElementById("employee-address").value;
				var employeePhoneNumber = document.getElementById("employee-phone").value;
				var errorMessage = "";
	
				 if (employeeName === "" || !/^[a-zA-ZåäöÅÄÖ]+$/.test(employeeName)) {
					errorMessage = "Please enter a valid Name (Letters only)."
					document.getElementById("error-label").innerHTML = errorMessage;
					return false;
				} else if (employeeAddress === ""
						|| !/^[a-zA-Z0-9\såäöÅÄÖ]*$/.test(employeeAddress)) {
					errorMessage = "Please enter an Address (Only letters and numbers allowed)."
					document.getElementById("error-label").innerHTML = errorMessage;
					return false;
				} else if (employeePhoneNumber === ""
						|| !/^\d{1,10}$/.test(employeePhoneNumber)) {
					errorMessage = "Please enter a valid Phone Number (numbers only)."
					document.getElementById("error-label").innerHTML = errorMessage;
					return false;
				} else {
					errorMessage = "Employee was successfully added!";
					document.getElementById("error-label").innerHTML = errorMessage;
					return true;
				}
			}
	
			function validateSearchForm(){
				var employeeId = document.getElementById("find-employee-id").value;
				if(employeeId === "" || !/^\d{1,10}$/.test(employeeId)){
					errorMessage = "Please enter a valid ID to search for!"
						document.getElementById("error-label").innerHTML = errorMessage;
						return false;
				} else {
			        var employeeExists = false;
			        $('#employee-table tbody tr').each(function() {
			            var rowEmployeeId = $(this).find('td:eq(0)').text();
			            if (rowEmployeeId === employeeId) {
			                employeeExists = true;
			                return false; // break the loop
			            }
			        });
			        if (!employeeExists) {
			            errorMessage = "Employee with ID " + employeeId + " doesn't exist.";
			            document.getElementById("error-label").innerHTML = errorMessage;
			            return false;
			        }
			    }
			    return true;
			}
 
