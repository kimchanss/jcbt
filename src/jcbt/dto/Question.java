package jcbt.dto;

import java.util.ArrayList;

/**
 * �ϳ��� Question�� ���õ� �������� ������ ���� DTO
 * question, answer, Example �� ���� ArrayList�� ����
 * 
 * @author Son Minho
 */
public class Question {

	private String question = "";
	private String answer = "";
	private ArrayList<String> exampleList = new ArrayList<String>();
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public ArrayList<String> getExampleList() {
		return exampleList;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void setExampleList(ArrayList<String> exampleList) {
		this.exampleList = exampleList;
	}
	
	public boolean addExample(String example) {
		return exampleList.add(example);
	}
	
	public String getExample(int index) {
		if (index < exampleList.size()) {
			return exampleList.get(index);
		} else {
			return null;
		}
	}
	
	public int getExampleSize() {
		return exampleList.size();
	}
}
