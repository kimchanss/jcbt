package jcbt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jcbt.dto.Examination;
import jcbt.dto.Question;
import jcbt.dto.SessionManager;

/**
 * Data Access Object
 * 
 * @author Son Minho
 *
 */
public class CBTDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private SQLManager sql = new SQLManager();
	
	/**
	 * 회원가입
	 * @param userID
	 * @param userName
	 * @param userPassword
	 * @return 회원가입이 정상적으로 성공되면 true를, ID 중복으로 실패할 경우 false를 리턴한다.
	 */
	public boolean signUp(String userID, String userName, String userPassword) {
		int count = 0;
		boolean result = false;
		
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql.getUserIDDupCheckQuery());
			ps.setString(1, userID);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			if (count == 0) {
				ps = conn.prepareStatement(sql.getSignUpQuery());
				ps.setString(1, userID);
				ps.setString(2, userName);
				ps.setString(3, userPassword);
				ps.setInt(4, 1); //userLevel은 무조건 1로 입력한다.
				ps.executeUpdate();
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return result;
	}
	
	/**
	 * 로그인
	 * @param userID
	 * @return 유저의 세션정보를 리턴
	 */
	public SessionManager logIn(String userID) {
		
		SessionManager session = SessionManager.getInstance();
		
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql.getUserQuery());
			ps.setString(1, userID);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				session.setUserID(rs.getString("USERID"));
				session.setUserName(rs.getString("USERNAME"));
				session.setUserPassword(rs.getString("PASSWORD"));
				session.setUserLevel(rs.getInt("USERLEVEL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return session;
	}
	
	/**
	 * 시험목록조회
	 * @return examID 리스트와 description 리스트가 담긴 HashMap을 리턴한다.
	 */
	public HashMap<String, ArrayList<String>> getExamList() {
		
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> examid = new ArrayList<String>();
		ArrayList<String> description = new ArrayList<String>();
	
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql.getExamListQuery());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				examid.add(rs.getString("EXAMID"));
				description.add(rs.getString("DESCRIPTION"));
			}
			
			map.put("examid", examid);
			map.put("description", description);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return map;
	}
	
	/**
	 * 시험 조회
	 * @param examID
	 * @return Examination
	 */
	public Examination getExam(String examID) {
		
		Examination exam = null;
		Question question = null;
		ArrayList<String> example = null;
		
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql.getExamQuery());
			ps.setString(1, examID);
			rs = ps.executeQuery();
	
			int idx = 0;
			while (rs.next()) {
				if (idx == 0) {
					exam = new Examination();
					exam.setExamID(rs.getString("EXAMID"));
					exam.setDescription(rs.getString("DESCRIPTION"));
					exam.setTimelimit(rs.getInt("TIMELIMIT"));
				}
				
				question = new Question();
				question.setQuestion(rs.getString("QUESTION"));
				question.setAnswer(rs.getString("ANSWER"));
				exam.addQuestion(question);
				idx++;
			}
			
			ps = conn.prepareStatement(sql.getExampleQuery());
			ps.setString(1, examID);
			rs = ps.executeQuery();
			
			int seq = 1;
			int questionNo = 1;
			
			while (rs.next()) {
				questionNo = rs.getInt("QUESTIONNO");
				if (seq == questionNo) {
					example = new ArrayList<String>();
					seq++;
				}
				example.add(rs.getString("EXAMPLE"));
				Question q = exam.getQuestion(questionNo-1);
				q.setExampleList(example);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return exam;
	}
	
	/**
	 * 시험입력
	 * @param exam
	 */
	public void insertExam(Examination exam) {
		try {
			conn = DBConnector.getConnection();
			
			ps = conn.prepareStatement(sql.getExamIDDupCheckQuery());
			ps.setString(1, exam.getExamID());
			rs = ps.executeQuery();
			
			int count = 0;
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			conn.setAutoCommit(false); //트랜잭션 처리
			
			if (count != 0) {
				ps = conn.prepareStatement(sql.getDeleteExampleQuery());
				ps.setString(1, exam.getExamID());
				ps.executeUpdate();
				
				ps = conn.prepareStatement(sql.getDeleteQuestionQuery());
				ps.setString(1, exam.getExamID());
				ps.executeUpdate();
				
				ps = conn.prepareStatement(sql.getDeleteExamQuery());
				ps.setString(1, exam.getExamID());
				ps.executeUpdate();
			}
			
			ps = conn.prepareStatement(sql.getInsertExamQuery());
			ps.setString(1, exam.getExamID());
			ps.setString(2, exam.getDescription());
			ps.setInt(3, exam.getTimelimit());
			ps.executeUpdate();
			
			ArrayList<Question> questionList = exam.getQuestionList();
			ArrayList<String> exampleList = null;
			
			for (int questionIdx = 0; questionIdx < questionList.size(); questionIdx++) {
				ps = conn.prepareStatement(sql.getInsertQuestionQuery());
				ps.setInt(1, questionIdx + 1);
				ps.setString(2, exam.getExamID());
				ps.setString(3, questionList.get(questionIdx).getQuestion());
				ps.setString(4, questionList.get(questionIdx).getAnswer());
				ps.executeUpdate();
				
				exampleList = questionList.get(questionIdx).getExampleList();
				for (int exampleIdx = 0; exampleIdx < exampleList.size(); exampleIdx++) {
					ps = conn.prepareStatement(sql.getInsertExampleQuery());
					ps.setInt(1, exampleIdx + 1);
					ps.setInt(2, questionIdx + 1);
					ps.setString(3, exam.getExamID());
					ps.setString(4, exampleList.get(exampleIdx));
					ps.executeUpdate();
				}
			}
			
			conn.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}
	
	/**
	 * 사용된 자원을 반환한다.
	 */
	public void closeAll() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
