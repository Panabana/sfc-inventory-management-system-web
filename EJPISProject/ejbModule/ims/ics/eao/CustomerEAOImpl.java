package ims.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;

/**
 * Session Bean implementation class CustomerEAOImpl
 */
@Stateless
public class CustomerEAOImpl implements CustomerEAOLocal {

	@PersistenceContext(unitName = "LabEJBSql")
	private EntityManager em;

	public CustomerEAOImpl() {
	}

	public List<Customer> findAllCustomers() {
		return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	}

	public Customer findCustomerById(int customerId) {
		return em.find(Customer.class, customerId);
	}

	public Customer createCustomer(Customer customer) {
		em.persist(customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer) {
		Customer existingCus = em.find(Customer.class, customer.getCustomerId());
		if (existingCus != null) {
			existingCus.setName(customer.getName());
			existingCus.setAddress(customer.getAddress());
			existingCus.setPhoneNbr(customer.getPhoneNbr());
			em.merge(existingCus);
			return existingCus;
		}
		return existingCus;
	}

	public void deleteCustomer(int customerId) {
		Customer customer = em.find(Customer.class, customerId);
		if (customer != null) {
			em.remove(customer);
		}
	}

	public int countAllCustomers() {
		Query query = em.createQuery("SELECT COUNT(c) FROM Customer c");
		return ((Long) query.getSingleResult()).intValue();
	}
}
