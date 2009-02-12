package jcbt.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import jcbt.dto.Examination;
import jcbt.dto.SessionManager;

/**
 * ���� ������
 * 
 * @author Son Minho
 */
public class CBTMain extends JFrame {

	/**
	 * �������� ���� ũ�� 
	 */
	private int width = 1024;
	/**
	 * �������� ���� ũ��
	 */
	private int height = 768;
	
	/**
	 * ��������
	 */
	protected SessionManager session = null;
	
	/**
	 * ������ ���� ����
	 */
	protected Examination exam = null;
	
	public CBTMain(String title) {
		super(title);
		initDBCP();
		initialize();
		showFrame();
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
	 * �⺻ �гε��� �����ϰ� �������� �ʱ�ȭ�Ѵ�.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		ExamPanel examPanel = new ExamPanel(this);
		add(examPanel);
		*/
	}
	
	
	/**
	 * �������� ��ġ�� ũ�⸦ �����ϰ� �����ش�. 
	 */
	public void showFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height /2);
		setSize(width, height);
		setVisible(true);
	}
	
	/**
	 * Card Layout���� ������ �߾� �гο��� ���ڷ� ���� �̸��� �г��� �����ش�.
	 * @param panelName
	 */
	public void showMainContent(String panelName) {
		//����
	}
}
