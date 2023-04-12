package ims.ics.eao;

import javax.ejb.Local;

import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;

@Local
public interface PurchaseEAOLocal {

	public Purchase findPurchaseById(int purchaseId);
	public Purchase createPurchase(Purchase purchase);
	public void updatePurchase(Purchase purchase);
	public void deletePurchase(int purchaseId);
}
