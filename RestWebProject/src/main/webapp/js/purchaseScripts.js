/**
 * 
 */

$(document).ready(function() {

	// Call the function to populate the select box
	populatePurchaseSelectBox();

	// Event listener for the select box
	$('#purchaseSelect').on('change', function() {
		// Retrieve the selected employee ID and name
		var selectedPur = $(this).val();
		console.log(selectedPur);
	});


	$(document).on("click", "#findPurBtn", function(event) {
		event.preventDefault();
		var strValue = $("#purchaseSelect").val();
		alert(strValue);
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

				clearTable();

				$.each(result, function(index, purchase) {
					var row = $("<tr>");
					row.append($("<td>").text(purchase.purchaseId));
					row.append($("<td>").text(purchase.employeeId));
					row.append($("<td>").text(purchase.customerId));
					$("#purchaseTable tbody").append(row);
				});

				$("#PurPurchaseId").text(result[0].purchaseId);
				$("#PurEmployeeId").text(result[0].employeeId);
				$("#PurCustomerId").text(result[0].customerId);

				$("#employeeSelect").val(result[0].employeeId);
				$("#customerSelect").val(result[0].customerId);
			}
		}
	});

	$("#findAllPurBtn").click(function(event) {
		event.preventDefault();
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/",
			success: function(result) {
				clearTable();
				displayPurchases(result);
				$("#error-label-purchase").empty();
				$("#error-label-purchase").append("All purchases found.");
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching purchases:", error);
				$("#error-label-purchase").append("Error in fetching purchases");
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

		var strValue = $("#purchaseSelect").val();
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

		var purchaseId = $("#employeeSelect").val();
		var employeeId = $("#employeeSelect").val();
		var customerId = $("#customerSelect").val();

		var obj = { purchaseId: purchaseId, employeeId: employeeId, customerId: customerId };
		var jsonString = JSON.stringify(obj);
		alert(jsonString);
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

	function populatePurchaseSelectBox() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/",
			success: function(result) {
				var selectBox = $("#purchaseSelect");
				selectBox.empty();
				$.each(result, function(index, purchase) {
					var option = $("<option>").val(purchase.purchaseId).text(purchase.purchaseId);
					selectBox.append(option);
				});
			},
			error: function(xhr, status, error) {
				console.error("Error in fetching employees:", error);
			}
		});
	}
	function clearTable() {
		$("#purchaseTable tbody").empty();
	}


})