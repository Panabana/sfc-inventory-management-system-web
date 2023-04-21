package ims.ics.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ims.ics.ejb.Purchase;

public class PurchaseAuditor {

	@PrePersist
	public void logCreatePurchase(Purchase p) {
		System.out.println("@PrePersist id: " + p.getPurchaseId());
		System.out.println("From PurchaseAuditor - " + p.getCustomer() + " - " + p.getEmployee());
	}
	
	@PreUpdate
	public void logPreUpdatePurchase(Purchase p) {
		System.out.println("@PrePersist id: " + p.getPurchaseId());
		System.out.println("From PurchaseAuditor - " + p.getCustomer() + " - " + p.getEmployee());
	}
}
