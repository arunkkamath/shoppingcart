package com.shop.controller;

import com.shop.domain.Product;
import com.shop.domain.User;

public interface CartController {
	
	public boolean add(User user, Product product, int quantity);

}
