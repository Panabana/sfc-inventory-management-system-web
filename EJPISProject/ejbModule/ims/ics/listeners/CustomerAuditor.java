package ims.ics.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ims.ics.ejb.Customer;

public class CustomerAuditor {

	@PrePersist
	public void logCreateCustomer(Customer c) {
		System.out.println("@PrePersist id: " + c.getCustomerId());
		System.out.println("From CustomerAuditor - " + c.getName() + " - " + c.getAddress() + " - " + c.getPhoneNbr());
	}
	
	@PreUpdate
	public void logPreUpdateCustomer(Customer c) {
		System.out.println("@PreUpdate id: " + c.getCustomerId());
		System.out.println("From CustomerAuditor - " + c.getName() + " - " + c.getAddress() + " - " + c.getPhoneNbr());
	}
}
