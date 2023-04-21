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

import ims.ics.ejb.Employee;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/RestServlet/*")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FacadeLocal facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
			//System.out.println("Alla");
			//System.out.println(pathInfo);
			
			List<ims.ics.ejb.Employee>allEmployees = facade.findAllEmployees();
			sendAsJson(response, allEmployees);
			
			return;
			}
		
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
		//System.out.println("Alla2");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
		}
		String id = splits[1];
		Employee emp = facade.findEmployeeById(Integer.parseInt(id));
		sendAsJson(response, emp);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
		BufferedReader reader = request.getReader();//Läs data Json
		Employee emp = parseJsonEmployee(reader);
		try {
		emp = facade.createEmployee(emp);
		}catch(Exception e) {
		System.out.println("duplicate key");
		}
		sendAsJson(response, emp);
		}
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		    
		    String id = splits[1];
		    BufferedReader reader = request.getReader();
		    Employee emp = parseJsonEmployeeUpdate(reader);
		    Employee updatedEmp = facade.updateEmployee(emp);

		    if (updatedEmp != null) {
		      sendAsJson(response, updatedEmp);
		    } else {
		      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    };
		    
	}
	/**§
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
		}
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
		}
		String id = splits[1];
		Employee emp = facade.findEmployeeById(Integer.parseInt(id));
		if (emp != null) {
		facade.deleteEmployee(Integer.parseInt(id));
		}
		sendAsJson(response, emp);
		
	}
	private void sendAsJson(HttpServletResponse response, Employee emp) throws IOException {
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    JsonBuilderFactory factory = Json.createBuilderFactory(null);
	    JsonObjectBuilder jsonBuilder = factory.createObjectBuilder();

	    if (emp != null) {
	        jsonBuilder.add("EmployeeName", emp.getName());
	        jsonBuilder.add("EmployeeId", emp.getEmployeeId());
	        jsonBuilder.add("EmployeeAddress", emp.getAddress());
	        jsonBuilder.add("Phone", emp.getPhoneNumber());
	    } else {
	        // If emp is null, add empty values to the JSON object
	        jsonBuilder.add("EmployeeName", "");
	        jsonBuilder.add("EmployeeId", "");
	        jsonBuilder.add("EmployeeAddress", "");
	        jsonBuilder.add("Phone", "");
	    }

	    JsonObject jsonObject = jsonBuilder.build();
	    out.print(jsonObject.toString());
	    out.flush();
	}

	
	private void sendAsJson(HttpServletResponse response, List<Employee> employees)
			throws IOException {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			if (employees != null) {
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (ims.ics.ejb.Employee emp : employees) {
			JsonObjectBuilder o = Json.createObjectBuilder();
			o.add("EmployeeName", emp.getName());
			o.add("EmployeeId", String.valueOf(emp.getEmployeeId()));
			o.add("EmployeeAddress", emp.getAddress());
			o.add("Phone", String.valueOf(emp.getPhoneNumber()));
			array.add(o);
			}
			JsonArray jsonArray = array.build();
			//System.out.println("Employee Rest: "+jsonArray);
			out.print(jsonArray);
			} else {
			out.print("[]");
			}
			out.flush();
			} 
	
	private Employee parseJsonEmployee(BufferedReader br) {
		//javax.json-1.0.4.jar
		JsonReader jsonReader = null;
		JsonObject jsonRoot = null;
		jsonReader = Json.createReader(br);
		jsonRoot = jsonReader.readObject();
		
		//System.out.println("JsonRoot: "+jsonRoot);
		Employee emp = new Employee();
		
		/*
		 * JsonValue empIdJson = jsonRoot.get("EmployeeId"); if (empIdJson != null &&
		 * empIdJson.getValueType() == ValueType.STRING) {
		 * emp.setEmployeeId(Integer.parseInt(((JsonString) empIdJson).getString())); }
		 */
	    
	    JsonValue empNameJson = jsonRoot.get("EmployeeName");
	    if (empNameJson != null && empNameJson.getValueType() == ValueType.STRING) {
	        emp.setName(((JsonString) empNameJson).getString());
	    }
	    
	    JsonValue empAddressJson = jsonRoot.get("EmployeeAddress");
	    if (empAddressJson != null && empAddressJson.getValueType() == ValueType.STRING) {
	        emp.setAddress(((JsonString) empAddressJson).getString());
	    }
	    
	    JsonValue empPhoneJson = jsonRoot.get("Phone");
	    if (empPhoneJson != null && empPhoneJson.getValueType() == ValueType.STRING) {
	        emp.setPhoneNumber(Integer.parseInt(((JsonString) empPhoneJson).getString()));
	    }
	    
		return emp;
		}
	
	
	private Employee parseJsonEmployeeUpdate(BufferedReader br) {
		//javax.json-1.0.4.jar
		JsonReader jsonReader = null;
		JsonObject jsonRoot = null;
		jsonReader = Json.createReader(br);
		jsonRoot = jsonReader.readObject();
		
		//System.out.println("JsonRoot: "+jsonRoot);
		Employee emp = new Employee();
		
		
		JsonValue empIdJson = jsonRoot.get("EmployeeId");
	    if (empIdJson != null && empIdJson.getValueType() == ValueType.STRING) {
	        emp.setEmployeeId(Integer.parseInt(((JsonString) empIdJson).getString()));
	    }
	    
	    
	    JsonValue empNameJson = jsonRoot.get("EmployeeName");
	    if (empNameJson != null && empNameJson.getValueType() == ValueType.STRING) {
	        emp.setName(((JsonString) empNameJson).getString());
	    }
	    
	    JsonValue empAddressJson = jsonRoot.get("EmployeeAddress");
	    if (empAddressJson != null && empAddressJson.getValueType() == ValueType.STRING) {
	        emp.setAddress(((JsonString) empAddressJson).getString());
	    }
	    
	    JsonValue empPhoneJson = jsonRoot.get("Phone");
	    if (empPhoneJson != null && empPhoneJson.getValueType() == ValueType.STRING) {
	        emp.setPhoneNumber(Integer.parseInt(((JsonString) empPhoneJson).getString()));
	    }
	    
		return emp;
		}
	
	
	
}
