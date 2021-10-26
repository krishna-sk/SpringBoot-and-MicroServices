package com.krishna.util;

import com.krishna.entity.Product;

public interface ProductUtil {

	public static void calculate(Product p) {

		double cost = p.getCost();
		double gst = cost * 20 / 100.0;
		double disc = cost * 12 / 100.0;

		p.setGst(gst);
		p.setDiscount(disc);
	}

}