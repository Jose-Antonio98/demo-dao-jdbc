package edu.joseph.model.dao;

import edu.joseph.db.Db;
import edu.joseph.model.dao.implementation.SellerDaoJDBC;

public class DaoFactory {
	
	//metodo que estancia a implementação através da interface sem expor a implementação
	public static DaoInterface createSellerDao() {
		return new SellerDaoJDBC(Db.getConnection());
	}
}
