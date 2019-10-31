package com.viz.retailstorediscountsystem.dao;

import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;
import com.viz.retailstorediscountsystem.util.DiscountUtil;

/**
 * The Class DiscountCalculatorImpl.
 */
public class DiscountSystemDaoImpl implements DiscountSystemDao {

	public double calculateDiscountForGroceries(User user, ProductDetails prd) {
		double dicount = 0;
		double discAmt = 0;
		discAmt = DiscountUtil.getDiscOnPrice(prd.getPrice());
		if (!prd.isGrocery()) {
			dicount = DiscountUtil.getDiscount(user);
		}
		return discAmt + calculateDiscPrice(prd.getPrice(), dicount);
	}

	/**
	 * Calculate disc price.
	 *
	 * @param price accepts double
	 * @param dicount accepts double
	 * @return the double
	 */
	private static double calculateDiscPrice(double price, double dicount) {
		return price * dicount;

	}

}
