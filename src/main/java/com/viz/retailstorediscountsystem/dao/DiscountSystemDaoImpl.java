package com.viz.retailstorediscountsystem.dao;

import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;
import com.viz.retailstorediscountsystem.util.DiscountUtil;

/**
 * The Class DiscountCalculatorImpl.
 */
public class DiscountSystemDaoImpl implements DiscountSystemDao {

	private double discAmt;

	public double calculateDiscountForGroceries(User user, ProductDetails prd) {
		double dicount = 0;
		discAmt = DiscountUtil.getDiscOnPrice(prd.getPrice());
		if (!prd.isGrocery()) {
			dicount = DiscountUtil.getDiscount(user);
		}
		return discAmt = discAmt + calculateDiscPrice(prd.getPrice(), dicount);
	}

	/**
	 * Calculate disc price.
	 *
	 * @param i       the user
	 * @param dicount the price
	 * @return the double
	 */
	private static double calculateDiscPrice(double price, double dicount) {
		return price * dicount;

	}

}
