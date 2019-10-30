package com.viz.retailstorediscountsystem.dao;

import org.springframework.stereotype.Repository;

import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;

/**
 * The Interface DiscountCalculator.
 */
@Repository
public interface DiscountSystemDao {
	
	/**
	 * Calculate discount for groceries.
	 *
	 * @param user the user
	 * @param prd the prd
	 * @return the double
	 */
	double calculateDiscountForGroceries(User user,ProductDetails prd);

}
