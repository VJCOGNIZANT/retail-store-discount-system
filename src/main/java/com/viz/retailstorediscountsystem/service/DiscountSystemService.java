package com.viz.retailstorediscountsystem.service;

import org.springframework.stereotype.Service;

import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;

/**
 * The Interface DiscountCalculator.
 */
@Service
public interface DiscountSystemService {
	
	/**
	 * Calculate discount for groceries.
	 *
	 * @param user accepts User
	 * @param prd accepts ProductDetails
	 * @return the double
	 */
	double calculateDiscountForGroceries(User user,ProductDetails prd);

}
