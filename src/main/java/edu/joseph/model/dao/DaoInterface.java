package edu.joseph.model.dao;

import java.util.List;

import edu.joseph.model.Department;
import edu.joseph.model.Seller;

public interface DaoInterface {
	
	void insert (Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findByDepartment(Department dept);
	List<Seller> findAll();
}
