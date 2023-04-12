package ims.ics.eao;

import java.util.List;

import javax.ejb.Local;

import ims.ics.ejb.Customer;

@Local
public interface CustomerEAOLocal {
	
	public Customer findCustomerById(int customerId);
	public Customer createCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(int customerId);
	public List<Customer> findAllCustomers();

}
