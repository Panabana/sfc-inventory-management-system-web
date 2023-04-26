<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>IMS - REST</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	
</script>
<script src=js/scripts.js>
	
</script>
<script src=js/customerScripts.js>	
</script>
<script src=js/purchaseScripts.js>
</script>

<body>
	<%@ include file="header.jsp"%>

	<div class="main-content">

		<!-- Employees section -->
		<div class="section" id="Employee">
			<fieldset>
				<legend>Employees Data</legend>
				<div class="left-side">
					<div class="table-container">
						<table id="employeeTable">
							<thead>
								<tr>
									<th>Name</th>
									<th>Employee ID</th>
									<th>Address</th>
									<th>Phone Number</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><span id="EmployeeId"></span></td>
									<td><span id="EmployeeName"></span></td>
									<td><span id="EmployeeAddress"></span></td>
									<td><span id="EmployeePhone"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="right-side">
					<div class="search-form">
						<form action="#">
							<div class="form-group">
								<input type="text" id="empId" name="empId" class="form-control"
									placeholder="Enter ID...">
								<button type="submit" class="btn" id="FindBtn">Search</button>
								<button type="submit" class="btn" id="findAllBtn">Find
									all</button>

							</div>
						</form>
					</div>
					<div class="insert-form">
						<form>
							<h2 style="text-align: center;">Employee Form</h2>
							<div class="form-group">
								<input type="text" class="form-control item" id="empIdAdd"
									name="empIdAdd" placeholder="Employee ID" disabled>
							</div>
							<div class="form-group">
								<input type="text" class="form-control item" id="empName"
									name="empNameAdd" placeholder="Name">
							</div>
							<div class="form-group">
								<input type="text" class="form-control item" id="empAddress"
									name="empAddressAdd" placeholder="Address">
							</div>
							<div class="form-group">
								<input type="text" class="form-control item" id="empPhone"
									name="empPhoneAdd" placeholder="Phone Number">
							</div>
							<div class="button-container">
								<button type="submit" class="btn" name="addEmpBtn"
									value="Add Employee" id="addEmpBtn">Add</button>
								<button type="submit" class="btn" name="updtEmpBtn"
									value="updtEmpBtn" id="updtEmpBtn">Update</button>
								<button type="submit" class="btn" id="delEmpBtn">Remove</button>
							</div>
							<div class="error-label">
								<!-- Error messages will be displayed here -->
								<p>User messages will be displayed here</p>
							</div>
						</form>
					</div>
				</div>
			</fieldset>
		</div>

		<!-- Customers section -->
		<div class="section" id="Customer">
			<fieldset>
				<legend>Customers Data</legend>
				<div class="left-side">
					<div class="table-container">
						<table id="customerTable">
							<thead>
								<tr>
									<th>Customer ID</th>
									<th>Name</th>
									<th>Address</th>
									<th>Phone Number</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><span id=CustomerId></span></td>
									<td><span id="CustomerName"></span></td>
									<td><span id="CustomerAddress"></span></td>
									<td><span id="CustomerPhone"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="right-side">
					<div class="search-form">
						<form action="#">
							<div class="form-group">
								<input type="text" id="customerId" name="customerId"
									class="form-control" placeholder="Enter ID...">
								<button type="submit" class="btn" id="FindCustomerBtn">Search</button>
								<button type="submit" class="btn" id="findAllCustomersBtn">Find
									all</button>

							</div>
						</form>
					</div>
					<div class="insert-form">
						<form>
							<h2 style="text-align: center;">Customer Form</h2>
							<div class="form-group">
								<input type="text" class="form-control item" id="customerIdAdd"
									name="customerId" placeholder="Customer ID" disabled>
							</div>
							<div class="form-group">
								<input type="text" class="form-control item" id="customerName"
									name="customerName" placeholder="Name">
							</div>
							<div class="form-group">
								<input type="text" class="form-control item"
									id="customerAddress" name="customerAddress"
									placeholder="Address">
							</div>
							<div class="form-group">
								<input type="text" class="form-control item" id="customerPhone"
									name="customerPhone" placeholder="Phone Number">
							</div>
							<div class="button-container">
								<button type="submit" class="btn" value="addCustomerBtn"
									id="addCustomerBtn">Add</button>
								<button type="submit" class="btn" name=updtCustBtn
									value="updtCustBtn" id="updtCustBtn">Update</button>
								<button type="submit" class="btn" id="delCustBtn">Remove</button>
							</div>
							<div class="error-label">
								<!-- Error messages will be displayed here -->
								<p>User messages will be displayed here</p>
							</div>
						</form>
					</div>
				</div>
			</fieldset>
		</div>
	

	<!-- Purchase section -->
	<div class="section" id="Purchase">
		<fieldset>
			<legend>Purchases Data</legend>
			<div class="left-side">
				<div class="table-container">
					<table id="purchaseTable">
						<thead>
							<tr>
								<th>Purchase ID</th>
								<th>Customer</th>
								<th>Employee</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span id=PurchaseId></span></td>
								<td><span id="CustomerId"></span></td>
								<td><span id="EmployeeId"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="right-side">
				<div class="search-form">
					<form action="#">
						<div class="form-group">
							<input type="text" id="purId" name="purchaseId"
								class="form-control" placeholder="Enter ID...">
							<button type="submit" class="btn" id="findPurBtn">Search</button>
							<button type="submit" class="btn" id="findAllPurBtn">Find
								all</button>

						</div>
					</form>
				</div>
				<div class="insert-form">
					<form>
						<h2 style="text-align: center;">Purchase Form</h2>
						<div class="form-group">
							<select name="purchase-id" id="purchase-id">
								<option disabled selected value="">Select a purchase</option>
								<c:forEach var="" items="">
									<option value=""></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<select name="customer-id" id="customerSelect">
								<option disabled selected value="">Select a customer</option>
								<c:forEach var="" items="">
									<option value=""></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<select name="employee-id" id="employeeSelect">
								<option disabled selected value="">Select an employee</option>
								<c:forEach var="" items="">
									<option value=""></option>
								</c:forEach>
							</select>
						</div>
						<div class="button-container">
							<button type="submit" class="btn" value="addPurBtn"
								id="addPurBtn">Add</button>
							<button type="submit" class="btn" name=updtpurchaseBtn
								value="updtCustBtn" id="updtCustBtn">Update</button>
							<button type="submit" class="btn" id="delPurBtn">Remove</button>
						</div>
						<div class="error-label">
							<!-- Error messages will be displayed here -->
							<p>User messages will be displayed here</p>
						</div>
					</form>
				</div>
			</div>
		</fieldset>
	</div>
