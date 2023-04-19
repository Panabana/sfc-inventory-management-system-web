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
	
	private Employee employee;

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
		
		employee = new Employee(expectedId, expectedName, expectedAddress, expectedPhoneNumber, expectedPurchases);
	}

	@AfterEach
	void tearDown() throws Exception {
		employee = null;
	}

	@Test
	final void testGetEmployeeId() {
		assertNotNull(employee.getEmployeeId());
		assertEquals(expectedId, employee.getEmployeeId());
	}

	@Test
	final void testSetEmployeeId() {
		int newExpectedId = 10;
		employee.setEmployeeId(newExpectedId);
		assertEquals(newExpectedId, employee.getEmployeeId());
	}

	@Test
	final void testGetName() {
		assertNotNull(employee.getName());
		assertEquals(expectedName, employee.getName());
	}

	@Test
	final void testSetName() {
		String newExpectedName = "Lorem Ipsum";
		employee.setName(newExpectedName);
		assertEquals(newExpectedName, employee.getName());
	}
	
	@Test
	final void testGetAddress() {
		assertNotNull(employee.getAddress());
		assertEquals(expectedAddress, employee.getAddress());
	}

	@Test
	final void testSetAddress() {
		String newExpectedAddress = "Bikini bottom";
		employee.setAddress(newExpectedAddress);
		assertEquals(newExpectedAddress, employee.getAddress());
	}

	@Test
	final void testGetPhoneNumber() {
		assertNotNull(employee.getPhoneNumber());
		assertEquals(expectedPhoneNumber, employee.getPhoneNumber());
	}

	@Test
	final void testSetPhoneNumber() {
		int newExpectedPhoneNumber = 47474747;
		employee.setPhoneNumber(newExpectedPhoneNumber);
		assertEquals(newExpectedPhoneNumber, employee.getPhoneNumber());
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
