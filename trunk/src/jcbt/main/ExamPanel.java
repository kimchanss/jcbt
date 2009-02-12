package jcbt.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 시험 패널
 * @author Son Minho
 */
public class ExamPanel extends CBTPanel {

	//테스트용-----------------------------------
	String strQuestion = "1. 테스트용 문제..";
	String strExample1 = "보기 1";
	String strExample2 = "보기 2";
	String strExample3 = "보기 3";
	String strExample4 = "보기 4";
	String strExample5 = "보기 5";
	//------------------------------------------
	
	JPanel pnlTest = new JPanel(new BorderLayout());
	JPanel pnlExample = new JPanel(new GridLayout(5, 1));
	
	JLabel lblQuestion = new JLabel();
	
	JPanel pnlExample1 = null;
	JPanel pnlExample2 = null;
	JPanel pnlExample3 = null;
	JPanel pnlExample4 = null;
	JPanel pnlExample5 = null;
	
	JLabel lblExample1 = null;
	JLabel lblExample2 = null;
	JLabel lblExample3 = null;
	JLabel lblExample4 = null;
	JLabel lblExample5 = null;
	
	JCheckBox chk1 = new JCheckBox();
	JCheckBox chk2 = new JCheckBox();
	JCheckBox chk3 = new JCheckBox();
	JCheckBox chk4 = new JCheckBox();
	JCheckBox chk5 = new JCheckBox();
	
	JButton btnPrev = new JButton("PREV");
	JButton btnNext = new JButton("NEXT");
	JButton btnGoto = new JButton("GOTO");
	
	JLabel lblCurPage = new JLabel();
	JLabel lblPage = new JLabel("page");
	
	JTextField tfPage = new JTextField();
	
	public ExamPanel(CBTMain cbtMain) {
		super(cbtMain);
		setLayout(new BorderLayout());
		init();
	}
	
	public void init() {
		lblQuestion.setText(strQuestion);
		
		pnlExample1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlExample2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlExample3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlExample4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlExample5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		lblExample1 = new JLabel(strExample1);
		lblExample2 = new JLabel(strExample2);
		lblExample3 = new JLabel(strExample3);
		lblExample4 = new JLabel(strExample4);
		lblExample5 = new JLabel(strExample5);
		
		pnlExample1.add(chk1);
		pnlExample1.add(lblExample1);
		pnlExample2.add(chk2);
		pnlExample2.add(lblExample2);
		pnlExample3.add(chk3);
		pnlExample3.add(lblExample3);
		pnlExample4.add(chk4);
		pnlExample4.add(lblExample4);
		pnlExample5.add(chk5);
		pnlExample5.add(lblExample5);
		
		pnlExample.add(pnlExample1);
		pnlExample.add(pnlExample2);
		pnlExample.add(pnlExample3);
		pnlExample.add(pnlExample4);
		pnlExample.add(pnlExample5);
		
		pnlTest.add(lblQuestion, BorderLayout.NORTH);
		pnlTest.add(pnlExample, BorderLayout.CENTER);
		
		add(pnlTest, BorderLayout.CENTER);
	}
}
