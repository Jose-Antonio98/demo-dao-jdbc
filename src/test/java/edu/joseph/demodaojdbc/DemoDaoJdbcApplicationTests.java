package edu.joseph.demodaojdbc;

import org.junit.jupiter.api.Test;
import edu.joseph.model.Department;


class DemoDaoJdbcApplicationTests {

	@Test
	void myDepartment() {
		var obj = new Department(1, "Books");
		System.out.println(obj.toString());
	}
	
	@Test
	void myDepartmentEquals() {
		var obj = new Department(1, "Books");
		var obj1 = new Department(2, "Eletronics");
		boolean comparison = obj.equals(obj1) ? true : false; 
		System.out.println(comparison);
		
	}
}
