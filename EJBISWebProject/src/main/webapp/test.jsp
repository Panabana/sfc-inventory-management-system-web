<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>IMS - Test</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
	<main>
		<div class="main-content">
			<h1>IMS Unit Test</h1>
			<div>
			<p>Please choose the unit tests you would like to run.</p>
				<form action=//NEEDS DEFINED ACTION>
					<input type="checkbox" id="productEntityTest" name="productEntityTest">
					<label for="productEntityTest">Product entity test</label><br>
					<input type="checkbox" id="supplierEntityTest" name="supplierEntityTest">
					<label for="supplierEntityTest">Supplier entity test</label><br>
					<input type="checkbox" id="purchaseEntityTest" name="purchaseEntityTest">
					<label for="purchaseEntityTest">Purchase entity test</label><br>
					<input type="checkbox" id="customerEntityTest" name="customerEntityTest">
					<label for="customerEntityTest">Customer entity test</label><br>
					<input type="checkbox" id="employeeEntityTest" name="employeeEntityTest">
					<label for="employeeEntityTest">Employee entity test</label><br>
					<input type="submit" value="Run">
				</form>
			</div>
		</div>
	</main>
	<%@ include file="footer.jsp" %>
</body>
</html>