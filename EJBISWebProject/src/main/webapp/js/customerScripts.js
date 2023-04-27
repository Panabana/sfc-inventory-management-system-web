

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

document.addEventListener("DOMContentLoaded", function() {
	var searchForm = document.getElementById("search-form");
	var findCustomerBtn = document.getElementById("search-btn");

	findCustomerBtn.addEventListener("click", function(event) {
		event.preventDefault();

		var strValue = document.getElementById("find-customer-id").value;

		if (strValue == "" || !/^\d{1,10}$/.test(strValue)) {
			var errorLabel = document.getElementById("error-label");
			errorLabel.innerText = "Please enter a valid ID to search for!";
			return;
		}
		var tableRows = document.querySelectorAll("#customers-table tbody tr");
		var customerFound = false;

		tableRows.forEach(function(row) {
			var customerId = row.cells[0].innerText;

			if (customerId === strValue) {
				customerFound = true;

				// Retrieve customer details
				var customerName = row.cells[1].innerText;
				var customerAddress = row.cells[2].innerText;
				var customerPhone = row.cells[3].innerText;

				// Fill in the text fields
				document.getElementById("customer-id").value = strValue;
				document.getElementById("customer-name").value = customerName;
				document.getElementById("customer-address").value = customerAddress;
				document.getElementById("customer-phone").value = customerPhone;
			}
		});

		if (!customerFound) {
			var errorLabel = document.getElementById("error-label");
			errorLabel.innerText = "Customer with ID: " + strValue + " not found.";
		} else {
			var errorLabel = document.getElementById("error-label");
			errorLabel.innerText = "Chosen customer found.";
		}

	});
});

