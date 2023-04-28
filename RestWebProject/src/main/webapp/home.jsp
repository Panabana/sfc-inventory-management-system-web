<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!--  Include custom JS files -->
<script src=js/employeeScripts.js></script>
<script src=js/customerScripts.js></script>
<script src=js/purchaseScripts.js></script>
<title>IMS - REST</title>
</head>
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
									<th>Employee ID</th>
									<th>Name</th>
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
									placeholder="Enter ID..." />
								<button type="submit" class="btn" id="findBtn">Search</button>
								<button type="submit" class="btn" id="findAllBtn">Find all</button>
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
									value="AddEmployee" id="addEmpBtn">Add</button>
								<button type="submit" class="btn" name="updtEmpBtn"
									value="updtEmpBtn" id="updtEmpBtn">Update</button>
								<button type="submit" class="btn" id="delEmpBtn">Remove</button>
							</div>
							<div>
								<!-- Error messages will be displayed here -->
								<p id="error-label-employee"></p>
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
									class="form-control" placeholder="Enter ID..." />
								<button type="submit" class="btn" id="FindCustomerBtn">Search</button>
								<button type="submit" class="btn" id="findAllCustomersBtn">Find all</button>
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
							<div>
								<!-- Error messages will be displayed here -->
								<p id="error-label-customer"></p>
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
								<th>Employee</th>
								<th>Customer</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span id=PurPurchaseId></span></td>
								<td><span id="PurEmployeeId"></span></td>
								<td><span id="PurCustomerId"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="right-side">
				<div class="search-form">
					<form action="#">
						<div class="form-group">
						<label>Select purchase:</label>
							<select class="form-group" name="purchase-id" id="purchaseSelect">
								<option disabled selected value="">Select a purchase</option>
								<c:forEach var="" items="">
									<option value=""></option>
								</c:forEach>
							</select>
							<button type="submit" class="btn" id="findPurBtn">Search</button>
							<button type="submit" class="btn" id="findAllPurBtn">Find all</button>
						</div>
					</form>
				</div>
				<div class="insert-form">
					<form>
						<h2 style="text-align: center;">Purchase Form</h2>
						<div class="form-group">
							
						</div>
						<div class="form-group">
						<label>Select customer:</label>
							<select name="customer-id" id="customerSelect">
								<option disabled selected value="">Select a customer</option>
								<c:forEach var="" items="">
									<option value=""></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
						<label>Select employee:</label>
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
							<button type="submit" class="btn" id="delPurBtn">Remove</button>
						</div>
						<div class="error-label">
							<!-- Error messages will be displayed here -->
							<p id="error-label-purchase">User messages will be displayed here</p>
						</div>
					</form>
				</div>
			</fieldset>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>