(function() {

	$(document).ready(function() {
		// Call the function to populate the select box
		populateEmployeeSelectBox();

		// Event listeners
		initEventListeners();
	});

	// Initialize
	function initEventListeners() {
		try {
			// Event listener for the select box
			$('#employeeSelect').on('change', function() {
				// Retrieve the selected employee ID and name
				var selectedEmp = $(this).val();
				console.log(selectedEmp);
			});

			// Find employee by ID
			$("#findBtn").click(findEmployeeById());

			// Find all employees
			$("#findAllBtn").click(findAllEmployees());

			// Delete employee by ID
			$("#delEmpBtn").click(deleteEmployeeById);

			// Add new employee
			$("#addEmpBtn").click(addEmployee);

			// Update employee
			$("#updtEmpBtn").click(updateEmployee);
		} catch (error) {
			console.error("An error occurred: ", error);
		}

	}

	// Display employee table
	function displayEmployees(employees) {
		try {
			$.each(employees, function(index, employee) {
				var row = $("<tr>");
				row.append($("<td>").text(employee.EmployeeId));
				row.append($("<td>").text(employee.EmployeeName));
				row.append($("<td>").text(employee.EmployeeAddress));
				row.append($("<td>").text(employee.Phone));
				$("#employeeTable tbody").append(row);
			});
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Clear employee table
	function clearTable() {
		try {
			$("#employeeTable tbody").empty();
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Find employee by ID
	function findEmployeeById() {
		try {
			$(document).on("click", "#findBtn", function(event) {
				event.preventDefault();
				var strValue = $("#empId").val();
				if (strValue != "") {
					$.ajax({
						method: "GET",
						url: "http://localhost:8080/EJBISWebProject/RestServlet/" + strValue,
						error: ajaxRestReturnError,
						success: ajaxRestReturnSuccess
					})
				} else {
					alert("strValue not set");
				}
			});
		} catch (error) {
			console.error("An error occurred: ", error);
		}

	}

	// Find all employees
	function findAllEmployees() {
		try {
			$("#findAllBtn").click(function(event) {
				event.preventDefault();
				$.ajax({
					method: "GET",
					url: "http://localhost:8080/EJBISWebProject/RestServlet/",
					success: function(result) {
						clearTable();
						displayEmployees(result);
						$("#error-label-employee").empty();
						$("#error-label-employee").append("All employees found.");
					},
					error: function(xhr, status, error) {
						console.error("Error in fetching employees:", error);
						$("#error-label-employee").empty();
						$("#error-label-employee").append("Error in fetching employees");
					}
				});
			});
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Delete employee by ID
	function deleteEmployeeById() {
		try {
			$("#delEmpBtn").click(function(event) {
				event.preventDefault();
				var strValue = $("#empId").val();
				if (strValue != "") {
					$.ajax({
						method: "DELETE",
						url: "http://localhost:8080/EJBISWebProject/RestServlet/" + strValue,
						error: ajaxDelReturnError,
						success: ajaxDelReturnSuccess
					});
				} else {
					console.log("strValue is blank/null");
				}
			});
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Add new employee
	function addEmployee(event) {
		try {
			event.preventDefault();
			var strName = $("#empName").val();
			var strAddress = $("#empAddress").val();
			var strPhone = $("#empPhone").val();

			// Validate input fields
			if (strName === "" || !/^[a-zA-ZåäöÅÄÖ]+$/.test(strName)) {
				throw new Error("Please enter a valid Name (Letters only).");
			} else if (strAddress === "" || !/^[a-zA-Z0-9\såäöÅÄÖ]*$/.test(strAddress)) {
				throw new Error("Please enter an Address (Only letters and numbers allowed).");
			} else if (strPhone === "" || !/^\d{1,10}$/.test(strPhone)) {
				throw new Error("Please enter a valid Phone Number (numbers only).");
			}

			// Create employee object
			var obj = { EmployeeName: strName, EmployeeAddress: strAddress, Phone: strPhone };
			var jsonString = JSON.stringify(obj);

			// Send AJAX request
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/EJBISWebProject/RestServlet/",
				data: jsonString,
				dataType: 'json',
				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			});

			// Show success message
			document.getElementById("error-label-employee").innerHTML = "Employee was successfully added!";
			// Clear input fields
			$("#empName").val("");
			$("#empAddress").val("");
			$("#empPhone").val("");
			return true;
		} catch (error) {
			// Handle error and show error message
			console.error("An error occurred: ", error);
			document.getElementById("error-label-employee").innerHTML = error.message;
			return false;
		}
	}


	// Update employee
	function updateEmployee() {
		try {
			$("#updtEmpBtn").click(function(event) {
				event.preventDefault();
				var strId = $("#empId").val();
				var strName = $("#empName").val();
				var strAddress = $("#empAddress").val();
				var strPhone = $("#empPhone").val();
				var obj = { EmployeeId: strId, EmployeeName: strName, EmployeeAddress: strAddress, Phone: strPhone };
				var jsonString = JSON.stringify(obj);
				if (strId != "") {
					$.ajax({
						method: "PUT",
						url: "http://localhost:8080/EJBISWebProject/RestServlet/" + strId,
						data: jsonString,
						dataType: 'json',
						error: ajaxUpdateReturnError,
						success: ajaxUpdateReturnSuccess
					});
				}
			});
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Populate employee select box
	function populateEmployeeSelectBox() {
		try {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBISWebProject/RestServlet/",
				success: function(result) {
					var selectBox = $("#employeeSelect");
					selectBox.empty();
					$.each(result, function(index, employee) {
						var option = $("<option>").val(employee.EmployeeId).text(employee.EmployeeId + " - " + employee.EmployeeName);
						selectBox.append(option);
					});
				},
				error: function(xhr, status, error) {
					console.error("Error in fetching employees:", error);
				}
			});
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Helper functions for AJAX calls
	function ajaxRestReturnSuccess(result, status, xhr) {
		parseJsonFileEmployee(result);
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Chosen employee found.");
		populateEmployeeSelectBox();
	}

	function ajaxRestReturnError(result, status, xhr) {
		alert("Error in rest Service");
		console.log("Ajax-find Employee: " + status);
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Error in fetching employee");
	}

	function ajaxUpdateReturnSuccess(result, status, xhr) {
		clearFields();
		$("#empName").attr("placeholder", "Employee updated");
		alert("Success" + status);
		displayEmployees(result);
		populateEmployeeSelectBox();
	}

	function ajaxUpdateReturnError(result, status, xhr) {
		alert("Error updating employee");
		console.log("Ajax-update employee: " + status);
		displayEmployees(result);
	}

	function ajaxDelReturnSuccess(result, status, xhr) {
		clearFields();
		alert("Success ", status);
		$("#EmployeeName").attr("placeholder", "Employee deleted");
		displayEmployees(result);
		populateEmployeeSelectBox();
	}

	function ajaxDelReturnError(result, status, xhr) {
		alert("Error");
		console.log("Ajax-find Employee: " + status);
		displayEmployees(result);
	}

	function ajaxAddReturnSuccess(result, status, xhr) {
		clearFields();
		$("#EmployeeName").attr("placeholder", "Employee added");
		displayEmployees(result);
		populateEmployeeSelectBox();
	}
	function ajaxAddReturnError(result, status, xhr) {
		alert("Error Add");
		console.log("Ajax-find employee: " + status);
		displayEmployees(result);
	}

	function parseJsonFileEmployee(result) {
		try {
			clearTable();
			clearFields();
			var row = $("<tr>");
			row.append($("<td>").text(result.EmployeeName));
			row.append($("<td>").text(result.EmployeeId));
			row.append($("<td>").text(result.EmployeeAddress));
			row.append($("<td>").text(result.Phone));
			$("#employeeTable tbody").empty().append(row);
			$("#empName").val(result.EmployeeName);
			$("#empIdAdd").val(result.EmployeeId);
			$("#empAddress").val(result.EmployeeAddress);
			$("#empPhone").val(result.Phone);
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	function clearFields() {
		try {
			$("#EmployeeId").text("");
			$("#EmployeeName").text("");
			$("#EmployeeAddress").text("");
			$("#EmployeePhone").text("");
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

})();