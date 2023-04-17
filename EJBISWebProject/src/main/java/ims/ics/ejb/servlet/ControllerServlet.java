package ims.ics.ejb.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Product;
import ims.ics.ejb.Purchase;
import ims.ics.ejb.Supplier;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@EJB
	FacadeLocal facade;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get action parameter from the URL
		String sidebarAction = request.getParameter("action");
		if (sidebarAction == null) {
			sidebarAction = "home";
		}
		
		request.setAttribute("activePage", sidebarAction);

		String page = "home.jsp";
		switch (sidebarAction) {
			case "about":
				page = "about.jsp";
				break;
			case "test":
				page = "test.jsp";
				break;
			case "employee":
				page = "employee.jsp";
				List<Employee> employees = facade.findAllEmployees();
				request.setAttribute("employees", employees);
				break;
			case "product":
				page = "product.jsp";
				List<Product> products = facade.findAllProducts();
				request.setAttribute("products", products);
				break;
			case "customer":
				page = "customer.jsp";
				List<Customer> customers = facade.findAllCustomers();
				request.setAttribute("customers", customers);
 				break;
			case "purchase":
				page = "purchase.jsp";
				List<Purchase> purchases = facade.findAllPurchases();
				request.setAttribute("purchases", purchases);
				break;
			case "supplier":
				page = "supplier.jsp";
				List<Supplier> suppliers = facade.findAllSuppliers();
				request.setAttribute("suppliers", suppliers);
				break;
			default:
				page = "home.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");
	    
	    //ADD EMPLOYEE
	    if ("add-employee".equals(action)) {

	        String id = request.getParameter("employee-id");
	        String name = request.getParameter("employee-name");
	        String address = request.getParameter("employee-address");
	        String phoneNumber = request.getParameter("employee-phone");  
	        int employeeId = 0;
	        int employeePhoneNbr = 0;
	        
	        if (id != null) {
	            employeeId = Integer.parseInt(id);
	        }
	        
	        if (phoneNumber != null) {
	            employeePhoneNbr = Integer.parseInt(phoneNumber);
	        }
	        
	        Employee employee = new Employee();
	        employee.setEmployeeId(employeeId);
	        employee.setName(name);
	        employee.setAddress(address);
	        employee.setPhoneNumber(employeePhoneNbr);
	        facade.createEmployee(employee);
	        response.sendRedirect("ControllerServlet?action=employee");
	        
	        //UPDATE EMPLOYEE
	    } else if ("update-employee".equals(action)){
	    	
	    	String id = request.getParameter("employee-id");
	    	int empId = Integer.parseInt(id);
	    	
	    	Employee employee = facade.findEmployeeById(empId);
	    	if(employee != null) {
	    		if(request.getParameter("employee-name")!= null) {
	    			employee.setName(request.getParameter("employee-name"));
	    		}
	    		if(request.getParameter("employee-address")!=null) {
	    			employee.setAddress(request.getParameter("employee-address"));
	    		}
	    		if(request.getParameter("employee-phone")!= null) {
	    			employee.setPhoneNumber(Integer.parseInt(request.getParameter("employee-phone")));
	    		}
	    		
	    		facade.updateEmployee(employee);
	    		response.sendRedirect("ControllerServlet?action=employee");
	    	}
	    	//ADD CUSTOMER
	    } else if ("add-customer".equals(action)) {
	    	String id = request.getParameter("customer-id");
	    	String name = request.getParameter("customer-name");
	    	String address = request.getParameter("customer-address");
	    	String phoneNumber = request.getParameter("customer-phone");
	    	int customerId = 0;
	    	int customerPhoneNbr = 0;
	    	
	    	if(id != null) {
	    		customerId = Integer.parseInt(id);
	    	}
	    	if(phoneNumber != null) {
	    		customerPhoneNbr = Integer.parseInt(phoneNumber);
	    	}
	    	
	    	Customer customer = new Customer();
	    	customer.setCustomerId(customerId);
	    	customer.setName(name);
	    	customer.setAddress(address);
	    	customer.setPhoneNbr(customerPhoneNbr);
	    	facade.createCustomer(customer);
	    	response.sendRedirect("ControllerServlet?action=customer");
	    }
	    
	    //UPDATE CUSTOMER
	    else if("update-customer".equals(action)) {
	    	String id = request.getParameter("customer-id");
	    	int customerId = Integer.parseInt(id);
	    	
	    	Customer customer = facade.findCustomerById(customerId);
	    	if(customer != null) {
	    		if(request.getParameter("customer-name")!= null) {
	    			customer.setName(request.getParameter("customer-name"));
	    		}
	    		if(request.getParameter("customer-address")!=null) {
	    			customer.setAddress(request.getParameter("customer-address"));
	    		}
	    		if(request.getParameter("customer-phone")!=null) {
	    			customer.setPhoneNbr(Integer.parseInt(request.getParameter("customer-phone")));
	    		}
	    	}
	    	facade.updateCustomer(customer);
    		response.sendRedirect("ControllerServlet?action=customer");
	    	
	    }
	    //ADD PRODUCT
	    else if("add-product".equals(action)) {
	    	String id = request.getParameter("product-id");
	    	String name = request.getParameter("product-name");
	    	String price = request.getParameter("product-price");
	    	int productId = 0;
	    	float productPrice = 0;
	    	
	    	if(id !=null) {
	    		productId = Integer.parseInt(id);
	    	}
	    	if(price != null) {
	    		productPrice = Float.parseFloat(price);
	    	}
	    	Product product = new Product();
	    	product.setProductId(productId);
	    	product.setProductName(name);
	    	product.setPrice(productPrice);
	    	facade.createProduct(product);
	    	response.sendRedirect("ControllerServlet?action=product");
	    	
	    }
	    //UPDATE PRODUCT
	    else if("update-product".equals(action)) {
	    	String id = request.getParameter("product-id");
	    	int productId = Integer.parseInt(id);
	    	
	    	Product product = facade.findProductByID(productId);
	    	if(product !=null) {
	    		if(request.getParameter("product-name")!=null) {
	    			product.setProductName(request.getParameter("product-name"));
	    		}
	    		if(request.getParameter("product-price")!=null) {
	    			product.setPrice(Float.parseFloat(request.getParameter("product-price")));
	    		}
	    	}
	    	facade.updateProduct(product);
	    	response.sendRedirect("ControllerServlet?action=product");
	    }
	    else if("add-supplier".equals(action)) {
	    	String id = request.getParameter("supplier-id");
	    	String name = request.getParameter("supplier-name");
	    	String address = request.getParameter("supplier-address");
	    	String phoneNbr = request.getParameter("supplier-phone");
	    	int supplierId = 0;
	    	int supplierPhone = 0;
	    	
	    	if(id !=null) {
	    		supplierId = Integer.parseInt(id);
	    	}
	    	if(phoneNbr != null) {
	    		supplierPhone = Integer.parseInt(phoneNbr);
	    	}
	    	Supplier supplier = new Supplier();
	    	supplier.setSupplierId(supplierId);
	    	supplier.setSupplierName(name);
	    	supplier.setSupplierAddress(address);
	    	supplier.setPhoneNumber(supplierPhone);
	    	facade.createSupplier(supplier);
	    	response.sendRedirect("ControllerServlet?action=supplier");
	    }
	    else if ("update-supplier".equals(action)) {
	    	String id = request.getParameter("supplier-id");
	    	int supplierId = Integer.parseInt(id);
	    	
	    	Supplier supplier = facade.findSupplierById(supplierId);
	    	if(supplier !=null) {
	    		if(request.getParameter("supplier-name")!=null) {
	    			supplier.setSupplierName(request.getParameter("supplier-name"));
	    		}
	    		if(request.getParameter("supplier-address")!=null) {
	    			supplier.setSupplierAddress(request.getParameter("supplier-address"));
	    		}
	    		if(request.getParameter("supplier-phone")!=null) {
	    			supplier.setPhoneNumber(Integer.parseInt(request.getParameter("supplier-phone")));
	    		}
	    	}
	    	facade.updateSupplier(supplier);
	    	response.sendRedirect("ControllerServlet?action=supplier");
	    }
	    else {
	    	doGet(request, response);
	    }
	
	}
	
	

}
