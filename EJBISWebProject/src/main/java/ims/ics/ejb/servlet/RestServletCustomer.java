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
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ims.ics.ejb.Customer;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class RestServletCustomer
 */
@WebServlet("/RestServletCustomer/*")
public class RestServletCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	FacadeLocal facade;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestServletCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
			List<Customer> allCustomers = facade.findAllCustomers();
			sendAsJson(response, allCustomers);
		} else {
			String[] splits = pathInfo.split("/");
			if(splits.length != 2) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			String id = splits[1];
			Customer customer = facade.findCustomerById(Integer.parseInt(id));
			sendAsJson(response, customer);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			BufferedReader reader = request.getReader();// Read JSON data
			Customer cust = parseJsonCustomer(reader);
			try {
				cust = facade.createCustomer(cust);
			} catch (Exception e) {
				System.out.println("Duplicate key");
			}
			sendAsJson(response, cust);
		}
		
		
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	    String customerId = splits[1];
	    Customer customer = facade.findCustomerById(Integer.parseInt(customerId));
	    if (customer != null) {
	        facade.deleteCustomer(Integer.parseInt(customerId));
	    }

	    sendAsJson(response, customer);
	}
	
	private void sendAsJson(HttpServletResponse response, Customer cust) throws IOException {
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    JsonBuilderFactory factory = Json.createBuilderFactory(null);
	    JsonObjectBuilder jsonBuilder = factory.createObjectBuilder();

	    if (cust != null) {
	        jsonBuilder.add("CustomerId", cust.getCustomerId());
	        jsonBuilder.add("CustomerName", cust.getName());
	        jsonBuilder.add("CustomerAddress", cust.getAddress());
	        jsonBuilder.add("Phone", cust.getPhoneNbr());
	    } else {
	        // If cust is null, add empty values to the JSON object
	        jsonBuilder.add("CustomerId", "");
	        jsonBuilder.add("CustomerName", "");
	        jsonBuilder.add("CustomerAddress", "");
	        jsonBuilder.add("Phone", "");
	    }

	    JsonObject jsonObject = jsonBuilder.build();
	    out.print(jsonObject.toString());
	    out.flush();
	}
	
	private void sendAsJson(HttpServletResponse response, List<Customer> customerList) throws IOException {
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    JsonBuilderFactory factory = Json.createBuilderFactory(null);
	    JsonArrayBuilder jsonArrayBuilder = factory.createArrayBuilder();

	    if (customerList != null && !customerList.isEmpty()) {
	        for (Customer cust : customerList) {
	            JsonObjectBuilder jsonBuilder = factory.createObjectBuilder();
	            jsonBuilder.add("CustomerId", cust.getCustomerId());
	            jsonBuilder.add("CustomerName", cust.getName());
	            jsonBuilder.add("CustomerAddress", cust.getAddress());
	            jsonBuilder.add("Phone", cust.getPhoneNbr());
	            jsonArrayBuilder.add(jsonBuilder.build());
	        }
	    } else {
	        // If customerList is null or empty, add an empty JSON array
	        jsonArrayBuilder.add(factory.createObjectBuilder().build());
	    }

	    JsonArray jsonArray = jsonArrayBuilder.build();
	    out.print(jsonArray.toString());
	    out.flush();
	}
	
	private Customer parseJsonCustomer(BufferedReader br) {
		JsonReader jsonReader = null;
		JsonObject jsonRoot = null;
		jsonReader = Json.createReader(br);
		jsonRoot = jsonReader.readObject();
		
		Customer customer = new Customer();
		
		JsonValue customerIdJson = jsonRoot.get("CustomerId");
		if (customerIdJson != null && customerIdJson.getValueType() == ValueType.STRING) {
			customer.setCustomerId(Integer.parseInt(((JsonString) customerIdJson).getString()));
		}

		JsonValue customerNameJson = jsonRoot.get("CustomerName");
		if (customerNameJson != null && customerNameJson.getValueType() == ValueType.STRING) {
			customer.setName(((JsonString) customerNameJson).getString());
		}

		JsonValue customerAddressJson = jsonRoot.get("CustomerAddress");
		if (customerAddressJson != null && customerAddressJson.getValueType() == ValueType.STRING) {
			customer.setAddress(((JsonString) customerAddressJson).getString());
		}

		JsonValue customerPhoneJson = jsonRoot.get("Phone");
		if (customerPhoneJson != null && customerPhoneJson.getValueType() == ValueType.STRING) {
			customer.setPhoneNbr(Integer.parseInt(((JsonString) customerPhoneJson).getString()));
		}

		return customer;
	}


}
