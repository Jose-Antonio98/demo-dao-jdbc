package edu.joseph.demodaojdbc;

import java.util.Date;

import org.junit.jupiter.api.Test;

import edu.joseph.model.Department;
import edu.joseph.model.Seller;

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
}