</div>







	<div class="main">
		<form action="#">
			<label for="empId">Employee Id:</label> <input type="number"
				id="empId" name="empId"><br> <br> <input
				type="submit" value="Find Employee" id="FindBtn">
		</form>
		<button type="submit" class="findAllBtn" id="findAllBtn">Find
			All</button>
		<button type="submit" class="deleteEmployee" id="delEmpBtn">Delete
			Employee</button>
		<table id="employeeTable">
			<caption>Employees</caption>
			<thead>
				<tr>
					<th>Employee name</th>
					<th>Employee Id</th>
					<th>Address</th>
					<th>Phone</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span id="EmployeeName"></span></td>
					<td><span id="EmployeeId"></span></td>
					<td><span id="EmployeeAddress"></span></td>
					<td><span id="EmployeePhone"></span></td>
				</tr>
			</tbody>
		</table>
		<form action="#">
			<label for="empIdAdd">Employee Id:</label> <input type="number"
				id="empIdAdd" name="empIdAdd"><br> <br> <label
				for="empAddress">Employee Name:</label> <input type="text"
				id="empName" name="empNameAdd"><br> <br> <label
				for="empAddress">Employee Address:</label> <input type="text"
				id="empAddress" name="empAddressAdd"><br> <br> <label
				for="empPhone">Employee Phone:</label> <input type="number"
				id="empPhone" name="empPhoneAdd"><br> <br> <input
				type="submit" value="Add Employee" id="addEmpBtn"> <input
				type="submit" value="Update Employee" id="updtEmpBtn">
		</form>





		<!-- 		
		<table id="customerTable">
			<caption>Customers</caption>
			<thead>
				<tr>
					<th>Customer name</th>
					<th>Customer Id</th>
					<th>Address</th>
					<th>Phone</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span id="CustomerName"></span></td>
					<td><span id="CustomerId"></span></td>
					<td><span id="CustomerAddress"></span></td>
					<td><span id="CustomerPhone"></span></td>
				</tr>
			</tbody>
		</table>
		<form action="#">
			<label for="customerId">Customer Id:</label> <input type="number"
				id="customerId" name="customerId"><br> <br> <input
				type="submit" value="Find Customer" id="FindCustomerBtn">
		</form>
		<button type="submit" class="findAllCustomersBtn"
			id="findAllCustomersBtn">Find All</button>
		<button type="submit" class="deleteCustomer" id="delCustBtn">Delete
			Customer</button>
		<form action="#" method="POST">
			<label for="customerIdAdd">Customer ID:</label> <input type="number"
				id="customerIdAdd" name="customerId"><br> <br> 
				<label for="customerName">Customer Name:</label>
				 <input type="text" id="customerName" name="customerName"><br><br> 
				 <label for="customerAddress">Customer Address:</label> 
				 <input type="text" id="customerAddress" name="customerAddress"><br><br>
				 <label for="customerPhone">Customer Phone:</label>
				 <input type="number" id="customerPhone" name="customerPhone"><br><br> 
				 <input type="submit" value="Add Customer" id="addCustomerBtn"> 
				 <input type="submit" value="Update Customer" id="updateCustomerBtn">
		</form> -->

	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>