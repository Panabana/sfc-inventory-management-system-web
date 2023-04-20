package ims.ics.eao;

import java.util.List;

import javax.ejb.Local;

import ims.ics.ejb.Employee;

@Local
public interface EmployeeEAOLocal {

	public Employee findEmployeeById(int employeeId);
	public Employee createEmployee(Employee employee);
	public  Employee updateEmployee(Employee employee);
	public void deleteEmployee(int employeeId);
	public List<Employee> findAllEmployees();
	public int countAllEmployees();
        
}
