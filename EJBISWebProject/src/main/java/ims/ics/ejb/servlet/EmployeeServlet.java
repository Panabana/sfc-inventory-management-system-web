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

import ims.ics.ejb.Employee;
import ims.ics.facade.FacadeLocal;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FacadeLocal facade;

	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    String action = request.getParameter("action");
		    if ("find-employee".equals(action)) {
		        String id = request.getParameter("find-employee-id");
		        int employeeId = 0;
		        if (id != null) {
		            employeeId = Integer.parseInt(id);
		            Employee emp = facade.findEmployeeById(employeeId);
		            if (emp != null) {
		            	employeeId = emp.getEmployeeId();
		                String employeeName = emp.getName();
		                String employeeAddress = emp.getAddress();
		                int employeePhoneNbr = emp.getPhoneNumber();
		                request.setAttribute("employeeId", employeeId);
		                request.setAttribute("employeeName", employeeName);
		                request.setAttribute("employeeAddress", employeeAddress);
		                request.setAttribute("employeePhoneNumber", employeePhoneNbr);
		                
		            }
		        }
		    }

		    List<Employee> employees = facade.findAllEmployees();
		    request.setAttribute("employees", employees);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("employee.jsp");
		    dispatcher.forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");

		// ADD EMPLOYEE
		if ("add-employee".equals(action)) {

			String id = request.getParameter("employee-id");
			String name = request.getParameter("employee-name");
			String address = request.getParameter("employee-address");
			String phoneNumber = request.getParameter("employee-phone");
			int employeeId = 0;
			int employeePhoneNbr = 0;

			if (id != null) {
				employeeId = Integer.parseInt(id);
			}

			if (phoneNumber != null) {
				employeePhoneNbr = Integer.parseInt(phoneNumber);
			}

			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setName(name);
			employee.setAddress(address);
			employee.setPhoneNumber(employeePhoneNbr);
			facade.createEmployee(employee);
			response.sendRedirect("EmployeeServlet");

			// UPDATE EMPLOYEE
		} else if ("update-employee".equals(action)) {

			String id = request.getParameter("employee-id");
			int empId = Integer.parseInt(id);

			Employee employee = facade.findEmployeeById(empId);
			if (employee != null) {
				if (request.getParameter("employee-name") != null) {
					employee.setName(request.getParameter("employee-name"));
				}
				if (request.getParameter("employee-address") != null) {
					employee.setAddress(request.getParameter("employee-address"));
				}
				if (request.getParameter("employee-phone") != null) {
					employee.setPhoneNumber(Integer.parseInt(request.getParameter("employee-phone")));
				}

				facade.updateEmployee(employee);
				response.sendRedirect("EmployeeServlet");
			} else {

				doGet(request, response);
			}
		}
	}

}
