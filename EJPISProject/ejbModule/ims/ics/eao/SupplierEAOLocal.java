package ims.ics.eao;

import java.util.List;

import javax.ejb.Local;

import ims.ics.ejb.Supplier;

@Local
public interface SupplierEAOLocal {
	
	public Supplier findSupplierById(int supplierId);
	public Supplier createSupplier(Supplier supplier);
	public void updateSupplier(Supplier supplier);
	public void deleteSupplier(int supplierId);
	public List<Supplier> findAllSuppliers();

}
