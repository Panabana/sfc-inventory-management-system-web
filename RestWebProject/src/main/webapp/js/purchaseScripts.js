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

		$("#findPurBtn").click(findPurchaseByID);

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
	function findPurchaseByID() {
		$(document).on("click", "#findPurBtn", function(event) {
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
					displayPurchases(result);
					$("#error-label-purchase").empty();
					$("#error-label-purchase").append("Chosen purchase found.");
					populatePurchaseSelectBox();
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
	}

	// Find all purchases
	function findAllPurchases() {
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
	}

	// Delete purchase by ID
	function deletePurchaseById() {
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
					// clearFields();
					displayPurchases(result);
					$("#employeeId").attr("placeholder", "Purchase deleted");
					populatePurchaseSelectBox();
				}

				function ajaxDelReturnError(result, status, xhr) {
					alert("Error");
					console.log("Ajax-find Purchase: " + status);
				}
			}
		});
	}

	// Add new purchase
	function addPurchase() {
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
					// clearFields();
					displayPurchases(result);
					$("#purchaseAmountAdd").attr("placeholder", "Purchase added");
					populatePurchaseSelectBox();
				}
				function ajaxAddReturnError(result, status, xhr) {
					alert("Error Add");
					console.log("Ajax-add purchase: " + status);
				}
			}
		});
	}

	// Update purchase
	function updatePurchase() {

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