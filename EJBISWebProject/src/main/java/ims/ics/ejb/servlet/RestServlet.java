package ims.ics.ejb.servlet;

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
			System.out.println("Employee Rest: "+jsonArray);
			out.print(jsonArray);
			} else {
			out.print("[]");
			}
			out.flush();
			} 
}
