

function validateForm() {
	var employeeId = document.getElementById("employee-id").value;
	var employeeName = document.getElementById("employee-name").value;
	var employeeAddress = document.getElementById("employee-address").value;
	var employeePhoneNumber = document.getElementById("employee-phone").value;
	var errorMessage = "";

	if (employeeId === "" || !/^\d*$/.test(employeeId)) {
		errorMessage = "Please enter a valid ID (numbers only).";
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	}else if (employeeName === "" || !/^[a-zA-ZåäöÅÄÖ]+$/.test(employeeName)) {
		errorMessage = "Please enter a valid Name (Letters only)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (employeeAddress === ""
		|| !/^[a-zA-Z0-9\såäöÅÄÖ]*$/.test(employeeAddress)) {
		errorMessage = "Please enter an Address (Only letters and numbers allowed)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (employeePhoneNumber === ""
		|| !/^\d{1,10}$/.test(employeePhoneNumber)) {
		errorMessage = "Please enter a valid Phone Number (numbers only)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (document.activeElement.value === "update-employee" && employeeId === "") {
		errorMessage = "Please enter an Employee ID you want to Update.";
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else {
		errorMessage = "Employee was successfully added!";
		document.getElementById("error-label").innerHTML = errorMessage;
		return true;
	}
}


document.addEventListener("DOMContentLoaded", function() {
	var searchForm = document.getElementById("search-form");
	var findEmployeeBtn = document.getElementById("search-btn");

	findEmployeeBtn.addEventListener("click", function(event) {
		event.preventDefault();

		var strValue = document.getElementById("find-employee-id").value;

		if (strValue == "" || !/^\d{1,10}$/.test(strValue)) {
			var errorLabel = document.getElementById("error-label");
			errorLabel.innerText = "Please enter a valid ID to search for!";
			return;
		}

		var tableRows = document.querySelectorAll("#employee-table tbody tr");
		var employeeFound = false;

		tableRows.forEach(function(row) {
			var employeeId = row.cells[0].innerText;

			if (employeeId === strValue) {
				employeeFound = true;

				// Retrieve customer details
				var employeeName = row.cells[1].innerText;
				var employeeAddress = row.cells[2].innerText;
				var employeePhone = row.cells[3].innerText;

				// Fill in the text fields
				document.getElementById("employee-id").value = strValue;
				document.getElementById("employee-name").value = employeeName;
				document.getElementById("employee-address").value = employeeAddress;
				document.getElementById("employee-phone").value = employeePhone;
			}
		});

		if (!employeeFound) {
			var errorLabel = document.getElementById("error-label");
			errorLabel.innerText = "Employee with ID: " + strValue + " not found.";
		} else {
			var errorLabel = document.getElementById("error-label");
			errorLabel.innerText = "Chosen employee found.";
		}
	});
});



