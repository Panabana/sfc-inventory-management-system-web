$(document).ready(function() {
			$('#search-btn').click(function() {
				$('#search-form').submit();
			});

			var purchaseId = '${purchaseId}';
			var employeeId = '${employeeId}';
			var customerId = '${customerId}';

			$('#purchase-id').val(purchaseId);
			$('#employee-id').val(employeeId);
			$('#customer-id').val(customerId);
		});
		
		function validateForm() {
			var employeeId = document.getElementById("employee-id").value;
			var customerId = document.getElementById("customer-id").value;
			var purchaseId = document.getElementById("purchase-id").value;
			var errorMessage = "";
			
			if (employeeId === "") {
				errorMessage = "Please select an Employee ID."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else if (customerId === "") {
				errorMessage = "Please select a Customer ID."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else if (document.activeElement.value === "update-purchase" && purchaseId === "") {
			    errorMessage = "Please select a Purchase ID you want to Update.";
			    document.getElementById("error-label").innerHTML = errorMessage;
			    return false;
			} else {
				errorMessage = "The Purchase was successfully added!"
				document.getElementById("error-label").innerHTML = errorMessage;
				return true;
			}
		}

		function validateSearchForm() {
		    var purchaseId = document.getElementById("find-purchase-id").value;

		    if (purchaseId === "" || !/^\d{1,10}$/.test(purchaseId)) {
		        errorMessage = "Please enter a valid ID (number only) to search for.."
		        document.getElementById("error-label").innerHTML = errorMessage;
		        return false;
		    } else {
		        var purchaseExists = false;
		        $('#purchase-table tbody tr').each(function() {
		            var rowPurchaseId = $(this).find('td:eq(0)').text();
		            if (rowPurchaseId === purchaseId) {
		                purchaseExists = true;
		                return false; // break the loop
		            }
		        });
		        if (!purchaseExists) {
		            errorMessage = "Purchase with ID " + purchaseId + " doesn't exist.";
		            document.getElementById("error-label").innerHTML = errorMessage;
		            return false;
		        }
		    }
		    return true;
		}