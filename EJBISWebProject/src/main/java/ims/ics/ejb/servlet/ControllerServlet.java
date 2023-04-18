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
			response.sendRedirect("CustomerServlet");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
