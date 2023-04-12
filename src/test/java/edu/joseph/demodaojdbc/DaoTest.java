package edu.joseph.demodaojdbc;

import org.junit.jupiter.api.Test;

import edu.joseph.model.dao.DaoFactory;

class DaoTest {
	
	@Test
	void testSellerDao() {
		var sellerDao = DaoFactory.createSellerDao();
		
		var seller = sellerDao.findById(3);
		
		System.out.println(seller.toString());
	}
}
