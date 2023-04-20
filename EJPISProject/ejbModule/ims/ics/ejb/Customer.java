package ims.ics.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToMany(mappedBy = "customer", fetch=FetchType.EAGER)
	public Set<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	
	

}
