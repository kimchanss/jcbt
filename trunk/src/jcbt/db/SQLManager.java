package jcbt.db;

/**
 * 쿼리를 관리한다. 
 * @author Son Minho
 */
public class SQLManager {

	/**
	 * UserID 중복 확인
	 */
	public String getUserIDDupCheckQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) FROM JCBTUSER WHERE USERID = ?  \n");
		return buffer.toString();
	}
	
	/**
	 * User조회
	 */
	public String getUserQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT USERID, USERNAME, PASSWORD, USERLEVEL \n");
		buffer.append("FROM   JCBTUSER 								\n");
		buffer.append("WHERE  USERID = ? 							\n");
		return buffer.toString();
	}
	
	/**
	 * 회원가입
	 */
	public String getSignUpQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO JCBTUSER 					 \n");
		buffer.append("  (USERID, USERNAME, PASSWORD, USERLEVEL) \n");
		buffer.append("VALUES 									 \n");
		buffer.append("  (?, ?, ?, ?)				 			 \n");
		return buffer.toString();
	}
	
	/**
	 * 시험목록 조회
	 */
	public String getExamListQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT EXAMID, DESCRIPTION FROM JCBTEXAM		\n");
		buffer.append("ORDER BY EXAMID								\n");
		return buffer.toString();
	}
	
	/**
	 * 시험 및 문제조회
	 */
	public String getExamQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT EXAM.EXAMID 						\n");
		buffer.append("      ,EXAM.DESCRIPTION 					\n");
		buffer.append("      ,EXAM.TIMELIMIT 					\n");
		buffer.append("      ,QUST.QUESTIONNO 					\n");
		buffer.append("      ,QUST.QUESTION 					\n");
		buffer.append("      ,QUST.ANSWER 						\n");
		buffer.append("FROM   JCBTEXAM EXAM 					\n");
		buffer.append("      ,JCBTQUESTION QUST 				\n");
		buffer.append("WHERE  EXAM.EXAMID = QUST.EXAMID 		\n");
		buffer.append("AND    EXAM.EXAMID = ? 					\n");
		buffer.append("ORDER BY EXAMID, QUESTIONNO				\n");
		return buffer.toString();
	}
	
	/**
	 * 보기 조회
	 */
	public String getExampleQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT QUESTIONNO						\n");
		buffer.append("      ,EXAMPLENO							\n");
		buffer.append("      ,EXAMPLE							\n");
		buffer.append("FROM   JCBTEXAMPLE 						\n");
		buffer.append("WHERE  EXAMID = ?						\n");
		buffer.append("ORDER BY QUESTIONNO, EXAMPLENO			\n");
		return buffer.toString();
	}
	
	/**
	 * 보기삭제
	 */
	public String getDeleteExampleQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM JCBTEXAMPLE WHERE EXAMID = ? \n");
		return buffer.toString();
	}
	
	/**
	 * 문제삭제
	 */
	public String getDeleteQuestionQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM JCBTQUESTION WHERE EXAMID = ? \n");
		return buffer.toString();
	}
	
	/**
	 * 시험삭제
	 */
	public String getDeleteExamQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DELETE FROM JCBTEXAM WHERE EXAMID = ? \n");
		return buffer.toString();
	}
	
	/**
	 * ExamID 중복확인
	 */
	public String getExamIDDupCheckQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) FROM JCBTEXAM WHERE EXAMID = ? \n");
		return buffer.toString();
	}
	
	/**
	 * 시험입력
	 */
	public String getInsertExamQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO JCBTEXAM					\n");
		buffer.append("  (EXAMID, DESCRIPTION, TIMELIMIT)	\n");
		buffer.append("VALUES								\n");
		buffer.append("  (?, ?, ?)							\n");
		return buffer.toString();
	}
	
	/**
	 * 문제입력
	 */
	public String getInsertQuestionQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO JCBTQUESTION					\n");
		buffer.append("  (QUESTIONNO, EXAMID, QUESTION, ANSWER)	\n");
		buffer.append("VALUES									\n");
		buffer.append("  (?, ?, ?, ?)							\n");
		return buffer.toString();
	}
	
	/**
	 * 보기입력
	 */
	public String getInsertExampleQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO JCBTEXAMPLE						\n");
		buffer.append("  (EXAMPLENO, QUESTIONNO, EXAMID, EXAMPLE)	\n");
		buffer.append("VALUES										\n");
		buffer.append("  (?, ?, ?, ?)								\n");
		return buffer.toString();
	}
}
