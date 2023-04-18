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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get action parameter from the URL
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
			response.sendRedirect("EmployeeServlet");
			break;
		case "product":
			response.sendRedirect("ProductServlet");
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
		case "home":
			page = "home.jsp";
			int countEmployees = facade.countAllEmployees();
			request.setAttribute("countEmployees", countEmployees);
			int countCustomers = facade.countAllCustomers();
			request.setAttribute("countCustomers", countCustomers);
			int countProducts = facade.countAllProducts();
			request.setAttribute("countProducts", countProducts);
			int countPurchases = facade.countAllPPurchases();
			request.setAttribute("countPurchases", countPurchases);
			int countSuppliers = facade.countAllSuppliers();
			request.setAttribute("countSuppliers", countSuppliers);
			break;

		default:
			page = "home.jsp";
		}

		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

			// ADD CUSTOMER
		 if ("add-customer".equals(action)) {
			String id = request.getParameter("customer-id");
			String name = request.getParameter("customer-name");
			String address = request.getParameter("customer-address");
			String phoneNumber = request.getParameter("customer-phone");
			int customerId = 0;
			int customerPhoneNbr = 0;

			if (id != null) {
				customerId = Integer.parseInt(id);
			}
			if (phoneNumber != null) {
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

		// UPDATE CUSTOMER
		else if ("update-customer".equals(action)) {
			String id = request.getParameter("customer-id");
			int customerId = Integer.parseInt(id);

			Customer customer = facade.findCustomerById(customerId);
			if (customer != null) {
				if (request.getParameter("customer-name") != null) {
					customer.setName(request.getParameter("customer-name"));
				}
				if (request.getParameter("customer-address") != null) {
					customer.setAddress(request.getParameter("customer-address"));
				}
				if (request.getParameter("customer-phone") != null) {
					customer.setPhoneNbr(Integer.parseInt(request.getParameter("customer-phone")));
				}
			}
			facade.updateCustomer(customer);
			response.sendRedirect("ControllerServlet?action=customer");

		
		
		} else if ("add-supplier".equals(action)) {
			String id = request.getParameter("supplier-id");
			String name = request.getParameter("supplier-name");
			String address = request.getParameter("supplier-address");
			String phoneNbr = request.getParameter("supplier-phone");
			int supplierId = 0;
			int supplierPhone = 0;

			if (id != null) {
				supplierId = Integer.parseInt(id);
			}
			if (phoneNbr != null) {
				supplierPhone = Integer.parseInt(phoneNbr);
			}
			Supplier supplier = new Supplier();
			supplier.setSupplierId(supplierId);
			supplier.setSupplierName(name);
			supplier.setSupplierAddress(address);
			supplier.setPhoneNumber(supplierPhone);
			facade.createSupplier(supplier);
			response.sendRedirect("ControllerServlet?action=supplier");
		} else if ("update-supplier".equals(action)) {
			String id = request.getParameter("supplier-id");
			int supplierId = Integer.parseInt(id);

			Supplier supplier = facade.findSupplierById(supplierId);
			if (supplier != null) {
				if (request.getParameter("supplier-name") != null) {
					supplier.setSupplierName(request.getParameter("supplier-name"));
				}
				if (request.getParameter("supplier-address") != null) {
					supplier.setSupplierAddress(request.getParameter("supplier-address"));
				}
				if (request.getParameter("supplier-phone") != null) {
					supplier.setPhoneNumber(Integer.parseInt(request.getParameter("supplier-phone")));
				}
			}
			facade.updateSupplier(supplier);
			response.sendRedirect("ControllerServlet?action=supplier");
		} else {
			doGet(request, response);
		}

	}

}
