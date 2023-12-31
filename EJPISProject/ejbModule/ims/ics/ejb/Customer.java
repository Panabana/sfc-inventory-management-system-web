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

import ims.ics.listeners.CustomerAuditor;

@Entity
@EntityListeners(CustomerAuditor.class)
@Table(name="Customer")
public class Customer implements Serializable {
	
	private int customerId;
	private String name;
	private String address;
	private int phoneNbr;
	private Set<Purchase> purchases;
	
	public Customer(int customerId, String name, String address, int phoneNbr, Set<Purchase> purchases) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.phoneNbr = phoneNbr;
		this.purchases = purchases;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name="CustomerID")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Column(name="CustomerName")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="CustomerAddress")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="PhoneNumber")
	public int getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(int phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
	@OneToMany(mappedBy = "customer", fetch=FetchType.EAGER,  cascade = CascadeType.MERGE)
	public Set<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
//	@PostLoad
//	public void logOperationCustomer() {
//		System.out.println("@PostLoad on id: " + this.getCustomerId());
//		System.out.println("@PostLoad: " + this.getName() + " - " + this.getAddress() + " - " + this.getPhoneNbr());
//	}

}
