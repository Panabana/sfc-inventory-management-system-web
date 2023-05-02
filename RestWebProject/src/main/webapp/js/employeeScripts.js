(function() {

	$(document).ready(function() {
		// Call the function to populate the select box
		populateEmployeeSelectBox();

		// Event listeners
		initEventListeners();
	});

	function initEventListeners() {
		try {
			// Event listener for the select box
			$('#employeeSelect').on('change', function() {
				// Retrieve the selected employee ID and name
				var selectedEmp = $(this).val();
				console.log(selectedEmp);
			});

			$("#findBtn").click(findEmployeeById);

			$("#findAllBtn").click(findAllEmployees);

			$("#delEmpBtn").click(deleteEmployeeById);

			$("#addEmpBtn").click(addEmployee);

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
	function findEmployeeById(event) {
		try {
			event.preventDefault();

			var strValue = $("#empId").val();
			if (strValue != "") {
				$.ajax({
					method: "GET",
					url: "http://localhost:8080/EJBISWebProject/RestServletEmployee/" + strValue,
					error: ajaxRestReturnError,
					success: ajaxRestReturnSuccess
				})
			} else {
				$("#error-label-employee").empty();
				$("#error-label-employee").append("Please enter an ID");
			}

		} catch (error) {
			console.error("An error occurred: ", error);
		}

	}

	// Find all employees
	function findAllEmployees() {
		try {

			event.preventDefault();

			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBISWebProject/RestServletEmployee/",
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

		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Delete employee by ID
	function deleteEmployeeById(event) {
		event.preventDefault();

		var strValue = $("#empId").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE",
				url: "http://localhost:8080/EJBISWebProject/RestServletEmployee/" + strValue,
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			});
		} else {
			console.log("strValue is blank/null");
		}
	}

	// Add new employee
	function addEmployee(event) {
		try {
			event.preventDefault();

			var strId = $("#empIdAdd").val();
			var strName = $("#empName").val();
			var strAddress = $("#empAddress").val();
			var strPhone = $("#empPhone").val();

			// Validate input fields
			if (strName === "" || !/^[a-zA-Z\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strName)) {
				throw new Error("Please enter a valid Name (Letters only).");
			} else if (strAddress === "" || !/^[a-zA-Z0-9\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strAddress)) {
				throw new Error("Please enter an Address (Only letters and numbers allowed).");
			} else if (strPhone === "" || !/^\d{1,10}$/.test(strPhone)) {
				throw new Error("Please enter a valid Phone Number (numbers only).");
			} else if (strId === "" || !/^\d{1,10}$/.test(strId)) {
				throw new Error("Please enter a valid ID (numbers only).");
			}

			// Create employee object
			var obj = { EmployeeId: strId, EmployeeName: strName, EmployeeAddress: strAddress, Phone: strPhone };
			var jsonString = JSON.stringify(obj);

			// Send AJAX request
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/EJBISWebProject/RestServletEmployee/",
				data: jsonString,
				dataType: 'json',
				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			});

			function ajaxAddReturnSuccess(result, status, xhr) {
				clearFields();
				displayEmployees(result);
				populateEmployeeSelectBox();
				 $("#EmployeeName").attr("placeholder", "Employee added");
				$("#error-label-employee").empty();
				$("#error-label-employee").append("Employee added.");
			}
			function ajaxAddReturnError(result, status, xhr) {
				console.log("Ajax-find employee: " + status);

				$("#error-label-employee").empty();
				$("#error-label-employee").append("Employee with ID already exists!");
			}

			// Show success message
			document.getElementById("error-label-employee").innerHTML = "Employee was successfully added!";
			// Clear input fields
			$("#empIdAdd").val("");
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
	function updateEmployee(event) {
		try {
			event.preventDefault();

			var strId = $("#empIdAdd").val();
			var strName = $("#empName").val();
			var strAddress = $("#empAddress").val();
			var strPhone = $("#empPhone").val();

			// Validate input fields
			if (strId === "" || !/^\d+$/.test(strId)) {
				throw new Error("Please enter a valid Employee ID (Numbers only).");
			} else if (strName === "" || !/^[a-zA-Z\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strName)) {
				throw new Error("Please enter a valid Name (Letters only).");
			} else if (strAddress === "" || !/^[a-zA-Z0-9\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strAddress)) {
				throw new Error("Please enter an Address (Only letters and numbers allowed).");
			} else if (strPhone === "" || !/^\d{1,10}$/.test(strPhone)) {
				throw new Error("Please enter a valid Phone Number (Numbers only).");
			}

			var obj = { EmployeeId: strId, EmployeeName: strName, EmployeeAddress: strAddress, Phone: strPhone };
			var jsonString = JSON.stringify(obj);

			// Send AJAX request
			if (strId !== "") {
				$.ajax({
					method: "PUT",
					url: "http://localhost:8080/EJBISWebProject/RestServletEmployee/" + strId,
					data: jsonString,
					dataType: 'json',
					error: ajaxUpdateReturnError,
					success: ajaxUpdateReturnSuccess
				});
				// Show success message
				document.getElementById("error-label-employee").innerHTML = "Employee was successfully updated!";
				// Clear input fields
				$("#empIdAdd").val("");
				$("#empName").val("");
				$("#empAddress").val("");
				$("#empPhone").val("");
				return true;
			} else {
				throw new Error("Please enter a valid Employee ID.");
			}

		} catch (error) {
			// Handle error and show error message
			console.error("An error occurred: ", error);
			document.getElementById("error-label-employee").innerHTML = error.message;
			return false;
		}
	}


	// Populate employee select box
	function populateEmployeeSelectBox() {
		try {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBISWebProject/RestServletEmployee/",
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
		if (result.EmployeeId !== "") {
			parseJsonFileEmployee(result);
			populateEmployeeSelectBox();
			$("#error-label-employee").empty();
			$("#error-label-employee").append("Chosen employee found.");
		} else {
			$("#error-label-employee").empty();
			$("#error-label-employee").append("ID not found. Please enter a valid ID");
			clearFields();
			clearTable();
		}

	}

	function ajaxRestReturnError(result, status, xhr) {
		console.log("Ajax-find Employee: " + status);
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Error in fetching employee");
	}

	function ajaxUpdateReturnSuccess(result, status, xhr) {
		clearFields();
		displayEmployees(result);
		populateEmployeeSelectBox();
		// $("#empName").attr("placeholder", "Employee updated");
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Chosen employee updated.");
	}

	function ajaxUpdateReturnError(result, status, xhr) {
		console.log("Ajax-update employee: " + status);
		displayEmployees(result);
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Error updating employee");
	}

	function ajaxDelReturnSuccess(result, status, xhr) {
		clearFields();
		clearTable();
		displayEmployees(result);
		populateEmployeeSelectBox();
		// $("#EmployeeName").attr("placeholder", "Employee deleted");
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Chosen employee deleted.");
	}

	function ajaxDelReturnError(result, status, xhr) {
		console.log("Ajax-find Employee: " + status);
		displayEmployees(result);
		$("#error-label-employee").empty();
		$("#error-label-employee").append("Error deleting employee");
	}


	function parseJsonFileEmployee(result) {
		try {
			clearTable();
			clearFields();
			var row = $("<tr>");
			row.append($("<td>").text(result.EmployeeId));
			row.append($("<td>").text(result.EmployeeName));
			row.append($("<td>").text(result.EmployeeAddress));
			row.append($("<td>").text(result.Phone));
			$("#employeeTable tbody").empty().append(row);
			$("#empIdAdd").val(result.EmployeeId);
			$("#empName").val(result.EmployeeName);
			$("#empAddress").val(result.EmployeeAddress);
			$("#empPhone").val(result.Phone);
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	function clearFields() {
		try {
			$("#empIdAdd").val("");
			$("#empName").val("");
			$("#empAddress").val("");
			$("#empPhone").val("");
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

})();