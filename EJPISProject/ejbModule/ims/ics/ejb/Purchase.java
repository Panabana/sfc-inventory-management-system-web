package ims.ics.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import ims.ics.listeners.PurchaseAuditor;

@Entity
@EntityListeners(PurchaseAuditor.class)
@Table(name="Purchase")
@NamedQuery(name = "Purchase.findPurchaseDetails",
query = "SELECT p.purchaseId, c.customerId, c.name AS customerName, e.employeeId, e.name AS employeeName " +
        "FROM Purchase p " +
        "JOIN p.customer c " +
        "JOIN p.employee e")
public class Purchase implements Serializable {
	
	private int purchaseId;
	private Employee employee;
	private Customer customer;
	
	public Purchase(int purchaseId, Employee employee, Customer customer) {
		super();
		this.purchaseId = purchaseId;
		this.employee = employee;
		this.customer = customer;
	}
	
	public Purchase() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PurchaseID")
	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="EmployeeID", referencedColumnName = "EmployeeID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CustomerID", referencedColumnName = "CustomerID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
//	@PostLoad
//	public void logOperationPurchase() {
//		System.out.println("@PostLoad on id: " + this.getPurchaseId());
//		System.out.println("@PostLoad: " + this.getCustomer() + " - " + this.getEmployee());
//	}

}
