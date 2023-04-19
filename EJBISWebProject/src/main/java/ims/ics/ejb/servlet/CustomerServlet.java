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
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FacadeLocal facade;

	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
	    if ("find-customer".equals(action)) {
	        String id = request.getParameter("find-customer-id");
	        int customerId = 0;
	        if (id != null) {
	            customerId = Integer.parseInt(id);
	            Customer customer = facade.findCustomerById(customerId);
	            if (customer != null) {
	            	customerId = customer.getCustomerId();
	                String customerName = customer.getName();
	                String customerAddress = customer.getAddress();
	                int customerPhoneNbr = customer.getPhoneNbr();
	                request.setAttribute("customerId", customerId);
	                request.setAttribute("customerName", customerName);
	                request.setAttribute("customerAddress", customerAddress);
	                request.setAttribute("customerPhoneNumber", customerPhoneNbr);
	                
	            }
	        }
	    }
		List<Customer> customers = facade.findAllCustomers();
		request.setAttribute("customers", customers);

		RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");
		dispatcher.forward(request, response);
	}

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
			response.sendRedirect("CustomerServlet");
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
			response.sendRedirect("CustomerServlet");

		} else {

			doGet(request, response);
		}
	}

}
