package jcbt.main;

import jcbt.main.CBTPanel;
import jcbt.db.CBTDao;
import jcbt.dto.SessionManager;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Login Panel
 * 
 * @author Kim Tae Ho
 */

public class LogInPanel extends CBTPanel implements ActionListener{
	
	protected static final String idString = "ID";
	protected static final String passwordString = "PASSWORD";
	protected static final String loginString = "Log In";
	protected static final String signupString = "Sign Up";
	
	JTextField idField = null;
	JPasswordField passwordField = null;
	
	JButton logInButton = null;
	JButton signUpButton = null;
	
	SignUpDialog dialog = null;
	
	public LogInPanel(CBTMain cbtMain) {
		super(cbtMain);
		// TODO Auto-generated constructor stub
		//setLayout(new BorderLayout());
		createLogInPanel();
	}
	
	private void createLogInPanel(){
		// create id text field
		idField = new JTextField(10);
		idField.setActionCommand(idString);
		idField.addActionListener(this);
		
		// create password text field
		passwordField = new JPasswordField(10);
        passwordField.setActionCommand(passwordString);
        passwordField.addActionListener(this);
		
        // create some labels for the fields.
        JLabel idFieldLabel = new JLabel(idString + ": ");
        idFieldLabel.setLabelFor(idField);
        JLabel passwordFieldLabel = new JLabel(passwordString + ": ");
        passwordFieldLabel.setLabelFor(passwordField);
        
        // set layout as gridbaglayout
        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight = 1;
        add(idFieldLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 1;
        c.gridheight = 1;
        add(idField, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 2;
        c.gridheight = 1;
        add(passwordFieldLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridheight = 1;
        add(passwordField, c);
        
        logInButton = new JButton(loginString);
        logInButton.addActionListener(this);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 3;
        c.gridheight = 1;
        add(logInButton, c);
        
        signUpButton = new JButton(signupString);
        signUpButton.addActionListener(this);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 3;
        c.gridwidth = 1;
        c.gridy = 3;
        c.gridheight = 1;
        add(signUpButton, c);    
        
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String uId;
		String uName;
		String uPassword;
		
		CBTDao dao = new CBTDao();
		
		if(e.getActionCommand().equals(loginString)){
			uId = idField.getText();
			uPassword = passwordField.getText();
			
			SessionManager sm = dao.logIn(uId);
			
			if(sm == null){
				JOptionPane.showMessageDialog(cbtMain, "ID does not exist", 
						"Fail to log in", JOptionPane.ERROR_MESSAGE);
			}
			else if(!sm.getUserPassword().equals(uPassword)){
				JOptionPane.showMessageDialog(cbtMain, "Password does not match", 
						"Fail to log in", JOptionPane.ERROR_MESSAGE);
			}else{
				cbtMain.setSessionManager(sm);
				/* TODO 
				 * If the user is a common user change panel to the SelectExamPanel
				 * or if the user is admin change panel to the EditExamPanel
				 */
			}
		}else if(e.getActionCommand().equals(signupString)){
			// if user click signup button generate and set up a dialog 
			dialog = new SignUpDialog(cbtMain);
			dialog.pack();	
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension screenSize = tk.getScreenSize();
			dialog.setLocation(screenSize.width/2, screenSize.height/2);
			dialog.setVisible(true);
			
			uId = dialog.getId();
			uName = dialog.getName();
			uPassword = dialog.getPassword();
			
			if(!uPassword.equals(dialog.getConfirmPassword())){
				JOptionPane.showMessageDialog(cbtMain, "Password does not match", 
						"Fail to sign up", JOptionPane.ERROR_MESSAGE);
			}else{
				if(!dao.signUp(uId, uName, uPassword)){
					JOptionPane.showMessageDialog(cbtMain, "ID aleady exists", 
							"Fail to sign up", JOptionPane.ERROR_MESSAGE);
				}
			}
			dialog.dispose();
		}else{
			
		}
		
	}

}


/* Dialog Box */
class SignUpDialog extends JDialog implements ActionListener{
	
	protected static final String idString = "ID";
	protected static final String nameString = "NAME";
	protected static final String passwordString = "PASSWORD";
	protected static final String confirmString = "CONFIRM PASSWORD";
	protected static final String okString = "OK";
	protected static final String cancelString = "Cancel";
	
	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	
	private JTextField idField = null;
	private JTextField nameField = null;
	private JPasswordField passwordField = null;
	private JPasswordField confirmField = null;
	
	private JButton okButton = null;
	private JButton cancelButton = null;
	
	public SignUpDialog(Frame owner){
		super(owner, true);
		init();
	}
	
	private void init(){
		// create id text field
		idField = new JTextField(10);
		idField.setActionCommand(idString);
		idField.addActionListener(this);
		
		// create name text field
		nameField = new JTextField(10);
		nameField.setActionCommand(nameString);
		nameField.addActionListener(this);
		
		// create password text field
		passwordField = new JPasswordField(10);
        passwordField.setActionCommand(passwordString);
        passwordField.addActionListener(this);
		
        // create confirm text field 
        confirmField = new JPasswordField(10);
        confirmField.setActionCommand(confirmString);
        confirmField.addActionListener(this);
        
        // create some labels for the fields.
        JLabel idFieldLabel = new JLabel(idString + " :");
        idFieldLabel.setLabelFor(idField);
        
        JLabel nameFieldLabel = new JLabel(nameString + " :");
        nameFieldLabel.setLabelFor(nameField);
        
        JLabel passwordFieldLabel = new JLabel(passwordString + " :");
        passwordFieldLabel.setLabelFor(passwordField);
        
        JLabel confirmFieldLabel = new JLabel(confirmString + " :");
        confirmFieldLabel.setLabelFor(confirmField);
        
        // set layout as gridbaglayout
        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        c.gridheight = 1;
        add(idFieldLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 0;
        c.gridheight = 1;
        add(idField, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        c.gridheight = 1;
        add(nameFieldLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 1;
        c.gridheight = 1;
        add(nameField, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridheight = 1;
        add(passwordFieldLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridheight = 1;
        add(passwordField, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 3;
        c.gridheight = 1;
        add(confirmFieldLabel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 3;
        c.gridheight = 1;
        add(confirmField, c);
        
        okButton = new JButton(okString);
        okButton.addActionListener(this);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 5;
        c.gridheight = 1;
        add(okButton, c);
        
        cancelButton = new JButton(cancelString);
        cancelButton.addActionListener(this);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 5;
        c.gridheight = 1;
        add(cancelButton, c);
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getConfirmPassword(){
		return confirmPassword;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(okString)){
			id = idField.getText();
			name = nameField.getText();
			password = passwordField.getText();
			confirmPassword = confirmField.getText();
			
			this.setVisible(false);
		}else if(e.getActionCommand().equals(cancelString)){
			this.setVisible(false);
		}else{
			
		}
		
	}
}

