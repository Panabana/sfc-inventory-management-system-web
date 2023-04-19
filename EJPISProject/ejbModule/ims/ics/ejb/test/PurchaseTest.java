package ims.ics.ejb.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;

class PurchaseTest {
	private int expectedId;
	private Employee expectedEmployee;
	private Customer expectedCustomer;
	
	private Purchase purchase;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		expectedId = 3;
		expectedEmployee = new Employee();
		expectedCustomer = new Customer();
		
		purchase = new Purchase(expectedId, expectedEmployee, expectedCustomer);
	}

	@AfterEach
	void tearDown() throws Exception {
		purchase = null;
	}

	@Test
	final void testGetPurchaseId() {
		assertNotNull(purchase.getPurchaseId());
		assertEquals(expectedId, purchase.getPurchaseId());
	}

	@Test
	final void testSetPurchaseId() {
		int newExpectedId = 50;
		purchase.setPurchaseId(newExpectedId);
		assertEquals(newExpectedId, purchase.getPurchaseId());
	}

	@Test
	final void testGetEmployee() {
		assertNotNull(purchase.getEmployee());
		assertEquals(expectedEmployee, purchase.getEmployee());
	}

	@Test
	final void testSetEmployee() {
		Employee newExpectedEmployee = new Employee();
		purchase.setEmployee(newExpectedEmployee);
		assertEquals(newExpectedEmployee, purchase.getEmployee());
	}

	@Test
	final void testGetCustomer() {
		assertNotNull(purchase.getCustomer());
		assertEquals(expectedCustomer, purchase.getCustomer());
	}

	@Test
	final void testSetCustomer() {
		Customer newExpectedCustomer = new Customer();
		purchase.setCustomer(newExpectedCustomer);
		assertEquals(newExpectedCustomer, purchase.getCustomer());
	}

}
