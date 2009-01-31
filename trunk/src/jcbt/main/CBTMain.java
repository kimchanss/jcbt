package jcbt.main;

import javax.swing.JFrame;

public class CBTMain extends JFrame {

	public CBTMain(String title) {
		super(title);
		initDBCP();
		//나머지 구현
	}
	
	/**
	 * DBMS와 연결할 때 사용될 JDBC 드라이버와 PoolingDriver 로딩 
	 */
	public void initDBCP() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Card Layout으로 설정된 중앙 패널에서 인자로 받은 이름의 패널을 보여준다.
	 * @param panelName
	 */
	public void showMainContent(String panelName) {
		//구현
	}
}
