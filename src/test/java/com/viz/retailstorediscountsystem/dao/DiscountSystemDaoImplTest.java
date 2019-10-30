package com.viz.retailstorediscountsystem.dao;

import static org.junit.Assert.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;
import com.viz.retailstorediscountsystem.util.DiscountUtil;

public class DiscountSystemDaoImplTest {
	private DiscountSystemDaoImpl dao;

	@Before
	public void setUp() throws Exception {
		dao=new DiscountSystemDaoImpl();
		DiscountUtil obj=new DiscountUtil();
		MockitoAnnotations.initMocks(DiscountUtil.class);
		ReflectionTestUtils.setField(obj,"RESTRICT",100);
		ReflectionTestUtils.setField(obj,"DISC",5);
		ReflectionTestUtils.setField(obj,"DISC_EMP",0.3);
		ReflectionTestUtils.setField(obj,"DISC_AFF",0.1);
		ReflectionTestUtils.setField(obj,"DISC_OLD",0.05);
		ReflectionTestUtils.setField(obj,"TIME_THRESOLD",2);
	}
	
	@Test
	public void test_calculateDiscountForGroceries_IfPriceIsLessthan0() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(true);
		prd.setName("Prodname1");
		prd.setPrice(-990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(0), disc);
	}

	@Test
	public void test_calculateDiscountForGroceries_WhenGroceries() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(true);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(45.0), disc);
	}
	@Test
	public void testDiscount_NG_For_Employee() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(true);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(342.0), disc);// 45+employee 30% discount (297)= 342
	}
	@Test
	public void testDiscount_NG_For_Affiliated() {
		User user=new User();
		user.setAffiliated(true);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(144.0), disc);// 45+ affiliated 10% discount (99)= 144
	}
	@Test
	public void testDiscount_NG_assciatedForMoreThan2years() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(94.5), disc);// 45+ association for more than 2 years 5% discount (49.5)= 144
	}
	@Test
	public void testDiscount_NG_assciatedForLessThan2years() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("2018-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(45.0), disc);// 45+ association for more than 2 years  0% discount (0)= 144
	}
	@Test
	public void testDiscount_NG_assciatedDateIsNull() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(45.0), disc);// 45+ association date is null  0% discount (0)= 144
	}

	@Test(expected=DateTimeParseException.class)
	public void testDiscount_NG_DateTimeParseException() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(123);
		user.setName("UserName");
		user.setDoa("12-02-2018");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		dao.calculateDiscountForGroceries(user, prd);
	}
}
