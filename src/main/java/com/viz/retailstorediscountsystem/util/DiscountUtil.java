package com.viz.retailstorediscountsystem.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Value;

import com.viz.retailstorediscountsystem.exception.CheckPriceException;
import com.viz.retailstorediscountsystem.model.User;

/**
 * The Class DiscountUtil.
 */
public final class DiscountUtil {

	@Value("${com.viz.discount.restrict}")
	private static double restrict;
	@Value("${com.viz.discount.discount}")
	private static double discount;
	@Value("${com.viz.discount.discountForEmployee}")
	private static double discountForEmployee;
	@Value("${com.viz.discount.discountForAffiliated}")
	private static double discountForAffiliated;
	@Value("${com.viz.discount.discountForOldCustomer}")
	private static double discountForOldCustomer;
	@Value("${com.viz.discount.timeThresold}")
	private static int timeThresold;

	/**
	 * Gets the year difference.
	 *
	 * @param user the user
	 * @return the year difference
	 */

	public static int getYearDifference(User user) {

		if (user.getDoa() == null) {
			return 0;
		}
		try {
			LocalDate now = LocalDate.now();
			LocalDate date = LocalDate.parse(user.getDoa());
			Period period = Period.between(date, now);
			return period.getYears();
		} catch (DateTimeParseException e) {
			throw new DateTimeParseException("The date format is not matching with 'YYYY-MM-DD'", user.getDoa(), 0, e);
		}
	}

	/**
	 * Gets the disc on price.
	 *
	 * @param price the price
	 * @return the disc on price
	 * @throws CheckPriceException
	 */
	public static double getDiscOnPrice(double price) {
		double discount=0;
		if (price > 0) {
			discount= ((int) (price / restrict)) * discount;
		}
		else {
			try {
				throw new CheckPriceException("Price should be more than 0");
			} catch (CheckPriceException e) {
				e.printStackTrace();
			}
		}
		return discount;
	}
/**
 * getDiscount method get discount
 * @param user as User Object
 * @return discount as double type
 */
	public static double getDiscount(User user) {
		double discount = 0;
		if (user.isEmployee()) {
			discount = discountForEmployee;
		} else if (user.isAffiliated()) {
			discount = discountForAffiliated;
		} else if (DiscountUtil.getYearDifference(user) >= timeThresold) {
			discount = discountForOldCustomer;
		}
		return discount;

	}
}
