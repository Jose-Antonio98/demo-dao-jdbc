package edu.joseph.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {

	private static Connection conn = null;

	// pega propriedades de login, senha e link do banco de dados
	private static Properties loadProperties() {
		try (var fs = new FileInputStream("C:\\Users\\josea\\OneDrive\\Documentos\\WorkSpace java\\demo-dao-jdbc\\demo-dao-jdbc\\src\\main\\resources\\application.properties")) {
			var props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException("Error: " + e.getMessage());
		}
	}

	// abre conexão com banco de dados
	public static Connection getConnection() {
		try {
			if (conn == null) {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException("Error: " + e.getMessage());
			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeresultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
	}
}