package ims.ics.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;

/**
 * Session Bean implementation class CustomerEAOImpl
 */
@Stateless
public class CustomerEAOImpl implements CustomerEAOLocal {

	@PersistenceContext(unitName = "LabEJBSql")
	private EntityManager em;
   
    public CustomerEAOImpl() {}

    public Customer findCustomerById(int customerId) {
    	return em.find(Customer.class, customerId);
    }
    
    public Customer createCustomer(Customer customer) {
    	em.persist(customer);
    	return customer;
    }
    
    public void updateCustomer(Customer customer) {
    	Customer existingCus = em.find(Customer.class, customer.getCustomerId());
    	if(existingCus != null) {
    		existingCus.setName(customer.getName());
    		existingCus.setAddress(customer.getAddress());
    		existingCus.setPhoneNbr(customer.getPhoneNbr());
    		em.merge(existingCus);
    	}
    }
    
    public void deleteCustomer(int customerId) {
    	Customer customer =em.find(Customer.class, customerId);
    	if(customer != null) {
    		em.remove(customer);
    	}
    }
}

