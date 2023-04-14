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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
