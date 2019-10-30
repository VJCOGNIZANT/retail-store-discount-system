package com.viz.retailstorediscountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.viz.retailstorediscountsystem.dao.DiscountSystemDao;
import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;

/**
 * The Class DiscountCalculatorImpl.
 */
public class DiscountSystemServiceImpl implements DiscountSystemService {
	@Autowired
	private DiscountSystemDao discountSystemDao;

	@Override
	public double calculateDiscountForGroceries(User user, ProductDetails prd) {
		return discountSystemDao.calculateDiscountForGroceries(user, prd);
	}

}
