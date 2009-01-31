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
	
	//����
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
	 * ���� �ڿ��� ��ȯ�Ѵ�.
	 */
	public void closeAll() {
		try {
			if (conn != null) conn.close();
			if (ps != null) ps.close();
			if (rs != null) rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
