package edu.joseph.model.dao;

import java.util.List;

public interface DaoInterface {
	
	void insert (Object obj);
	void update(Object obj);
	void deleteById(Integer id);
	void findById(Integer id);
	List<Object> findAll();
}
