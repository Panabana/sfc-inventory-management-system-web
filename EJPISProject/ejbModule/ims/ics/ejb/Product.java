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
	
	private int productId;
	private String productName;
	private float price;
	
	@ManyToMany
	@JoinTable(
		name = "ProductSupplier",
		joinColumns = @JoinColumn(name="ProductId"),
		inverseJoinColumns = @JoinColumn(name="SupplierID"))
	Set<Supplier> ProductSupplier;
	
	@Id
	@Column(name="ProductId")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Column(name="ProductName")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="Price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
