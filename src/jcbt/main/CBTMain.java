package jcbt.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CBTMain extends JFrame {

	
	/**
	 * 프레임의 가로 크기 
	 */
	private int width = 1024;
	/**
	 * 프레임의 세로 크기
	 */
	private int height = 768;
	
	public CBTMain(String title) {
		super(title);
		initDBCP();
		initialize();
		showFrame();
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
	 * 기본 패널들을 부착하고 프레임을 초기화한다.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	
	/**
	 * 프레임의 위치와 크기를 지정하고 보여준다. 
	 */
	public void showFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height /2);
		setSize(width, height);
		setVisible(true);
	}
	
	/**
	 * Card Layout으로 설정된 중앙 패널에서 인자로 받은 이름의 패널을 보여준다.
	 * @param panelName
	 */
	public void showMainContent(String panelName) {
		//구현
	}
}
