package edu.joseph.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insert(Seller obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				Db.closeresultSet(rs);
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
		}
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
				var dep = instantiatiateDepartment(rs);
				var obj = instantiatiateSeller(rs, dep);
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
			Db.closeresultSet(rs);
		}
	}

	private Department instantiatiateDepartment(ResultSet rs) throws SQLException {
		var dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
		return dep;
	}

	private Seller instantiatiateSeller(ResultSet rs, Department dep) throws SQLException {
		var obj = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"),
				rs.getDouble("BaseSalary"), dep);
		return obj;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// passa o camando para o BD
			st = conn.prepareStatement("SELECT seller.* , department.Name as DepName FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = Department.Id ORDER BY Name");
			rs = st.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> mapDepartment = new HashMap<>();

			// evita instanciar varios departamentos repetidos
			while (rs.next()) {

				Department dep = mapDepartment.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiatiateDepartment(rs);
					mapDepartment.put(rs.getInt("DepartmentId"), dep);
				}

				var obj = instantiatiateSeller(rs, dep);
				sellers.add(obj);
			}
			return sellers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
			Db.closeresultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department dept) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.* , department.Name as DepName FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = Department.Id WHERE DepartmentId = ? ORDER BY Name");

			st.setInt(1, dept.getId());
			rs = st.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> mapDepartment = new HashMap<>();

			while (rs.next()) {

				Department dep = mapDepartment.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiatiateDepartment(rs);
					mapDepartment.put(rs.getInt("DepartmentId"), dep);
				}

				var obj = instantiatiateSeller(rs, dep);
				sellers.add(obj);
			}
			return sellers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
			Db.closeresultSet(rs);
		}
	}
}
