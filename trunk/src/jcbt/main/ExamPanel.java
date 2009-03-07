package jcbt.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import jcbt.db.CBTDao;
import jcbt.dto.Examination;
import jcbt.dto.Question;
import jcbt.dto.SessionManager;
/**
 * 시험 패널
 * @author Son Minho
 */
public class ExamPanel extends CBTPanel implements ActionListener{
	final protected String prevString = "PREV";
	final protected String nextString = "NEXT";
	final protected String gotoString = "GO TO";
	
	Examination exam = null;
	
	JPanel examPanel = null;
	JPanel functionPanel = null;
	
	JButton prevButton = null;
	JButton nextButton = null;
	JButton gotoButton = null;
	
	JLabel indexLabel = null;
	
	JTextField putIndexField = null;
	
	int currentQuestionNumber;
	int numOfQuestion = 0;
	
	public ExamPanel(CBTMain cbtMain) {
		super(cbtMain);
		setLayout(new BorderLayout());
		init();
	}
	
	public void init() {
		//exam = cbtMain.getExam();
		
		CBTDao cbtDao = new CBTDao();
		HashMap<String, ArrayList<String>> map = cbtDao.getExamList();
		ArrayList<String> examID = (ArrayList<String>)map.get("examid");
		exam = cbtDao.getExam(examID.get(0));
		
		numOfQuestion = exam.getQuestionSize();
		
		// set Function Panel up
		makeFunctionPanel();
		
		// set Exam Panel up
		examPanel = new JPanel();
		examPanel.setLayout(new BoxLayout(examPanel, BoxLayout.Y_AXIS));
		examPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		showQuestion(1);
	
		add(functionPanel, BorderLayout.PAGE_END);
		add(examPanel, BorderLayout.CENTER);
	}
	
	private void makeFunctionPanel(){
		functionPanel = new JPanel();
		
		prevButton = new JButton(prevString);
		nextButton = new JButton(nextString);
		gotoButton = new JButton(gotoString);
		
		indexLabel = new JLabel("test");
		
		putIndexField = new JTextField(2);
		
		// add action listener
		ButtonActionHandler handler = new ButtonActionHandler();
		prevButton.addActionListener(handler);
		nextButton.addActionListener(handler);
		gotoButton.addActionListener(handler);
		
		// set layout
		GridBagLayout gridbag = new GridBagLayout();
        functionPanel.setLayout(gridbag);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        functionPanel.add(prevButton, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        functionPanel.add(nextButton, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        functionPanel.add(indexLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 3;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        functionPanel.add(gotoButton, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 4;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        functionPanel.add(putIndexField, c);
        
        c= new GridBagConstraints();
        JLabel page = new JLabel("page");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 5;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        functionPanel.add(page, c);
	}
	
	private void showQuestion(int index){
		
		//Question question = exam.getQuestion(index);
		Question question = null;
		if(question == null){
			return;
		}
		currentQuestionNumber = index;
		
		int exampleSize = question.getExampleSize();
		JLabel questionSentence = new JLabel();
		questionSentence.setText(index+question.getQuestion());
		
		questionSentence.setAlignmentX(Component.LEFT_ALIGNMENT);
		examPanel.add(questionSentence);
		ButtonGroup group = new ButtonGroup();
		for(int i=1; i<=exampleSize; i++){
			addExample(i, question.getExample(i), group, examPanel);
		}
		
		indexLabel.setText(currentQuestionNumber+"/"+numOfQuestion);
	}
	
	private void addExample(int index, String content, ButtonGroup group, Container container){
		JRadioButton radioButton = new JRadioButton(index+"."+content);
		radioButton.setActionCommand(index+"");
		radioButton.addActionListener(this);
		
		container.add(radioButton);
		group.add(radioButton);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		cbtMain.setSelectedAnswer(currentQuestionNumber, e.getActionCommand());
	}
	
	class ButtonActionHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals(prevString)){
				showQuestion(currentQuestionNumber-1);
			}else if(e.getActionCommand().equals(nextString)){
				showQuestion(currentQuestionNumber+1);
			}else if(e.getActionCommand().equals(gotoString)){
				try{
					int pageNum = Integer.parseInt(putIndexField.getText());
					showQuestion(pageNum);
				}catch(NumberFormatException nfe){
				}finally{
					putIndexField.setText("");
				}
			}else{
			}
		}
		
	}
}
