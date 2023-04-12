package ims.ics.ejb;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product implements Serializable {
	
	private int ProductId;
	private String ProductName;
	private float Price;
	
	@ManyToMany
	@JoinTable(
		name = "ProductSupplier",
		joinColumns = @JoinColumn(name="ProductId"),
		inverseJoinColumns = @JoinColumn(name="SupplierID"))
	Set<Supplier> ProductSupplier;
	
	@Id
	@Column(name="ProductId")
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	
	@Column(name="ProductName")
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	
	@Column(name="Price")
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	
	

}
