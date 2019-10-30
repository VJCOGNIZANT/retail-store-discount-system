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

	@Value("${com.viz.discount.RESTRICT}")
	private static double RESTRICT;
	@Value("${com.viz.discount.DISC}")
	private static double DISC;
	@Value("${com.viz.discount.DISC_EMP}")
	private static double DISC_EMP;
	@Value("${com.viz.discount.DISC_AFF}")
	private static double DISC_AFF;
	@Value("${com.viz.discount.DISC_OLD}")
	private static double DISC_OLD;
	@Value("${com.viz.discount.TIME_THRESOLD}")
	private static int TIME_THRESOLD;

	public DiscountUtil() {

	}

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
			discount= ((int) (price / RESTRICT)) * DISC;
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

	public static double getDiscount(User user) {
		double discount = 0;
		if (user.isEmployee()) {
			discount = DISC_EMP;
		} else if (user.isAffiliated()) {
			discount = DISC_AFF;
		} else if (DiscountUtil.getYearDifference(user) >= TIME_THRESOLD) {
			discount = DISC_OLD;
		}
		return discount;

	}
}
