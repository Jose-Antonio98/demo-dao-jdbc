package edu.joseph.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.joseph.db.Db;
import edu.joseph.db.DbException;
import edu.joseph.model.Department;
import edu.joseph.model.Seller;
import edu.joseph.model.dao.DaoInterface;

public class SellerDaoJDBC implements DaoInterface {

	private Connection conn;

	// metodo que faz a conexão automatico com o Bd ao ser instanciado
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// passa o camando para o BD
			st = conn.prepareStatement(
					"SELECT seller.* , department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller .DepartmentId = Department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				// instancia o objeto department pegando os itens contidos cons colunas e linhas
				// do objeto rs
				var dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));

				var obj = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
						rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), dep);
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeresultSet(rs);
		}
	}

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
