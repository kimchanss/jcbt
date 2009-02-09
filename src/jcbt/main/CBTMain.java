package jcbt.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import jcbt.db.CBTDao;
import jcbt.dto.Examination;
import jcbt.dto.Question;
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
		CBTDao dao = new CBTDao();
		exam = dao.getExam("driver��s license test");
		
		System.out.println(exam.getExamID());
		System.out.println(exam.getDescription());
		System.out.println(exam.getTimelimit());
		for (int i = 0; i < exam.getQuestionSize(); i++) {
			Question question = exam.getQuestion(i);
			System.out.println(question.getQuestion());
			System.out.println(question.getAnswer());
			for (int j = 0; j < question.getExampleSize(); j++) {
				System.out.println(question.getExample(j));
			}
		}
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
