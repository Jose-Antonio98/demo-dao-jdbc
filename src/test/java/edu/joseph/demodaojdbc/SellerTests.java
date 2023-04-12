package edu.joseph.demodaojdbc;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.joseph.model.Department;
import edu.joseph.model.Seller;
import edu.joseph.model.dao.DaoFactory;
import edu.joseph.model.dao.DaoInterface;

class SellerTests {
	
	@Test
	void mySeller() {
		var seller = new Seller();
		System.out.println(seller);
	}
	
	@Test
	void mySellerEquals() {
		var depart = new Department(1, "Books");
		var seller1 = new Seller(01, "Jose", "jose@gmail.com", new Date() , 3000.0, depart);
		var seller2 = new Seller(02, "Alex", "alex@gmail.com", new Date() , 3500.0, depart);
		System.out.println(seller1.equals(seller2));
	}
	
	@Test
	void testFindById() {
		DaoInterface sellerDao = DaoFactory.createSellerDao();
		var seller = sellerDao.findById(3);
		System.out.println(seller);
	}
	
	@Test
	void testFindbyDepartment() {
		DaoInterface sellerDao = DaoFactory.createSellerDao();
		var department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}
}
