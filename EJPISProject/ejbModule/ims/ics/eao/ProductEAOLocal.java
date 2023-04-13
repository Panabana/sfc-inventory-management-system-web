package ims.ics.eao;


import java.util.List;

import javax.ejb.Local;

import ims.ics.ejb.Product;


@Local
public interface ProductEAOLocal {
	
	public Product findProductById(int productId);
	public Product createProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(int productId);
	public List<Product> findAllProducts();
}
