package ims.ics.ejb;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Supplier")
public class Supplier implements Serializable{
	
	private int supplierId;
	private String supplierName;
	private String supplierAddress;
	private int phoneNumber;
	
	@ManyToMany(mappedBy ="ProductSupplier")
	Set<Product> SupplierProduct;
	
	@Id
	@Column(name="SupplierID")
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierID) {
		this.supplierId = supplierID;
	}
	
	@Column(name="SupplierName")
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Column(name="SupplierAddress")
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
	 this.supplierAddress = supplierAddress;
	}
	
	@Column(name="PhoneNumber")
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
