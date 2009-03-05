package jcbt.main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jcbt.db.CBTDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/*
 * author : Kim Tae Ho
 */

public class SelectExamPanel extends CBTPanel implements ActionListener{

	final protected String startString = "Start";
	
	JButton startButton = null;
	JList examList = null;
	JTextArea descriptionTextArea = null;
	
	ArrayList<String> examID = null;
	ArrayList<String> description = null;
	
	String selectedExam = null;
	
	public SelectExamPanel(CBTMain cbtMain) {
		super(cbtMain);
		// TODO Auto-generated constructor stub
		
		init();
	}
	
	public void init(){
		// Get Exam IDs and Descriptions from DB 
		CBTDao dao = new CBTDao();
		HashMap<String, ArrayList<String>> map = dao.getExamList();
		examID = (ArrayList<String>)map.get("examid");
		description = (ArrayList<String>)map.get("description");
		
		// Make Exam ID List
		examList = new JList(examID.toArray());
		ListSelectionModel listSelectionModel = examList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionHandler());
		JScrollPane listPane = new JScrollPane(examList);	
		JPanel listContainer = new JPanel(new GridLayout(1,1));
		listContainer.setBorder(BorderFactory.createTitledBorder("Select Your Exam"));
		listContainer.add(listPane);
		
		// Make Description Text Area for a exam ID
		descriptionTextArea = new JTextArea(20, 40);
		descriptionTextArea.setEditable(false);
		JScrollPane descriptionPane = new JScrollPane(descriptionTextArea);
		
		// Lay out components
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
        add(listContainer, c);
		
		
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 0;
        c.gridheight = 1;
		add(descriptionPane, c);
		
		startButton = new JButton(startString);
        startButton.addActionListener(this);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight = 1;
        add(startButton, c);   
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(startString)){
			// Change panel with a exampanel
		}
		
	}
	
	// List event handler
	class ListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
        	if(!e.getValueIsAdjusting()){
        		selectedExam = (String)examList.getSelectedValue();
        		int index = examID.indexOf(selectedExam);	
        		descriptionTextArea.append((String)description.get(index));
        	}
        }
    }

}
