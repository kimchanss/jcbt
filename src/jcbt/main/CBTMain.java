package jcbt.main;

import javax.swing.JFrame;

public class CBTMain extends JFrame {

	public CBTMain(String title) {
		super(title);
		initDBCP();
		//������ ����
	}
	
	/**
	 * DBMS�� ������ �� ���� JDBC ����̹��� PoolingDriver �ε� 
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
	 * Card Layout���� ������ �߾� �гο��� ���ڷ� ���� �̸��� �г��� �����ش�.
	 * @param panelName
	 */
	public void showMainContent(String panelName) {
		//����
	}
}
