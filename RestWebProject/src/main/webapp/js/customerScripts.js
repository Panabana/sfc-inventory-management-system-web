(function() {
	
	$(document).ready(function() {

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
			}

			function ajaxRestReturn_Error(result, status, xhr) {
				alert("Error in rest Service");
				console.log("Ajax-find Customer: " + status);
			}

			function parseJsonFileCustomer(result) {
				clearFields();
				clearTable();
				
				$("#CustomerId").text(result.CustomerId);
				$("#CustomerName").text(result.CustomerName);
				$("#CustomerAddress").text(result.CustomerAddress);
				$("#CustomerPhone").text(result.Phone);

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
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching customers:", error);
				$(".#error-label-customer").append("Error in fetching customers");
			}
		});
	});

	function displayCustomers(customers) {
		// Clear existing table rows
		// $("#customerTable tbody").empty();

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
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			})

			function ajaxDelReturnSuccess(result, status, xhr) {
				clearFields();
				displayCustomers();
				$("#CustomerName").attr("placeholder", "Customer deleted");
			}

			function ajaxDelReturnError(result, status, xhr) {
				alert("Error");
				console.log("Ajax-find Customer: " + status);
			}
		}
	})

	$("#addCustomerBtn").click(function(event) {
		event.preventDefault();

		var strId = $("#customerIdAdd").val();
		var strName = $("#customerName").val();
		var strAddress = $("#customerAddress").val();
		var strPhone = $("#customerPhone").val();

		var obj = { CustomerId: strId, CustomerName: strName, CustomerAddress: strAddress, Phone: strPhone };
		var jsonString = JSON.stringify(obj);
		if (strId != "") {
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/",
				data: jsonString,
				dataType: 'json',
				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			})
			function ajaxAddReturnSuccess(result, status, xhr) {
				clearFields();
				displayCustomers();
				$("#customerName").attr("placeholder", "Customer added");
			}
			function ajaxAddReturnError(result, status, xhr) {
				alert("Error Add");
				console.log("Ajax-find customer: " + status);
			}
		}
	})

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
				alert("Success")
				displayCustomers(result);
			}
			function ajaxUpdateReturnError(result, status, xhr) {
				alert("Error updating customer");
				console.log("Ajax-update customer: " + status);
				displayCustomers(result);
			}
		}
	})

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
});
})();
