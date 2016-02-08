package com.pxy.studyhelper.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "FavoriteQuestion")
public class FavoriteQuestion {
	@Column(name = "id",isId = true,autoGen = true)
	public int id;
	@Column(name = "question")
	public String question;
	@Column(name = "answerA")
	public String answerA;
	@Column(name = "answerB")
	public String answerB;
	@Column(name = "answerC")
	public String answerC;
	@Column(name = "answerD")
	public String answerD;
	@Column(name = "answerE")
	public String answerE;
	@Column(name = "rightAnswer")
	public int rightAnswer;
	@Column(name = "explaination")
	public String explaination;
	@Column(name = "isWrong")
	public int  isWrong;
	@Column(name = "isFavorite")
	public int  isFavorite;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getAnswerE() {
		return answerE;
	}
	public void setAnswerE(String answerE) {
		this.answerE = answerE;
	}
	public int getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String getExplaination() {
		return explaination;
	}
	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}

	@Override
	public String toString() {
		return "FavoriteQuestion{" +
				"id=" + id +
				", question='" + question + '\'' +
				", answerA='" + answerA + '\'' +
				", answerB='" + answerB + '\'' +
				", answerC='" + answerC + '\'' +
				", answerD='" + answerD + '\'' +
				", answerE='" + answerE + '\'' +
				", rightAnswer=" + rightAnswer +
				", explaination='" + explaination + '\'' +
				", isWrong=" + isWrong +
				", isFavorite=" + isFavorite +
				'}';
	}
}
