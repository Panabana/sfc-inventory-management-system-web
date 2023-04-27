$(document).ready(function() {
			$('#search-btn').click(function() {
				$('search-form').submit()
			});
			var customerId = '${customerId}';
			var customerName = '${customerName}';
			var customerAddress = '${customerAddress}';
			var customerPhoneNbr = '${customerPhoneNumber}';

			$('#customer-id').val(customerId);
			$('#customer-name').val(customerName);
			$('#customer-address').val(customerAddress);
			$('#customer-phone').val(customerPhoneNbr);
		});

		function validateForm() {
			var customerName = document.getElementById("customer-name").value;
			var customerAddress = document.getElementById("customer-address").value;
			var customerPhoneNumber = document.getElementById("customer-phone").value;
			var errorMessage = "";

			if (customerName === "" || !/^[a-zA-ZåäöÅÄÖ]+$/.test(customerName)) {
				errorMessage = "Please enter a valid Name (Letters only)."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else if (customerAddress === ""
					|| !/^[a-zA-Z0-9\såäöÅÄÖ]*$/.test(customerAddress)) {
				errorMessage = "Please enter an Address (Only letters and numbers allowed)."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else if (customerPhoneNumber === ""
					|| !/^\d{1,10}$/.test(customerPhoneNumber)) {
				errorMessage = "Please enter a valid Phone Number (number only)."
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else {
				errorMessage = "Customer was successfully aded!";
				document.getElementById("error-label").innerHTML = errorMessage;
				return true;
			}
		}

		function validateSearchForm() {
		    var customerId = document.getElementById("find-customer-id").value;

		    if (customerId === "" || !/^\d{1,10}$/.test(customerId)) {
		        errorMessage = "Please enter a valid ID (number only) to search for.."
		        document.getElementById("error-label").innerHTML = errorMessage;
		        return false;
		    } else {
		        var customerExists = false;
		        $('#customers-table tbody tr').each(function() {
		            var rowCustomerId = $(this).find('td:eq(0)').text();
		            if (rowCustomerId === customerId) {
		                customerExists = true;
		                return false; // break the loop
		            }
		        });
		        if (!customerExists) {
		            errorMessage = "Customer with ID " + customerId + " doesn't exist.";
		            document.getElementById("error-label").innerHTML = errorMessage;
		            return false;
		        }
		    }
		    return true;
		}
  
 