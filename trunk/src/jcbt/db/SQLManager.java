package jcbt.db;

public class SQLManager {

	//ป๙วร
	public String getTestQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT USERID            \n");
		buffer.append("      ,USERNAME          \n");
		buffer.append("      ,PASSWORD          \n");
		buffer.append("      ,USERLEVEL         \n");
		buffer.append("FROM   JCBTUSER          \n");
		buffer.append("WHERE  USERID = 'admin'  \n");
		return buffer.toString();
	}
}
