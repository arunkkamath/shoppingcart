package com.shop.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {

	private Map<Product, Integer> container = new HashMap<Product, Integer>();
	private static final double TAX_RATE = 12.5;

	public Map<Product, Integer> getContainer() {
		return container;
	}

	public void add(Product product, Integer quantity) {
		validateProduct(product, quantity);
		Integer existingQuantity = getContainer().get(product);
		if (existingQuantity == null) {
			getContainer().put(product, quantity);
		} else {
			getContainer().put(product, quantity + existingQuantity);
		}
	}

	private void validateProduct(Product product, Integer quantity) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null while adding to cart");
		}
		if (product.getName() == null || product.getName().equals("")) {
			throw new IllegalArgumentException("Product's name cannot be null/empty while adding to cart");
		}
		if (quantity < 1) {
			throw new IllegalArgumentException("Product's quantity cannot be less than 1");
		}
	}

	public double getValue() {
		double totalValue = 0.0;
		for (Entry<Product, Integer> entry : getContainer().entrySet()) {
			totalValue += entry.getKey().getPrice() * entry.getValue();
		}
		return round(totalValue);
	}

	public double getTaxAmount() {
		return round(getValue() * (TAX_RATE / 100));
	}

	public double getTaxedValue() {
		return getValue() + getTaxAmount();
	}
	
	public static double round(double value) {
		return (double)Math.round(value * 100d) / 100d;
	}

}
