package com.song.entities;

public class QuestionBeen {
	private String questionid;
	private String question;
	
	public QuestionBeen() {
		super();
	}

	
	/**
	 * @return the questionid
	 */
	public String getQuestionid() {
		return questionid;
	}
	/**
	 * @param questionid the questionid to set
	 */
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}

	
	public String toString() {
		return "QuestionBeen [questionid=" + questionid + ", question=" + question + "]";
	}
	
}
