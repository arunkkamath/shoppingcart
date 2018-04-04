package com.shop.controller;

import com.shop.domain.Cart;
import com.shop.domain.Product;
import com.shop.domain.User;

public class ShoppingCartController implements CartController {

	public boolean add(User user, Product product, int quantity) {
		Cart cart = user.getCart();
		cart.add(product, quantity);
		return true;
	}

}
