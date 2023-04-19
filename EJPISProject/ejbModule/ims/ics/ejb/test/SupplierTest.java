package ims.ics.ejb.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ims.ics.ejb.Supplier;

class SupplierTest {
	private int expectedId;
	private String expectedName;
	private String expectedAddress;
	private int expectedPhoneNumber;
	
	private Supplier supplier;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		expectedId = 1;
		expectedName = "Mats";
		expectedAddress = "Lund";
		expectedPhoneNumber = 12345;
		
		supplier = new Supplier(expectedId, expectedName, expectedAddress, expectedPhoneNumber);
	}

	@AfterEach
	void tearDown() throws Exception {
		supplier = null;
	}

	@Test
	final void testGetSupplierId() {
		assertNotNull(supplier.getSupplierId());
		assertEquals(expectedId, supplier.getSupplierId());
	}

	@Test
	final void testSetSupplierId() {
		int newExpectedId = 10;
		supplier.setSupplierId(newExpectedId);
		assertEquals(newExpectedId, supplier.getSupplierId());
	}

	@Test
	final void testGetSupplierName() {
		assertNotNull(supplier.getSupplierName());
		assertEquals(expectedName, supplier.getSupplierName());
	}

	@Test
	final void testSetSupplierName() {
		String newExpectedName = "Lorem Ipsum";
		supplier.setSupplierName(newExpectedName);
		assertEquals(newExpectedName, supplier.getSupplierName());
	}

	@Test
	final void testGetSupplierAddress() {
		assertNotNull(supplier.getSupplierAddress());
		assertEquals(expectedAddress, supplier.getSupplierAddress());
	}

	@Test
	final void testSetSupplierAddress() {
		String newExpectedAddress = "Bikini bottom";
		supplier.setSupplierAddress(newExpectedAddress);
		assertEquals(newExpectedAddress, supplier.getSupplierAddress());
	}

	@Test
	final void testGetPhoneNumber() {
		assertNotNull(supplier.getPhoneNumber());
		assertEquals(expectedPhoneNumber, supplier.getPhoneNumber());
	}

	@Test
	final void testSetPhoneNumber() {
		int newExpectedPhoneNumber = 47474747;
		supplier.setPhoneNumber(newExpectedPhoneNumber);
		assertEquals(newExpectedPhoneNumber, supplier.getPhoneNumber());
	}

}
