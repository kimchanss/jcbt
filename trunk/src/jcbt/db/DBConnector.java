package jcbt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection을 반환해준다.
 * 
 * @author Son Minho
 */
public class DBConnector {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/pool");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
