package jcbt.dto;

/**
 * �α��ε� ���� ������ �����Ѵ�.
 * 
 * @author Son Minho
 */
public class SessionManager {

	private String userID = "";
	private String userPassword = "";
	private String userName = "";
	private int userLevel = 0;
	
	private static SessionManager session = null;
	
	private SessionManager () {}
	
	private SessionManager (String userID, String userPassword, String userName, int userLevel) {
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userLevel = userLevel;
	}
	
	public static SessionManager getInstance() {
		if (session == null) {
			session = new SessionManager();
		} 			
		return session;
	}
	
	public static SessionManager getInstance(String userID, String userPassword, String userName, int userLevel) {
		if (session == null) {
			session = new SessionManager(userID, userPassword, userName, userLevel);
		}	
		return session;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getUserLevel() {
		return userLevel;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	/**
	 * �α׾ƿ��� ������ Ŭ�����Ѵ�. 
	 */
	public void clearSession() {
		this.userID = "";
		this.userPassword = "";
		this.userName = "";
		this.userLevel = 0;
	}
	
	/**
	 * �α��� ���θ� �����Ѵ�.
	 * 
	 * @return �α��� ����
	 */
	public boolean isLoggedIn() {
		if (this.userID.equals("")) return false; else return true;
	}
}
