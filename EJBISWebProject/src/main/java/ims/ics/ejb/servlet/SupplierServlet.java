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

import ims.ics.ejb.Supplier;
import ims.ics.facade.FacadeLocal;


/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet("/SupplierServlet")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FacadeLocal facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Supplier> suppliers = facade.findAllSuppliers();
		request.setAttribute("suppliers", suppliers);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("supplier.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("add-supplier".equals(action)) {
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
			response.sendRedirect("SupplierServlet");

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
			response.sendRedirect("SupplierServlet");
		} else {
			doGet(request, response);
		}
	}

}
