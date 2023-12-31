package ims.ics.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import ims.ics.listeners.EmployeeAuditor;

@Entity
@EntityListeners(EmployeeAuditor.class)
@Table(name="Employee")
public class Employee implements Serializable {
	
	
	private int employeeId;
	private String name;
	private String address;
	private int phoneNumber;
	private Set<Purchase> purchases;
	
	public Employee(int employeeId, String name, String address, int phoneNumber, Set<Purchase> purchases) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.purchases = purchases;
	}
	
	public Employee() {
	}

	@Id
	@Column(name = "EmployeeID")
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "EmployeeName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EmployeeAddress")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PhoneNumber")
	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
//	@PostLoad
//	public void logOperationEmployee() {
//		System.out.println("@PostLoad on id: " + this.getEmployeeId());
//		System.out.println("@PostLoad: " + this.getName() + " - " + this.getAddress() + " - " + this.getPhoneNumber());
//	}

}
