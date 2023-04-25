package ims.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;

/**
 * Session Bean implementation class PurchaseEAOImpl
 */
@Stateless
public class PurchaseEAOImpl implements PurchaseEAOLocal {

	@PersistenceContext(unitName = "LabEJBSql")
	private EntityManager em;

	public PurchaseEAOImpl() {
	}

	public List<Purchase> findAllPurchases() {
		return em.createQuery("SELECT p FROM Purchase p", Purchase.class).getResultList();
	}

	public Purchase findPurchaseById(int purchaseId) {
		return em.find(Purchase.class, purchaseId);
	}

	public Purchase createPurchase(Purchase purchase) {
		em.persist(purchase);
		return purchase;
	}

	public void updatePurchase(Purchase purchase) {
		Purchase existingPurchase = em.find(Purchase.class, purchase.getPurchaseId());
		if (existingPurchase != null) {
			existingPurchase.setEmployee(purchase.getEmployee());
			existingPurchase.setCustomer(purchase.getCustomer());
			em.merge(existingPurchase);
		}
	}

	public void deletePurchase(int purchaseId) {
		Purchase purchase = em.find(Purchase.class, purchaseId);
		if (purchase != null) {
			em.remove(purchase);
		}
	}

	public int countAllPurchases() {
		Query query = em.createQuery("SELECT COUNT(p) FROM Purchase p");
		return ((Long) query.getSingleResult()).intValue();
	}
	
	public List<Purchase> findPurchaseDetails() {
	    TypedQuery<Purchase> query = em.createQuery("SELECT p " +  
	                                "FROM Purchase p " +
	                                "JOIN p.customer c " +
	                                "JOIN p.employee e", Purchase.class);
	    return query.getResultList();
	}
	
	public String getTopCustomer() {
	    String query = "SELECT c.name, COUNT(p.purchaseId) " +
                "FROM Purchase p " +
                "JOIN p.customer c " +
                "GROUP BY c.customerId, c.name " +
                "ORDER BY COUNT(p.purchaseId) DESC";
	    Object[] result = (Object[]) em.createQuery(query)
	                                  .setMaxResults(1)
	                                  .getSingleResult();
	    return (String) result[0];
	}

	}


