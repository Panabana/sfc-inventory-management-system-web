

function validateForm() {
	var customerId = document.getElementById("customer-id").value;
	var customerName = document.getElementById("customer-name").value;
	var customerAddress = document.getElementById("customer-address").value;
	var customerPhoneNumber = document.getElementById("customer-phone").value;
	var errorMessage = "";

	if (customerId === "" || !/^\d*$/.test(customerId)) {
		errorMessage = "Please enter a valid ID (numbers only).";
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (customerName === "" || !/^[a-zA-Z\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(customerName)) {
		errorMessage = "Please enter a valid Name (Letters only)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (customerAddress === ""
		|| !/^[a-zA-Z0-9\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(customerAddress)) {
		errorMessage = "Please enter a valid Address (Only letters and numbers and whitespaces allowed)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (customerPhoneNumber === ""
		|| !/^\d{1,10}$/.test(customerPhoneNumber)) {
		errorMessage = "Please enter a valid Phone Number (number only)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (document.activeElement.value === "update-customer" && customerId === "") {
		errorMessage = "Please enter a Customer ID you want to Update.";
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else {
		if (document.activeElement.value === "add-customer") {
			var tableRows = document.querySelectorAll("#customers-table tbody tr");
			var customerExists = false;

			tableRows.forEach(function(row) {
				var existingCustomerId = row.cells[0].innerText;

				if (existingCustomerId === customerId) {
					customerExists = true;
				}
			});

			if (customerExists) {
				errorMessage = "Customer with ID: " + customerId + " already exists!";
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else {
				errorMessage = "Customer was successfully added!";
			}
		} else if (document.activeElement.value === "update-customer") {
			var tableRows = document.querySelectorAll("#customers-table tbody tr");
			var customerExists = false;

			tableRows.forEach(function(row) {
				var existingCustomerId = row.cells[0].innerText;

				if (existingCustomerId === customerId) {
					customerExists = true;
				}
			});

			if (!customerExists) {
				errorMessage = "You can't update a non-existing customer";
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else {
				errorMessage = "Customer was successfully updated!";
			}
		}

		document.getElementById("error-label").innerHTML = errorMessage;
		return true;
	}
}

document.addEventListener("DOMContentLoaded", function() {
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

