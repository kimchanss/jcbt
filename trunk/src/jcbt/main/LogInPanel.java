package jcbt.main;

import jcbt.main.CBTPanel;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

public class LogInPanel extends CBTPanel implements ActionListener{
	
	protected static final String idString = "ID";
	protected static final String passwordString = "PASSWORD";
	
	public LogInPanel(CBTMain cbtMain) {
		super(cbtMain);
		// TODO Auto-generated constructor stub
		//setLayout(new BorderLayout());
		createLogInPanel();
	}
	
	private void createLogInPanel(){
		JTextField textField = new JTextField(10);
		textField.setActionCommand(idString);
		textField.addActionListener(this);
		
		JPasswordField passwordField = new JPasswordField(10);
        passwordField.setActionCommand(passwordString);
        passwordField.addActionListener(this);
		
        JLabel textFieldLabel = new JLabel(idString + ": ");
        textFieldLabel.setLabelFor(textField);
        JLabel passwordFieldLabel = new JLabel(passwordString + ": ");
        passwordFieldLabel.setLabelFor(passwordField);
        
        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
        
        JLabel[] labels = {textFieldLabel, passwordFieldLabel};
        JTextField[] textFields = {textField, passwordField};
        addLabelTextRows(labels, textFields, gridbag, this);
        
	}
	

	private void addLabelTextRows(JLabel[] labels,
									JTextField[] textFields,
									GridBagLayout gridbag,
									Container container) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		int numLabels = labels.length;
		
		for (int i = 0; i < numLabels; i++) {
		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		container.add(labels[i], c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;     //end row
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		container.add(textFields[i], c);
	}
}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
