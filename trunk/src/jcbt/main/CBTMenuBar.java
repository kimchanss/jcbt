package jcbt.main;

import javax.swing.*;

/* menu bar
 * 09/02/18
 * ±Ë≈¬»£
 *  
 */
public class CBTMenuBar{

	// menu bar
	JMenuBar bar;
	
	// menus in the menu bar
	JMenu test;
	JMenu admin;
	JMenu help;
	
	// menu items in the Test menu
	JMenuItem newTest;
	JMenuItem submit;
	JMenuItem logout;
	
	// menu items in the Admin menu
	JMenuItem editExam;
	JMenuItem uploadExam;
	
	// menu items in the Help menu
	JMenuItem helpItem;
	JMenuItem about;
	
	
	public CBTMenuBar() {
		// TODO Auto-generated constructor stub
		
		createMenu();
	}
	
	private void createMenu(){
		bar = new JMenuBar();
		
		test = new JMenu("Test");
		admin = new JMenu("Admin");
		help = new JMenu("Help");
		
		newTest = new JMenuItem("New Test");
		submit = new JMenuItem("Submit");
		logout = new JMenuItem("Logout");
		test.add(newTest);		test.add(submit);	test.add(logout);
		
		editExam = new JMenuItem("Edit Exam");
		uploadExam = new JMenuItem("Upload Exam");
		admin.add(editExam);	admin.add(uploadExam);
		
		helpItem = new JMenuItem("Help");
		about = new JMenuItem("About");
		help.add(helpItem);		help.add(about);
		
		bar.add(test);  		bar.add(admin);		bar.add(help);
	}
	
	public JMenuBar getMenuBar(){
		return bar;
	}

}
