package ims.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ims.ics.ejb.Supplier;
import ims.ics.ejb.Supplier;

/**
 * Session Bean implementation class SupplierEAOImpl
 */
@Stateless
public class SupplierEAOImpl implements SupplierEAOLocal {

	@PersistenceContext(unitName="LabEJBSql")
	private EntityManager em;
	
	public SupplierEAOImpl() {
    }
    
    public List<Supplier> findAllSuppliers() {
    	return em.createQuery("SELECT s FROM Supplier s", Supplier.class).getResultList();
    }
    
    public Supplier findSupplierById(int supplierId) {
    	return em.find(Supplier.class, supplierId);
    }
    
    public Supplier createSupplier(Supplier supplier) {
    	em.persist(supplier);
    	return supplier;
    }
    
    public void updateSupplier(Supplier supplier) {
    	Supplier existingSup = em.find(Supplier.class, supplier.getSupplierId());
    	if(existingSup != null) {
    		existingSup.setSupplierName(supplier.getSupplierName());
    		existingSup.setSupplierAddress(supplier.getSupplierAddress());
    		existingSup.setSupplierId(supplier.getSupplierId());
    		existingSup.setPhoneNumber(supplier.getPhoneNumber());
    		em.merge(existingSup);
    	}
    }
    
    public void deleteSupplier(int supplierId) {
    	Supplier supplier = em.find(Supplier.class, supplierId);
    	if(supplier != null) {
    		em.remove(supplier);
    	}
    }
	
	
    

}
