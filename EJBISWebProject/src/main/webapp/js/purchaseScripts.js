
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

		
		
		document.addEventListener("DOMContentLoaded", function() {
  var searchForm = document.getElementById("search-form");
  var findPurchaseBtn = document.getElementById("search-btn");

  findPurchaseBtn.addEventListener("click", function(event) {
    event.preventDefault();

    var strValue = document.getElementById("find-purchase-id").value;

    if (strValue == "" || !/^\d{1,10}$/.test(strValue)) {
      var errorLabel = document.getElementById("error-label");
      errorLabel.innerText = "Please enter a valid ID to search for!";
      return;
    }

    var tableRows = document.querySelectorAll("#purchase-table tbody tr");
    var purchaseFound = false;

    tableRows.forEach(function(row) {
      var purchaseId = row.cells[0].innerText;

      if (purchaseId === strValue) {
        purchaseFound = true;

        // Retrieve purchase details
        var customerDetails = row.cells[1].innerText;
        var employeeDetails = row.cells[2].innerText;

        // Extract customer ID and name
        var customerId = customerDetails.split("-")[0];
        var customerName = customerDetails.split("-")[1];

        // Extract employee ID and name
        var employeeId = employeeDetails.split("-")[0];
        var employeeName = employeeDetails.split("-")[1];

        // Fill in the text fields
        document.getElementById("purchase-id").value = strValue;
        document.getElementById("customer-id").value = customerId;
        document.getElementById("employee-id").value = employeeId;

      }
    });

    if (!purchaseFound) {
      var errorLabel = document.getElementById("error-label");
      errorLabel.innerText = "Purchase with ID: " + strValue + " not found.";
    } else {
      var errorLabel = document.getElementById("error-label");
      errorLabel.innerText = "Chosen purchase found.";
    }
  });
});
