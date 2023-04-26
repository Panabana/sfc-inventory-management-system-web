package ims.ics.ejb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ims.ics.eao.PurchaseEAOLocal;
import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class RestServletPurchase
 */
@WebServlet("/RestServletPurchase/*")
public class RestServletPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FacadeLocal facade;

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
			List<Purchase> allPurchases = facade.findAllPurchases();
			sendAsJson(response, allPurchases);
			return;
		}

		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String id = splits[1];
		Purchase purchase = facade.findPurchaseById(Integer.parseInt(id));
		sendAsJson(response, purchase);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		// Check if the path info is valid
		if (pathInfo == null || pathInfo.equals("/")) {
			// Read the request body
			BufferedReader reader = request.getReader();
			// Parse the JSON data into a Purchase object
			Purchase purchase = parseJsonPurchase(reader);

			try {
				// Attempt to create the new purchase
				purchase = facade.createPurchase(purchase);
			} catch (Exception e) {
				// If the creation fails due to a duplicate key or other error, return an error
				// response
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating purchase.");
				return;
			}

			// Send the created purchase as a JSON response
			sendAsJson(response, purchase);
		} else {
			// If the path info is invalid, return a 400 Bad Request error
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
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
		facade.updatePurchase(purchase);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
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
		Purchase purchase = facade.findPurchaseById(Integer.parseInt(id));
		if (purchase != null) {
			facade.deletePurchase(Integer.parseInt(id));
			sendAsJson(response, purchase);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void sendAsJson(HttpServletResponse response, Purchase purchase) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObjectBuilder jsonBuilder = factory.createObjectBuilder();

		if (purchase != null) {
			jsonBuilder.add("purchaseId", purchase.getPurchaseId());
			jsonBuilder.add("employeeId", purchase.getEmployee().getEmployeeId());
			jsonBuilder.add("customerId", purchase.getCustomer().getCustomerId());
			// jsonBuilder.add("purchaseDate", purchase.getPurchaseDate().toString());
			// jsonBuilder.add("purchaseAmount", purchase.getPurchaseAmount());
		} else {
			// If purchase is null, add empty values to the JSON object
			jsonBuilder.add("purchaseId", "");
			jsonBuilder.add("employeeId", "");
			jsonBuilder.add("customerId", "");
			// jsonBuilder.add("purchaseDate", "");
			// jsonBuilder.add("purchaseAmount", "");
		}

		JsonObject jsonObject = jsonBuilder.build();
		out.print(jsonObject.toString());
		out.flush();
	}

	private void sendAsJson(HttpServletResponse response, List<Purchase> purchases) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();

		if (purchases != null && !purchases.isEmpty()) {
			for (Purchase purchase : purchases) {
				JsonObjectBuilder jsonBuilder = factory.createObjectBuilder();
				jsonBuilder.add("purchaseId", purchase.getPurchaseId());
				jsonBuilder.add("employeeId", purchase.getEmployee().getEmployeeId());
				jsonBuilder.add("customerId", purchase.getCustomer().getCustomerId());
				// jsonBuilder.add("purchaseDate", purchase.getPurchaseDate().toString());
				// jsonBuilder.add("totalAmount", purchase.getTotalAmount());
				JsonObject jsonObject = jsonBuilder.build();
				arrayBuilder.add(jsonObject);
			}
		} else {
			// If purchases is null or empty, add an empty array to the JSON object
			arrayBuilder.add(Json.createArrayBuilder().build());
		}

		JsonArray jsonArray = arrayBuilder.build();
		out.print(jsonArray.toString());
		out.flush();
	}

	private Purchase parseJsonPurchase(BufferedReader br) {
		JsonReader jsonReader = Json.createReader(br);
	    JsonObject jsonRoot = jsonReader.readObject();

	    Purchase purchase = new Purchase();

	    JsonValue employeeIdJson = jsonRoot.get("employeeId");
	    if (employeeIdJson != null && employeeIdJson.getValueType() == JsonValue.ValueType.NUMBER) {
	        int employeeId = ((JsonNumber) employeeIdJson).intValue();
	        Employee employee = facade.findEmployeeById(employeeId);
	        if (employee != null) {
	            purchase.setEmployee(employee);
	        }
	    }

	    JsonValue customerIdJson = jsonRoot.get("customerId");
	    if (customerIdJson != null && customerIdJson.getValueType() == JsonValue.ValueType.NUMBER) {
	        int customerId = ((JsonNumber) customerIdJson).intValue();
	        Customer customer = facade.findCustomerById(customerId);
	        if (customer != null) {
	            purchase.setCustomer(customer);
	        }
	    }
	    return purchase;
	}

	private Purchase parseJsonPurchaseUpdate(BufferedReader br) {
		JsonReader jsonReader = Json.createReader(br);
		JsonObject jsonRoot = jsonReader.readObject();

		Purchase purchase = new Purchase();
		purchase.setPurchaseId(Integer.parseInt(jsonRoot.getString("purchaseId")));

		JsonValue employeeJson = jsonRoot.get("employee");
		if (employeeJson != null && employeeJson.getValueType() == ValueType.OBJECT) {
			JsonObject employeeObj = (JsonObject) employeeJson;
			Employee employee = new Employee();
			employee.setEmployeeId(Integer.parseInt(employeeObj.getString("employeeId")));
			employee.setName(employeeObj.getString("employeeName"));
			employee.setAddress(employeeObj.getString("employeeAddress"));
			employee.setPhoneNumber(Integer.parseInt(employeeObj.getString("employeePhone")));
			purchase.setEmployee(employee);
		}

		JsonValue customerJson = jsonRoot.get("customer");
		if (customerJson != null && customerJson.getValueType() == ValueType.OBJECT) {
			JsonObject customerObj = (JsonObject) customerJson;
			Customer customer = new Customer();
			customer.setCustomerId(Integer.parseInt(customerObj.getString("customerId")));
			customer.setName(customerObj.getString("customerName"));
			customer.setAddress(customerObj.getString("customerAddress"));
			customer.setPhoneNbr(Integer.parseInt(customerObj.getString("customerPhone")));
			purchase.setCustomer(customer);
		}

		return purchase;
	}

}
