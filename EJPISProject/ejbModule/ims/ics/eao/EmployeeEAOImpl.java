package ims.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;

/**
 * Session Bean implementation class EmployeeEAOImpl
 */
@Stateless
public class EmployeeEAOImpl implements EmployeeEAOLocal {

	@PersistenceContext(unitName = "LabEJBSql")
	private EntityManager em;
	
    public EmployeeEAOImpl() {}
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Customer e", Employee.class).getResultList();
    }
    public Employee findEmployeeById(int employeeId) {
    	return em.find(Employee.class, employeeId);
    }
    
    public Employee createEmployee(Employee employee) {
    	em.persist(employee);
    	return employee;
    }
    
    public void updateEmployee(Employee employee) {
    	Employee existingEmp = em.find(Employee.class, employee.getEmployeeId());
    	if(existingEmp != null) {
    		existingEmp.setName(employee.getName());
    		existingEmp.setAddress(employee.getAddress());
    		existingEmp.setPhoneNumber(employee.getPhoneNumber());
    		em.merge(existingEmp);
    	}
    }
    
    public void deleteEmployee(int employeeId) {
    	Employee employee =em.find(Employee.class, employeeId);
    	if(employee != null) {
    		em.remove(employee);
    	}
    }

}
