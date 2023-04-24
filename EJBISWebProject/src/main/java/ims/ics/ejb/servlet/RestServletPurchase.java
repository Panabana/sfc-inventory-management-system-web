package ims.ics.ejb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ims.ics.eao.PurchaseEAOLocal;
import ims.ics.ejb.Purchase;

/**
 * Servlet implementation class RestServletPurchase
 */
@WebServlet("/RestServletPurchase/*")
public class RestServletPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private PurchaseEAOLocal purchaseEAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestServletPurchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			List<Purchase> allPurchases = purchaseEAO.findAllPurchases();
			sendAsJson(response, allPurchases);
		} else {
			String[] splits = pathInfo.split("/");
			if (splits.length != 2) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			String id = splits[1];
			Purchase purchase = purchaseEAO.findPurchaseById(Integer.parseInt(id));
			sendAsJson(response, purchase);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		Purchase purchase = parseJsonPurchase(reader);
		purchase = purchaseEAO.createPurchase(purchase);
		sendAsJson(response, purchase);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String id = splits[1];
        BufferedReader reader = request.getReader();
        Purchase purchase = parseJsonPurchaseUpdate(reader);
        purchase.setPurchaseId(Integer.parseInt(id));
        purchaseEAO.updatePurchase(purchase);
        sendAsJson(response, purchase);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
