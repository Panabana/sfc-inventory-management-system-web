package ims.ics.ejb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ims.ics.ejb.Customer;
import ims.ics.ejb.Purchase;

class CustomerTest {
	private int expectedId;
	private String expectedName;
	private String expectedAddress;
	private int expectedPhoneNumber;
	private Set<Purchase> expectedPurchases;
	
	private Customer customer;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		expectedId = 1;
		expectedName = "Rikard";
		expectedAddress = "Lejonhjärtastaden";
		expectedPhoneNumber = 12345;
		// expectedPurchases = expectedPurchases.add(Purchase p);
		
		customer = new Customer(expectedId, expectedName, expectedAddress, expectedPhoneNumber, expectedPurchases);
	}

	@AfterEach
	void tearDown() throws Exception {
		customer = null;
	}

	@Test
	final void testGetCustomerId() {
		assertNotNull(customer.getCustomerId());
		assertEquals(expectedId, customer.getCustomerId());
	}

	@Test
	final void testSetCustomerId() {
		int newExpectedId = 20;
		customer.setCustomerId(newExpectedId);
		assertEquals(newExpectedId, customer.getCustomerId());
	}

	@Test
	final void testGetName() {
		assertNotNull(customer.getName());
		assertEquals(expectedName, customer.getName());
	}

	@Test
	final void testSetName() {
		String newExpectedName = "Lorem Ipsum";
		customer.setName(newExpectedName);
		assertEquals(newExpectedName, customer.getName());
	}

	@Test
	final void testGetAddress() {
		assertNotNull(customer.getAddress());
		assertEquals(expectedAddress, customer.getAddress());
	}

	@Test
	final void testSetAddress() {
		String newExpectedAddress = "Bikini bottom";
		customer.setAddress(newExpectedAddress);
		assertEquals(newExpectedAddress, customer.getAddress());
	}

	@Test
	final void testGetPhoneNbr() {
		assertNotNull(customer.getPhoneNbr());
		assertEquals(expectedPhoneNumber, customer.getPhoneNbr());
	}

	@Test
	final void testSetPhoneNbr() {
		int newExpectedPhoneNumber = 47474747;
		customer.setPhoneNbr(newExpectedPhoneNumber);
		assertEquals(newExpectedPhoneNumber, customer.getPhoneNbr());
	}

	@Test
	final void testGetPurchases() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetPurchases() {
		fail("Not yet implemented"); // TODO
	}

}
