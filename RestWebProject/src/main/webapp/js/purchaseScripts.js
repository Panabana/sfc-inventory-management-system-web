(function() {

	$(document).ready(function() {
		// Call the function to populate the select box
		populatePurchaseSelectBox();

		// Event listeners
		initEventListeners();
	});

	function initEventListeners() {
		// Event listener for the select box
		$('#purchaseSelect').on('change', function() {
			// Retrieve the selected purchase ID and name
			var selectedPur = $(this).val();
			console.log(selectedPur);
		});

		$("#findPurBtn").click(findPurchaseById);

		$("#findAllPurBtn").click(findAllPurchases);

		$("#delPurBtn").click(deletePurchaseById);

		$("#addPurBtn").click(addPurchase);

		// "Aaand this is where i'd put an update. IF I HAD ONE" -Fairly Odd Parents
		$("#").click(updatePurchase);
	}

	// Display purchase table
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

	// Find Purchase by ID
	function findPurchaseById(event) {

		event.preventDefault();
		
		var strValue = $("#purchaseSelect").val();
		if (strValue != "") {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBISWebProject/RestServletPurchase/" + strValue,
				error: ajaxRestReturn_Error,
				success: ajaxRestReturn_Success
			})
	
			function ajaxRestReturn_Success(result, status, xhr) {
				parseJsonFilePurchase(result);
				populatePurchaseSelectBox();
				$("#error-label-purchase").empty();
				$("#error-label-purchase").append("Chosen purchase found.");
			}

			function ajaxRestReturn_Error(result, status, xhr) {
				console.log("Ajax-find Purchase: " + status);
			}

			function parseJsonFilePurchase(result) {
				clearTable();
				

				$.each(result, function() {
					var row = $("<tr>");
					row.append($("<td>").text(result.purchaseId));
					row.append($("<td>").text(result.employeeId));
					row.append($("<td>").text(result.customerId));
				
					$("#purchaseTable tbody").empty().append(row);
					
					$("#employeeSelect").val(result.employeeId);
					$("#customerSelect").val(result.customerId);
				});

				//$("#PurPurchaseId").text(result[0].purchaseId);
				//$("#PurEmployeeId").text(result[0].employeeId);
				//$("#PurCustomerId").text(result[0].customerId);

			}
		}

	}

	// Find all purchases
	function findAllPurchases(event) {
		
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
		
	}

	// Delete purchase by ID
	function deletePurchaseById(event) {
		
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
					// clearFields();
					displayPurchases(result);
					populatePurchaseSelectBox();
					$("#employeeId").attr("placeholder", "Purchase deleted");
					$("#error-label-purchase").empty();
					$("#error-label-purchase").append("Chosen purchase deleted.");
				}

				function ajaxDelReturnError(result, status, xhr) {
					console.log("Ajax-find Purchase: " + status);
					$("#error-label-purchase").empty();
					$("#error-label-purchase").append("Error in deleting purchase.");
				}
			}
		
	}

	// Add new purchase
	function addPurchase(event) {
		
			event.preventDefault();

			var purchaseId = $("#employeeSelect").val();
			var employeeId = $("#employeeSelect").val();
			var customerId = $("#customerSelect").val();

			var obj = { purchaseId: purchaseId, employeeId: employeeId, customerId: customerId };
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
					// clearFields();
					displayPurchases(result);
					populatePurchaseSelectBox();
					$("#purchaseAmountAdd").attr("placeholder", "Purchase added");
					$("#error-label-purchase").empty();
					$("#error-label-purchase").append("Purchase added.");
				}
				function ajaxAddReturnError(result, status, xhr) {
					console.log("Ajax-add purchase: " + status);
					$("#error-label-purchase").empty();
					$("#error-label-purchase").append("Error in adding purchase.");
				}
			}
		
	}


	// Populate purchase select box
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

	/*
	
	// Commented out because maybe uneccesary
	
	function clearFields() {
		try {
			$("#PurchaseSelect").text("");
			$("#customerSelect").text("");
			$("employeeSelect").text("");
		} catch (error) {
			console.error("An error occurred: ", error);
		}
	}
	*/

})();