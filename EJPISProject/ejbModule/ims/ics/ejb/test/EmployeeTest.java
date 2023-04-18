package ims.ics.ejb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ims.ics.ejb.Employee;
import ims.ics.ejb.Purchase;

class EmployeeTest {
	private int expectedId;
	private String expectedName;
	private String expectedAddress;
	private int expectedPhoneNumber;
	private Set<Purchase> expectedPurchases;
	
	private Employee e1;

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
		// expectedPurchases = expectedPurchases.add(Purchase p);
		
		e1 = new Employee(expectedId, expectedName, expectedAddress, expectedPhoneNumber, expectedPurchases);
	}

	@AfterEach
	void tearDown() throws Exception {
		e1 = null;
	}

	@Test
	final void testGetEmployeeId() {
		assertNotNull(e1.getEmployeeId());
		assertEquals(expectedId, e1.getEmployeeId());
	}

	@Test
	final void testSetEmployeeId() {
		int newExpectedId = 10;
		e1.setEmployeeId(newExpectedId);
		assertEquals(newExpectedId, e1.getEmployeeId());
	}

	@Test
	final void testGetName() {
		assertNotNull(e1.getName());
		assertEquals(expectedName, e1.getName());
	}

	@Test
	final void testSetName() {
		String newExpectedName = "Lorem Ipsum";
		e1.setName(newExpectedName);
		assertEquals(newExpectedName, e1.getName());
	}
	
	@Test
	final void testGetAddress() {
		assertNotNull(e1.getAddress());
		assertEquals(expectedAddress, e1.getAddress());
	}

	@Test
	final void testSetAddress() {
		String newExpectedAddress = "Bikini bottom";
		e1.setAddress(newExpectedAddress);
		assertEquals(newExpectedAddress, e1.getAddress());
	}

	@Test
	final void testGetPhoneNumber() {
		assertNotNull(e1.getPhoneNumber());
		assertEquals(expectedPhoneNumber, e1.getPhoneNumber());
	}

	@Test
	final void testSetPhoneNumber() {
		int newExpectedPhoneNumber = 47474747;
		e1.setPhoneNumber(newExpectedPhoneNumber);
		assertEquals(newExpectedPhoneNumber, e1.getPhoneNumber());
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
