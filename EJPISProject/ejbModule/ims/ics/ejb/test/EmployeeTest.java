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
	
	private Employee e1;
	private Employee e2;
	private Employee e3;

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
		// DON'T forget
		// expectedPurchases = ???
		
		e1 = new Employee(expectedId, expectedName, expectedAddress, expectedPhoneNumber);
		e2 = new Employee(2, "Eva", "Malmö", 54321);
		e3 = new Employee(3, "Pak", "Pakistan", 010101);
	}

	@AfterEach
	void tearDown() throws Exception {
		e1 = null;
		e2 = null;
		e3 = null;
	}

	@Test
	final void testGetEmployeeId() {
		assertEquals(expectedId, e1.getEmployeeId());
	}

	@Test
	final void testSetEmployeeId() {
		int expectedId2 = 1;
		e1.setEmployeeId(expectedId2);
		assertEquals(expectedId2, e1.getEmployeeId());
	}

	@Test
	final void testGetName() {
		assertNotNull(e1);
		assertEquals(expectedName, e1.getName());
	}

	@Test
	final void testSetName() {
		String expectedName2 = "TestName";
		e1.setName(expectedName2);
		assertEquals(expectedName2, e1.getName());
	}

	/*
	@Test
	final void testGetAddress() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetAddress() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetPhoneNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetPhoneNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetPurchases() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetPurchases() {
		fail("Not yet implemented"); // TODO
	}
	*/
}
