package ims.ics.ejb.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FacadeLocal facade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Employee> employees = facade.findAllEmployees();
			request.setAttribute("employees", employees);
			List<Customer> customers = facade.findAllCustomers();
			request.setAttribute("customers", customers);

			String action = request.getParameter("action");
			if ("find-purchase".equals(action)) {
				String id = request.getParameter("find-purchase-id");
				int purchaseId = 0;

				purchaseId = Integer.parseInt(id);
				Purchase purchase = facade.findPurchaseById(purchaseId);
				if (purchase != null) {
					purchaseId = purchase.getPurchaseId();
					int employeeId = purchase.getEmployee().getEmployeeId();
					int customerId = purchase.getCustomer().getCustomerId();
					request.setAttribute("purchaseId", purchaseId);
					request.setAttribute("employeeId", employeeId);
					request.setAttribute("customerId", customerId);
				}
			}

			List<Purchase> purchases = facade.findPurchasesWithProductInfo();
			request.setAttribute("purchases", purchases);
			RequestDispatcher dispatcher = request.getRequestDispatcher("purchase.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			String errorMessage = "Something went wrong...";
			request.setAttribute("error", errorMessage);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");

			if ("add-purchase".equals(action)) {
				String employeeId = request.getParameter("employee-id");
				String customerId = request.getParameter("customer-id");
				int empId = 0;
				int custId = 0;

				if (customerId != null && employeeId != null) {
					empId = Integer.parseInt(employeeId);
					custId = Integer.parseInt(customerId);

					Purchase purchase = new Purchase();

					Employee employee = facade.findEmployeeById(empId);
					Customer customer = facade.findCustomerById(custId);

					purchase.setEmployee(employee);
					purchase.setCustomer(customer);

					facade.createPurchase(purchase);
					response.sendRedirect("PurchaseServlet");
				}
			}

			// Update
			if ("update-purchase".equals(action)) {
				String id = request.getParameter("purchase-id");
				int purchaseId = 0;
				purchaseId = Integer.parseInt(id);
				Purchase purchase = facade.findPurchaseById(purchaseId);
				if (purchase != null) {
					String employeeId = request.getParameter("employee-id");
					String customerId = request.getParameter("customer-id");

					int empId = Integer.parseInt(employeeId);
					int custId = Integer.parseInt(customerId);

					Employee employee = facade.findEmployeeById(empId);
					Customer customer = facade.findCustomerById(custId);

					purchase.setEmployee(employee);
					purchase.setCustomer(customer);

					facade.updatePurchase(purchase);
				}

			}
			doGet(request, response);
		} catch (Exception e) {
			String errorMessage = "Something went wrong...";
			request.setAttribute("error", errorMessage);
		}
	}
}
