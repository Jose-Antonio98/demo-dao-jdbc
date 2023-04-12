package edu.joseph.model.dao;

import java.util.List;

import edu.joseph.model.Department;
import edu.joseph.model.Seller;

public interface DaoInterface {
	
	void insert (Object obj);
	void update(Object obj);
	void deleteById(Integer id);
	Object findById(Integer id);
	List<Seller> findByDepartment(Department dept);
	List<Seller> findAll();
}
