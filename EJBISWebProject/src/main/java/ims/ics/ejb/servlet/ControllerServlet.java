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
import ims.ics.ejb.Purchase;
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
		case "employee":
			page= "employee.jsp";
			List<Employee> employees = facade.findAllEmployees();
			request.setAttribute("employees", employees);
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
			List<Employee> employeeList = facade.findAllEmployees();
			request.setAttribute("employees", employeeList);
			List<Customer> customerList = facade.findAllCustomers();
			request.setAttribute("customers", customerList);
			break;
		case "home":
			page = "home.jsp";
			int countEmployees = facade.countAllEmployees();
			request.setAttribute("countEmployees", countEmployees);
			int countCustomers = facade.countAllCustomers();
			request.setAttribute("countCustomers", countCustomers);
			int countPurchases = facade.countAllPPurchases();
			request.setAttribute("countPurchases", countPurchases);
			break;
		default:
			page = "home.jsp";
		}

		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
