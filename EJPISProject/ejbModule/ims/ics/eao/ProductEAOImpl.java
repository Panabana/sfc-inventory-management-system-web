package ims.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Product;

/**
 * Session Bean implementation class ProductEAOImpl
 */
@Stateless
public class ProductEAOImpl implements ProductEAOLocal {
	
	@PersistenceContext(unitName="LabEJBSql")
	private EntityManager em;
    
    public ProductEAOImpl() {
    }
    
    public List<Product> findAllProducts() {
    	return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
    
    public Product findProductById(int productId) {
    	return em.find(Product.class, productId);
    }
    
    public Product createProduct(Product product) {
    	em.persist(product);
    	return product;
    }
    
    public void updateProduct(Product product) {
    	Product existingProd = em.find(Product.class, product.getProductName());
    	if(existingProd != null) {
    		existingProd.setProductName(product.getProductName());
    		existingProd.setPrice(product.getPrice());
    		existingProd.setProductId(product.getProductId());
    		em.merge(existingProd);
    	}
    }
    
    public void deleteProduct(int productId) {
    	Product product =em.find(Product.class, productId);
    	if(product != null) {
    		em.remove(product);
    	}
    }

}
