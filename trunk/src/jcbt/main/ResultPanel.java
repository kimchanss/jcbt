package jcbt.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends CBTPanel{

	JPanel resultPanel = new JPanel(new BorderLayout());
	JPanel examInfo = new JPanel(new GridLayout(5, 1));
	JPanel examScore = new JPanel(new BorderLayout());
	
	JLabel subTitle1 = new JLabel("Examination Score Reoprt");
	JLabel subTitle2 = new JLabel("Exam Results");
	
	
	JLabel id = new JLabel("ID : Hong");
	JLabel name = new JLabel("NAME : Gil-Dong");
	JLabel date = new JLabel("DATE : feb 15");
	JLabel exam = new JLabel("EXAM : scwcd");
	
	
	
	public ResultPanel(CBTMain cbtMain) {
		super(cbtMain);
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		init();
	}
	
	public void init(){
		examInfo.add(subTitle1);
		examInfo.add(id);
		examInfo.add(name);
		examInfo.add(date);
		examInfo.add(exam);
		
		examScore.add(subTitle2);
		
		resultPanel.add(examInfo);
		
		add(resultPanel);
	}
	
}
