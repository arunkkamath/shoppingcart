package com.shop.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CartTest {
	
	Cart cut; 

	@BeforeEach
	void setUp() throws Exception {
		cut = new Cart();
	}

	@Test
	void addNullProductToCart() {
		Executable closureContainingCodeToTest = () ->{ cut.add(null, 1);};
		IllegalArgumentException iae = Assertions.assertThrows(IllegalArgumentException.class, closureContainingCodeToTest);
		assertEquals("Product cannot be null while adding to cart",iae.getMessage());
	}
	
	@Test
	void addProductWithEmptyNameToCart() {
		Product empty = new Product(1,"",0);
		Executable closureContainingCodeToTest = () ->{ cut.add(empty, 1);};
		IllegalArgumentException iae = Assertions.assertThrows(IllegalArgumentException.class, closureContainingCodeToTest);
		assertEquals("Product's name cannot be null/empty while adding to cart",iae.getMessage());
	}
	
	@Test
	void addProductWithQuantityLessThan1ToCart() {
		Product dummy = new Product(1,"dummy",0);
		Executable closureContainingCodeToTest = () ->{ cut.add(dummy, 0);};
		IllegalArgumentException iae = Assertions.assertThrows(IllegalArgumentException.class, closureContainingCodeToTest);
		assertEquals("Product's quantity cannot be less than 1",iae.getMessage());
	}
	
	@Test
	void addProduct1ToCart() {
		Product p1 = new Product(1,"p1",1);
		cut.add(p1, 1);
		assertTrue(cut.getContainer().get(p1) == 1);
	}
	
	@Test
	void addProduct1MultipleTimesToCart() {
		Product p1 = new Product(1,"p1",1);
		cut.add(p1, 1);
		cut.add(p1, 5);
		assertTrue(cut.getContainer().get(p1) == 6);
	}
	
	@Test
	void getCartValueWhenNothingIsAdded() {
		Product p1 = new Product(1,"p1",1);
		assertEquals(cut.getValue(), 0.0);
	}
	
	@Test
	void getCartValueWhenProductIsAdded() {
		Product p1 = new Product(1,"p1",1);
		cut.add(p1, 1);
		double actual = cut.getValue();
		assertEquals(1.0, actual);
	}
	
	@Test
	void testRound() {
		double actual = cut.round(12.34012344);
		assertEquals(12.34, actual);
		actual = cut.round(12.349089);
		assertEquals(12.35, actual);
	}

}
