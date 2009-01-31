package jcbt.dto;

import java.util.ArrayList;

/**
 * �ϳ��� Exam�� ���õ� �������� ������ ���� DTO
 * examID, description, timelimit, Question �� ���� ArrayList�� ����
 * 
 * @author Son Minho
 */
public class Examination {

	private String examID = "";
	private String description = "";
	private int timelimit = 0;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	
	public String getExamID() {
		return examID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getTimelimit() {
		return timelimit;
	}
	
	public ArrayList<Question> getQuestionList() {
		return questionList;
	}
	
	public void setExamID(String examID) {
		this.examID = examID;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTimelimit(int timelimit) {
		this.timelimit = timelimit;
	}
	
	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}
	
	public boolean addQuestion(Question question) {
		return questionList.add(question);
	}
	
	public Question getQuestion(int index) {
		if (index < questionList.size()) {
			return questionList.get(index);
		} else {
			return null;
		}
	}
	
	public int getQuestionSize() {
		return questionList.size();
	}
}
