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
		System.out.println("Test 1");
		var seller = new Seller();
		System.out.println(seller);
	}
	
	@Test
	void mySellerEquals() {
		System.out.println("Test 2");
		var depart = new Department(1, "Books");
		var seller1 = new Seller(01, "Jose", "jose@gmail.com", new Date() , 3000.0, depart);
		var seller2 = new Seller(02, "Alex", "alex@gmail.com", new Date() , 3500.0, depart);
		System.out.println(seller1.equals(seller2));
	}
	
	@Test
	void testFindById() {
		System.out.println("Test 3");
		DaoInterface sellerDao = DaoFactory.createSellerDao();
		var seller = sellerDao.findById(3);
		System.out.println(seller);
	}
	
	@Test
	void testFindbyDepartment() {
		System.out.println("Test 4");
		DaoInterface sellerDao = DaoFactory.createSellerDao();
		var department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}
	
	@Test
	void testFindAll() {
		System.out.println("Test 5");
		DaoInterface sellerDao = DaoFactory.createSellerDao();
		List<Seller> list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}
}
