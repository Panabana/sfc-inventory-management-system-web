package ims.ics.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ims.ics.eao.CustomerEAOLocal;
import ims.ics.eao.EmployeeEAOLocal;
import ims.ics.eao.ProductEAOLocal;
import ims.ics.eao.PurchaseEAOLocal;
import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Product;
import ims.ics.ejb.Purchase;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeLocal {

	@EJB
	EmployeeEAOLocal employee;
	@EJB
	CustomerEAOLocal customer;
	@EJB
	PurchaseEAOLocal purchase;
	@EJB
	ProductEAOLocal product;
    
    public Facade() {}
    
    //EMPLOYEE METHODS
    public List<Employee> findAllEmployees() {
        return employee.findAllEmployees();
    }

    public Employee findEmployeeById(int id) {
    	return employee.findEmployeeById(id);
    }
    public Employee createEmployee(Employee employee) {
    	employee = this.employee.createEmployee(employee);
    	return employee;
    }
    public void updateEmployee(Employee employee) {
    	this.employee.updateEmployee(employee);
    }
    public void deleteEmployee(int employeeId) {
    	employee.deleteEmployee(employeeId);
    }
    //CUSTOMER METHODS
    public List<Customer> findAllCustomers() {
        return customer.findAllCustomers();
    }

    public Customer findCustomerById(int id) {
    	return customer.findCustomerById(id);
    }
    public Customer createCustomer(Customer customer) {
    	customer = this.customer.createCustomer(customer);
    	return customer;
    }
    public void updateCustomer(Customer customer) {
    	this.customer.updateCustomer(customer);
    }
    public void deleteCustomer(int customerId) {
    	customer.deleteCustomer(customerId);
    }
    //PURCHASE METHODS
    public List<Purchase> findAllPurchases() {
        return purchase.findAllPurchases();
    }
    public Purchase findPurchaseById(int purchaseId) {
    	return purchase.findPurchaseById(purchaseId);
    }
    public Purchase createPurchase(Purchase purchase) {
    	purchase = this.purchase.createPurchase(purchase);
    	return purchase;
    }
    public void updatePurchase(Purchase purchase) {
    	this.purchase.updatePurchase(purchase);
    }
    public void deletePurchase(int purchaseId) {
    	purchase.deletePurchase(purchaseId);
    }
    //PRODUCT METHODS
    public List<Product> findAllProducts() {
    	return product.findAllProducts();
    }
    public Product findProductByID(int productId) {
    	return product.findProductById(productId);
    }
    public Product createProduct(Product product) {
    	product = this.product.createProduct(product);
    	return product;
    }
    public void updateProduct(Product product) {
    	this.product.updateProduct(product);
    }
    public void deleteProduct(int productId) {
    	product.deleteProduct(productId);
    }
    

}
