package jcbt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CBTDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private SQLManager sql = new SQLManager();
	
	//샘플
	public String getTestName() {
		String userName = "";

		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql.getTestQuery());
			rs = ps.executeQuery();
			
			if (rs.next()) {
				userName = rs.getString("USERNAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return userName;
	}
	
	/**
	 * 사용된 자원을 반환한다.
	 */
	public void closeAll() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
