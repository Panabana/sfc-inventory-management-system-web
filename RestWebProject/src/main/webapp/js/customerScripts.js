(function() {

	$(document).ready(function() {
		try {
			populateCustomerSelectBox();

			// Event listener for the select box
			$('#customerSelect').on('change', function() {
				// Retrieve the selected employee ID and name
				var selectedCust = $(this).val();
				console.log(selectedCust);
			});

			$(document).on("click", "#FindCustomerBtn", function(event) {
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
						parseJsonFileCustomer(result);
						$("#error-label-customer").empty();
						$("#error-label-customer").append("Chosen customer found.");
						populateCustomerSelectBox();
					}

					function ajaxRestReturn_Error(result, status, xhr) {
						alert("Error in rest Service");
						console.log("Ajax-find Customer: " + status);
					}

					function parseJsonFileCustomer(result) {
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
						$("#CustomerName").text("");
						$("#CustomerId").text("");
						$("#CustomerAddress").text("");
						$("#CustomerPhone").text("");
					}
				}
			});

			$("#findAllCustomersBtn").click(function(event) {
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
			});

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

			$("#delCustBtn").click(function(event) {
				event.preventDefault();

				var strValue = $("#customerId").val();
				if (strValue != "") {
					$.ajax({
						method: "DELETE",
						url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/" + strValue,
						success: function(result) {
							clearTable();
							displayCustomers(result);
							$("#error-label-customer").empty();
							$("#error-label-customer").append("Chosen customer deleted.");
						},
						error: function(xhr, status, error) {
							console.error("Error in deleting customers:", error);
							$(".#error-label-customer").append("Error in deleting customer");
						}
					})

					function ajaxDelReturnSuccess(result, status, xhr) {
						clearFields();
						displayCustomers(Customers);
						// $("#CustomerName").attr("placeholder", "Customer deleted");
						populateCustomerSelectBox();
					}

					function ajaxDelReturnError(result, status, xhr) {
						alert("Error");
						console.log("Ajax-find Customer: " + status);
					}
				}
			});

			$("#addCustomerBtn").click(function(event) {
				event.preventDefault();

				//var strId = $("#customerIdAdd").val();
				var strName = $("#customerName").val();
				var strAddress = $("#customerAddress").val();
				var strPhone = $("#customerPhone").val();

				var obj = { CustomerName: strName, CustomerAddress: strAddress, Phone: strPhone };
				var jsonString = JSON.stringify(obj);
				try {
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
						// $("#customerName").attr("placeholder", "Customer added");
						populateCustomerSelectBox();
					}
					function ajaxAddReturnError(result, status, xhr) {
						alert("Error Add");
						console.log("Ajax-find customer: " + status);
					}
				} catch (error) {
					console.error(error);
				}
			});

			$("#updtCustBtn").click(function(event) {
				event.preventDefault();

				var strId = $("#customerIdAdd").val();
				var strName = $("#customerName").val();
				var strAddress = $("#customerAddress").val();
				var strPhone = $("#customerPhone").val();

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
					function ajaxUpdateReturnSuccess(result, status, xhr) {
						clearFields();
						$("#customerName").attr("placeholder", "Customer updated");
						alert("Success");
						displayCustomers(result);
						populateCustomerSelectBox();
					}
					function ajaxUpdateReturnError(result, status, xhr) {
						alert("Error updating customer");
						console.log("Ajax-update customer: " + status);
						displayCustomers(result);
					}
				}
			})

			function clearFields() {
				$("#CustomerId").text("");
				$("#CustomerName").text("");
				$("#CustomerAddress").text("");
				$("#CustomerPhone").text("");
			}

			function clearTable() {
				$("#customerTable tbody").empty();
			}

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
		} catch (error) {
			console.error("An error occured: ", error);
		}
	});
})();
