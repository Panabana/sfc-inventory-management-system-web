/**
 * 
 */
 $(document).ready(function() {
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
				$("#CustomerName").text(result.CustomerName);
				$("#CustomerId").text(result.CustomerId);
				$("#CustomerAddress").text(result.CustomerAddress);
				$("#CustomerPhone").text(result.Phone);
				
				$("#customerName").val(result.CustomerName);
				$("#customerIdAdd").val(result.CustomerId);
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

	$("#findAllCustomersBtn").click(function() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/EJBISWebProject/RestServletCustomer/",
			success: function(result) {
				displayCustomers(result);
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching customers:", error);
			}
		});
	});


	function displayCustomers(customers) {
		// Clear existing table rows
		$("#customerTable tbody").empty();


		$.each(customers, function(index, customer) {
			var row = $("<tr>");
			row.append($("<td>").text(customer.CustomerName));
			row.append($("<td>").text(customer.CustomerId));
			row.append($("<td>").text(customer.CustomerAddress));
			row.append($("<td>").text(customer.Phone));
			$("#customerTable tbody").append(row);
		});
	}

	$("#delCustBtn").click(function() {
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
				$("#CustomerName").attr("placeholder", "Customer deleted");
			}

			function ajaxDelReturnError(result, status, xhr) {
				alert("Error");
				console.log("Ajax-find Customer: " + status);
			}
		}
	})
	
	$("#addCustomerBtn").click(function() {
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
            $("#customerName").attr("placeholder", "Customer added");
        }
        function ajaxAddReturnError(result, status, xhr) {
            alert("Error Add");
            console.log("Ajax-find customer: " + status);
        }
    }
})

});