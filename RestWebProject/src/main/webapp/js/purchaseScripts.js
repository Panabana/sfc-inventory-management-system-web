/**
 * 
 */

 $(document).ready(function() {
	$(document).on("click", "#findPurBtn", function(event) {
		event.preventDefault();
		var strValue = $("#purId").val();
		if (strValue != "") {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/" + strValue,
				error: ajaxRestReturn_Error,
				success: ajaxRestReturn_Success
			})

			function ajaxRestReturn_Success(result, status, xhr) {
				parseJsonFilePurchase(result);
			}

			function ajaxRestReturn_Error(result, status, xhr) {
				alert("Error in rest Service");
				console.log("Ajax-find Purchase: " + status);
			}

			function parseJsonFilePurchase(result) {
				$("#purIdAdd").val(result.purchaseId);
				$("#purEmpIdAdd").val(result.employeeId);
				$("#purCustIdAdd").val(result.customerId);
			}
		}
	});

	$("#findAllPurBtn").click(function(event) {
		event.preventDefault();
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/",
			success: function(result) {
				displayPurchases(result);
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching purchases:", error);
			}
		});
	});

	function displayPurchases(purchases) {
		// Clear existing table rows
		$("#purchaseTable tbody").empty();

		$.each(purchases, function(index, purchase) {
			var row = $("<tr>");
			row.append($("<td>").text(purchase.purchaseId));
			row.append($("<td>").text(purchase.employeeId));
			row.append($("<td>").text(purchase.customerId));
			$("#purchaseTable tbody").append(row);
		});
	}

	$("#delPurBtn").click(function(event) {
		event.preventDefault();

		var strValue = $("#purchaseId").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE",
				url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/" + strValue,
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			})

			function ajaxDelReturnSuccess(result, status, xhr) {
				clearFields();
				$("#employeeId").attr("placeholder", "Purchase deleted");
			}

			function ajaxDelReturnError(result, status, xhr) {
				alert("Error");
				console.log("Ajax-find Purchase: " + status);
			}
		}
	})
	
	$("#addPurBtn").click(function(event) {
    event.preventDefault();

    var employeeId = $("#purEmpIdAdd").val();
    var customerId = $("#purCustIdAdd").val();

    var obj = { employee: { employeeId: employeeId }, customer: { customerId: customerId }};
    var jsonString = JSON.stringify(obj);
    
    if (employeeId != "" && customerId != "") {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/",
            data: jsonString,
            dataType: 'json',
            error: ajaxAddReturnError,
            success: ajaxAddReturnSuccess
        })
        function ajaxAddReturnSuccess(result, status, xhr) {
            clearFields();
            $("#purchaseAmountAdd").attr("placeholder", "Purchase added");
        }
        function ajaxAddReturnError(result, status, xhr) {
            alert("Error Add");
            console.log("Ajax-add purchase: " + status);
        }
    }
})

	
	})