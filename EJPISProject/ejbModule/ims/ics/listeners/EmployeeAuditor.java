package ims.ics.listeners;

import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ims.ics.ejb.Employee;

public class EmployeeAuditor {

	@PrePersist
	public void logCreateEmployee(Employee e) {
		System.out.println("@PrePersist id: " + e.getEmployeeId());
		System.out.println("From EmployeeAuditor - " + e.getName() + " - " + e.getAddress() + " - " + e.getPhoneNumber());
	}
	
	@PreUpdate
	public void logPreUpdateEmployee(Employee e) {
		System.out.println("@PreUpdate id: " + e.getEmployeeId());
		System.out.println("From EmployeeAuditor - " + e.getName() + " - " + e.getAddress() + " - " + e.getPhoneNumber());
	}
}
