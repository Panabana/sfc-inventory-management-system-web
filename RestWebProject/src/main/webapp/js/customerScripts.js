(function() {

	$(document).ready(function() {
		// Call the function to populate the select box
		populateCustomerSelectBox();

		// Event listeners
		initEventListeners();
	});

	function initEventListeners() {
		try {
			// Event listener for the select box
			$('#customerSelect').on('change', function() {
				// Retrieve the selected customer ID and name
				var selectedCust = $(this).val();
				console.log(selectedCust);
			});

			$("#FindCustomerBtn").click(findCustomerById);

			$("#findAllCustomersBtn").click(findAllCustomers);

			$("#delCustBtn").click(deleteCustomerById);

			$("#addCustomerBtn").click(addCustomer);

			$("#updtCustBtn").click(updateCustomer);

		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}

	// Display customer table
	function displayCustomers(customers) {
		// Clear existing table rows
		$("#customerTable tbody").empty();

		$.each(customers, function(index, customer) {
			var row = $("<tr>");
			row.append($("<td>").text(customer.CustomerId));
			row.append($("<td>").text(customer.CustomerName));
			row.append($("<td>").text(customer.CustomerAddress));
			row.append($("<td>").text(customer.Phone));
			$("#customerTable tbody").append(row);
		});
	}

	function clearFields() {
		$("#customerId").val("");
		$("#customerIdAdd").val("");
		$("#customerName").val("");
		$("#customerAddress").val("");
		$("#customerPhone").val("");
	}

	function clearTable() {
		$("#customerTable tbody").empty();
	}

	// Find Customer by ID
	function findCustomerById(event) {
		try {
			event.preventDefault();

			var strValue = $("#customerId").val();
			if (strValue != "") {
				$.ajax({
					method: "GET",
					url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/" + strValue,
					error: ajaxRestReturn_Error,
					success: ajaxRestReturn_Success
				})

				function ajaxRestReturn_Success(result, status, xhr) {
					if (result.CustomerId !== "") {
						parseJsonFileCustomer(result);
						populateCustomerSelectBox();
						$("#error-label-customer").empty();
						$("#error-label-customer").append("Chosen customer found.");
					} else {
						$("#error-label-customer").empty();
						$("#error-label-customer").append("ID not found. Please enter a valid ID.");
						clearFields();
						clearTable();
					}
				}

				function ajaxRestReturn_Error(result, status, xhr) {
					console.log("Ajax-find Customer: " + status);
					$("#error-label-customer").empty();
					$("#error-label-customer").append("Please enter a valid ID.");
				}

				function parseJsonFileCustomer(result) {
					/*
					console.log($("#customerId").val());
					console.log("Before each:" + result.customerId);

					$.each(result, function(customer) {
						if ($("#customerId").val() != customer.CustomerId) {
							console.log("In if:" + result.customerId);
							ajaxRestReturn_Error();
						}
						console.log("In each:" + result.customerId);
					});
					*/

					clearTable();
					clearFields();

					var row = $("<tr>");
					row.append($("<td>").text(result.CustomerId));
					row.append($("<td>").text(result.CustomerName));
					row.append($("<td>").text(result.CustomerAddress));
					row.append($("<td>").text(result.Phone));
					$("#customerTable tbody").append(row);

					$("#customerIdAdd").val(result.CustomerId);
					$("#customerName").val(result.CustomerName);
					$("#customerAddress").val(result.CustomerAddress);
					$("#customerPhone").val(result.Phone);

				}

				function clearFields() {
					$("#customerName").val("");
					$("#customerIdAdd").val("");
					$("#customerAddress").val("");
					$("#customerPhone").val("");
				}
			} else {
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Please enter an ID");
			}
		} catch (error) {
			console.log("Error: " + error);
		}

	}

	// Find all customers
	function findAllCustomers(event) {

		event.preventDefault();

		$.ajax({
			method: "GET",
			url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/",
			success: function(result) {
				clearTable();
				displayCustomers(result);
				$("#error-label-customer").empty();
				$("#error-label-customer").append("All customers found.");
				populateCustomerSelectBox();
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching customers:", error);
				$(".#error-label-customer").append("Error in fetching customers");
			}
		});

	}

	//Delete customer by ID
	function deleteCustomerById(event) {
		event.preventDefault();

		var strValue = $("#customerId").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE",
				url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/" + strValue,
				success: function(result) {
					clearTable();
					displayCustomers(result);
					clearFields();

					$("#error-label-customer").empty();
					$("#error-label-customer").append("Chosen customer deleted.");
				},
				error: function(xhr, status, error) {
					console.error("Error in deleting customers:", error);
					$(".#error-label-customer").append("Error in deleting customer");
				}
			});

			function ajaxDelReturnSuccess(result, status, xhr) {
				clearFields();
				displayCustomers(Customers);
				populateCustomerSelectBox();
				$("#CustomerName").attr("placeholder", "Customer deleted");
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Chosen customer deleted.");
			}

			function ajaxDelReturnError(result, status, xhr) {
				console.log("Ajax-find Customer: " + status);
				displayCustomers(result);
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Error deleting customer");
			}
		}
	}

	// Add new customer
	function addCustomer(event) {
		try {
			event.preventDefault();
			
			var strId = $("#customerIdAdd").val(); 
			var strName = $("#customerName").val();
			var strAddress = $("#customerAddress").val();
			var strPhone = $("#customerPhone").val();
			
			// Validate input fields
			if (strName === "" || !/^[a-zA-Z\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strName)) {
				throw new Error("Please enter a valid Name (Letters only).");
			} else if (strAddress === "" || !/^[a-zA-Z0-9\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strAddress)) {
				throw new Error("Please enter an Address (Only letters and numbers allowed).");
			} else if (strPhone === "" || !/^\d{1,10}$/.test(strPhone)) {
				throw new Error("Please enter a valid Phone Number (numbers only).");
			}else if (strId === "" || !/^\d{1,10}$/.test(strId)) {
				throw new Error("Please enter a valid ID (numbers only).");
			}
			
			// Create Customer object
			var obj = {CustomerId: strId, CustomerName: strName, CustomerAddress: strAddress, Phone: strPhone };
			var jsonString = JSON.stringify(obj);

			// Send AJAX request
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/",
				data: jsonString,
				dataType: 'json',
				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			});

			function ajaxAddReturnSuccess(result, status, xhr) {
				clearFields();
				displayCustomers(result);
				populateCustomerSelectBox();
				$("#customerName").attr("placeholder", "Customer added");
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Customer added.");
			}

			function ajaxAddReturnError(result, status, xhr) {
				console.log("Ajax-find customer: " + status);
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Customer with ID already exists!");
			}

			// Show success message
			document.getElementById("error-label-customer").innerHTML = "Customer was successfully added!";
			// Clear input fields
			$("#customerIdAdd").val("");
			$("#customerName").val("");
			$("#customerAddress").val("");
			$("#customerPhone").val("");
			return true;

		} catch (error) {
			// Handle error and show error message
			console.error("An error occurred: ", error);
			document.getElementById("error-label-customer").innerHTML = error.message;
			return false;
		}
	}

	// Update customer
	function updateCustomer(event) {
		try {
			event.preventDefault();

			var strId = $("#customerIdAdd").val();
			var strName = $("#customerName").val();
			var strAddress = $("#customerAddress").val();
			var strPhone = $("#customerPhone").val();

			// Validate input fields
			if (strId === "" || !/^\d+$/.test(strId)) {
				throw new Error("Please enter a valid Customer ID (Numbers only).");
			} else if (strName === "" || !/^[a-zA-Z\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strName)) {
				throw new Error("Please enter a valid Name (Letters only).");
			} else if (strAddress === "" || !/^[a-zA-Z0-9\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(strAddress)) {
				throw new Error("Please enter an Address (Only letters and numbers allowed).");
			} else if (strPhone === "" || !/^\d{1,10}$/.test(strPhone)) {
				throw new Error("Please enter a valid Phone Number (numbers only).");
			}

			var obj = { CustomerId: strId, CustomerName: strName, CustomerAddress: strAddress, Phone: strPhone };
			var jsonString = JSON.stringify(obj);
			if (strId != "") {
				$.ajax({
					method: "PUT",
					url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/" + strId,
					data: jsonString,
					dataType: 'json',
					error: ajaxUpdateReturnError,
					success: ajaxUpdateReturnSuccess
				})
			}

			function ajaxUpdateReturnSuccess(result, status, xhr) {
				$("#customerIdAdd").val("");
				clearFields();
				displayCustomers(result);
				populateCustomerSelectBox();
				document.getElementById("error-label-customer").innerHTML = "Customer was successfully updated!";
				$("#customerName").attr("placeholder", "Customer updated");
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Chosen customer updated");
			}

			function ajaxUpdateReturnError(xhr, status, error) {
				$("#error-label-customer").empty();
				$("#error-label-customer").append("Error updating customer");
				var errorMessage = xhr.responseText || "Error updating customer";
				document.getElementById("error-label-customer").innerHTML = errorMessage;
				console.error("An error occurred: ", error);
				console.log("Erorr in ajax-update customer: " + status);
				displayCustomers(result);
			}
		} catch (error) {
			// Handle error and show error message
			console.error("An error occurred: ", error);
			document.getElementById("error-label-customer").innerHTML = error.message;
		}
	}



	// Populate customer select box
	function populateCustomerSelectBox() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/",
			success: function(result) {
				var selectBox = $("#customerSelect");
				selectBox.empty();
				$.each(result, function(index, customer) {
					var option = $("<option>").val(customer.CustomerId).text(customer.CustomerId + " - " + customer.CustomerName);
					selectBox.append(option);
				});
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching customers:", error);
			}
		});
	}

})();