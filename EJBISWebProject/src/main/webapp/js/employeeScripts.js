

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
	} else if (employeeName === "" || !/^[a-zA-Z\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(employeeName)) {
		errorMessage = "Please enter a valid Name (Letters only)."
		document.getElementById("error-label").innerHTML = errorMessage;
		return false;
	} else if (employeeAddress === "" || !/^[a-zA-Z0-9\u00C4\u00E4\u00D6\u00F6\u00C5\u00E5\s ]+$/.test(employeeAddress)) {
		errorMessage = "Please enter a valid Address (Only letters, numbers, and whitespaces allowed).";
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
		if (document.activeElement.value === "add-employee") {
			var tableRows = document.querySelectorAll("#employee-table tbody tr");
			var employeeExists = false;

			tableRows.forEach(function(row) {
				var existingEmployeeId = row.cells[0].innerText;

				if (existingEmployeeId === employeeId) {
					employeeExists = true;
				}
			});

			if (employeeExists) {
				errorMessage = "Employee with ID: " + employeeId + " already exists!";
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else {
				errorMessage = "Employee was successfully added!";
			}
		} else if (document.activeElement.value === "update-employee") {
			var tableRows = document.querySelectorAll("#employee-table tbody tr");
			var employeeExists = false;

			tableRows.forEach(function(row) {
				var existingEmployeeId = row.cells[0].innerText;

				if (existingEmployeeId === employeeId) {
					employeeExists = true;
				}
			});

			if (!employeeExists) {
				errorMessage = "You can't update a non-existing employee";
				document.getElementById("error-label").innerHTML = errorMessage;
				return false;
			} else {
				errorMessage = "Employee was successfully updated!";
			}
		}

		document.getElementById("error-label").innerHTML = errorMessage;
		return true;
	}
}

document.addEventListener("DOMContentLoaded", function() {
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

				// Retrieve details
				var employeeName = row.cells[1].innerText;
				var employeeAddress = row.cells[2].innerText;
				var employeePhone = row.cells[3].innerText;

				// Fill text fields wit found employee
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



