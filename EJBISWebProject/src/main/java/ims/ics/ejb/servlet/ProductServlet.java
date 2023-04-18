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

import ims.ics.ejb.Product;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FacadeLocal facade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> products = facade.findAllProducts();
		request.setAttribute("products", products);

		RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		// ADD PRODUCT
		if ("add-product".equals(action)) {
			String id = request.getParameter("product-id");
			String name = request.getParameter("product-name");
			String price = request.getParameter("product-price");
			int productId = 0;
			float productPrice = 0;

			if (id != null) {
				productId = Integer.parseInt(id);
			}
			if (price != null) {
				productPrice = Float.parseFloat(price);
			}
			Product product = new Product();
			product.setProductId(productId);
			product.setProductName(name);
			product.setPrice(productPrice);
			facade.createProduct(product);
			response.sendRedirect("ProductServlet");

			// UPDATE PRODUCT
		} else if ("update-product".equals(action)) {
			String id = request.getParameter("product-id");
			int productId = Integer.parseInt(id);

			Product product = facade.findProductByID(productId);
			if (product != null) {
				if (request.getParameter("product-name") != null) {
					product.setProductName(request.getParameter("product-name"));
				}
				if (request.getParameter("product-price") != null) {
					product.setPrice(Float.parseFloat(request.getParameter("product-price")));
				}
			}
			facade.updateProduct(product);
			response.sendRedirect("ProductServlet");
		} else {

			doGet(request, response);
		}
	}
}
