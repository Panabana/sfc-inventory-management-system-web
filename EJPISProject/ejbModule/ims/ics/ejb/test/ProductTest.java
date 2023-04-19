package ims.ics.ejb.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ims.ics.ejb.Product;

class ProductTest {
	private int expectedId;
	private String expectedName;
	private float expectedPrice;

	private Product product;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		expectedId = 5;
		expectedName = "Patrick";
		expectedPrice = 53.75f;
		
		product = new Product(expectedId, expectedName, expectedPrice);
	}

	@AfterEach
	void tearDown() throws Exception {
		product = null;
	}

	@Test
	final void testGetProductId() {
		assertNotNull(product.getProductId());
		assertEquals(expectedId, product.getProductId());
	}

	@Test
	final void testSetProductId() {
		int newExpectedId = 10;
		product.setProductId(newExpectedId);
		assertEquals(newExpectedId, product.getProductId());
	}

	@Test
	final void testGetProductName() {
		assertNotNull(product.getProductName());
		assertEquals(expectedName, product.getProductName());
	}

	@Test
	final void testSetProductName() {
		String newExpectedName = "Lorem Ipsum";
		product.setProductName(newExpectedName);
		assertEquals(newExpectedName, product.getProductName());
	}

	@Test
	final void testGetPrice() {
		assertNotNull(product.getPrice());
		assertEquals(expectedPrice, product.getPrice());
	}

	@Test
	final void testSetPrice() {
		float newExpectedPrice = 40.506f;
		product.setPrice(newExpectedPrice);
		assertEquals(newExpectedPrice, product.getPrice());
	}

}
