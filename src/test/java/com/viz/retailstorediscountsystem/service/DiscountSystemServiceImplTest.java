package com.viz.retailstorediscountsystem.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.viz.retailstorediscountsystem.dao.DiscountSystemDao;
import com.viz.retailstorediscountsystem.model.ProductDetails;
import com.viz.retailstorediscountsystem.model.User;

public class DiscountSystemServiceImplTest {
	private DiscountSystemServiceImpl service;
	@Mock
	private DiscountSystemDao discountSystemDao;

	@Before
	public void setUp() throws Exception {
		service=new DiscountSystemServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(service, "discountSystemDao", discountSystemDao);
	}

	@Test
	public void testCalculateDiscountForGroceries() {
		User user=new User();
		ProductDetails prd=new ProductDetails();
		double result = service.calculateDiscountForGroceries(user, prd);
		assertNotNull(result);
	}

}
