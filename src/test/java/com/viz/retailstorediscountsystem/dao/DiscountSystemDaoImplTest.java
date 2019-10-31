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
		ReflectionTestUtils.setField(obj,"restrict",100);
		ReflectionTestUtils.setField(obj,"discount",5);
		ReflectionTestUtils.setField(obj,"discountForEmployee",0.3);
		ReflectionTestUtils.setField(obj,"discountForAffiliated",0.1);
		ReflectionTestUtils.setField(obj,"discountForOldCustomer",0.05);
		ReflectionTestUtils.setField(obj,"timeThresold",2);
	}
	//for product's price less than 0 
	@Test
	public void test_calculateDiscountForGroceries_IfPriceIsLessthan0() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(121);
		user.setName("UserName1");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(true);
		prd.setName("Prodname0");
		prd.setPrice(-990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(0), disc);
	}
	
	
	//When the item is groceries
	@Test
	public void test_calculateDiscountForGroceries_WhenGroceries() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(122);
		user.setName("UserName2");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(true);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(45.0), disc);
	}
	
	//When the User is the Employee
	@Test
	public void testDiscount_NG_For_Employee() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(true);
		user.setId(123);
		user.setName("UserName3");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(342.0), disc);// 45+employee 30% discount (297)= 342
	}
	//When the user is Affiliated
	@Test
	public void testDiscount_NG_For_Affiliated() {
		User user=new User();
		user.setAffiliated(true);
		user.setEmployee(false);
		user.setId(124);
		user.setName("UserName4");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(144.0), disc);// 45+ affiliated 10% discount (99)= 144
	}
	//When is User is associated with store for more than 2 years
	@Test
	public void testDiscount_NG_assciatedForMoreThan2years() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(125);
		user.setName("UserName5");
		user.setDoa("2008-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(94.5), disc);// 45+ association for more than 2 years 5% discount (49.5)= 144
	}
	
	//When is User is neither employee nor affiliated and  associated with store for less than 2 years
	@Test
	public void testDiscount_NG_assciatedForLessThan2years() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(126);
		user.setName("UserName6");
		user.setDoa("2018-12-02");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(45.0), disc);// 45+ association for more than 2 years  0% discount (0)= 144
	}
	//when the user date of association is less null
	@Test
	public void testDiscount_NG_assciatedDateIsNull() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(127);
		user.setName("UserName7");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		Double disc=dao.calculateDiscountForGroceries(user, prd);
		assertEquals(new Double(45.0), disc);// 45+ association date is null  0% discount (0)= 144
	}

	//when the user date of association has format mismatch
	@Test(expected=DateTimeParseException.class)
	public void testDiscount_NG_DateTimeParseException() {
		User user=new User();
		user.setAffiliated(false);
		user.setEmployee(false);
		user.setId(128);
		user.setName("UserName8");
		user.setDoa("12-02-2018");
		ProductDetails prd=new ProductDetails();
		prd.setGrocery(false);
		prd.setName("Prodname1");
		prd.setPrice(990);
		prd.setPrdId(111);
		dao.calculateDiscountForGroceries(user, prd);
	}
}
