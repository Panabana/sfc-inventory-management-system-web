package ims.ics.facade;

import java.util.List;

import javax.ejb.Local;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Product;
import ims.ics.ejb.Purchase;
import ims.ics.ejb.Supplier;

@Local
public interface FacadeLocal {
	
	//Employee
	public List<Employee> findAllEmployees();
	public Employee findEmployeeById(int id);
	public Employee createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(int employeeId);
	
	//Customer
	public List<Customer> findAllCustomers();
	public Customer findCustomerById(int id);
	public Customer createCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(int customerId);
	
	//Purchase
	public List<Purchase> findAllPurchases();
	public Purchase findPurchaseById(int purchaseId);
	public Purchase createPurchase(Purchase purchase);
	public void updatePurchase(Purchase purchase);
	public void deletePurchase(int purchaseId);

	//Product
	public List<Product> findAllProducts();
    public Product findProductByID(int productId);
    public Product createProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(int productId);
    
    //Supplier
    public List<Supplier>findAllSuppliers();
    public Supplier findSupplierById(int supplierId);
    public Supplier createSupplier(Supplier supplier);
    public void updateSupplier(Supplier supplier);
    public void deleteSupplier(int supplierId);
    
}
