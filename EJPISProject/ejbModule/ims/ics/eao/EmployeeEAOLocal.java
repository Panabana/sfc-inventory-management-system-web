package ims.ics.eao;

import javax.ejb.Local;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;

@Local
public interface EmployeeEAOLocal {

	public Employee findEmployeeById(int employeeId);
	public Employee createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(int employeeId);
}