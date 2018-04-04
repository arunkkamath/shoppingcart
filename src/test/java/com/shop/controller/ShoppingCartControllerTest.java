package com.shop.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shop.controller.CartController;
import com.shop.controller.ShoppingCartController;
import com.shop.domain.Product;
import com.shop.domain.User;

class ShoppingCartControllerTest {

	CartController cut;
	User user;

	@BeforeEach
	void setUp() throws Exception {
		cut = new ShoppingCartController();
		user = new User("User1", 1);
	}

	@Test
	void userAdds5DoveSoapsToCart() {
		Product doveSoap = new Product(1,"DOVE",39.99);
		boolean result = cut.add(user, doveSoap, 5);
		assertTrue(result);
		assertTrue(user.getCart().getContainer().get(doveSoap).equals(5));
		assertEquals(199.95, user.getCart().getValue(),2);
	}
	
	@Test
	void userAddsDoveSoapMultipleTimesToCart() {
		Product doveSoap = new Product(1,"DOVE",39.99);
		boolean result = cut.add(user, doveSoap, 5);
		assertTrue(result);
		assertTrue(user.getCart().getContainer().get(doveSoap).equals(5));
		assertEquals(199.95, user.getCart().getValue(),2);
		result = cut.add(user, doveSoap, 3);
		assertTrue(user.getCart().getContainer().get(doveSoap).equals(8));
		assertEquals(319.92, user.getCart().getValue(),2);
	}
	
	@Test
	void userAddsMultipleItemsToCart() {
		Product doveSoap = new Product(1,"DOVE",39.99);
		Product axeDeo = new Product(1,"AXE",99.99);
		boolean result = cut.add(user, doveSoap, 2);
		assertTrue(result);
		assertTrue(user.getCart().getContainer().get(doveSoap).equals(2));
		result = cut.add(user, axeDeo, 2);
		assertTrue(user.getCart().getContainer().get(axeDeo).equals(2));
		assertEquals(314.95, user.getCart().getTaxedValue(),2);
		assertEquals(35, user.getCart().getTaxAmount(),2);
	}

}
