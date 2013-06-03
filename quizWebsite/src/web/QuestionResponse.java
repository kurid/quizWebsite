package web;

import java.util.ArrayList;
import java.util.List;

public class QuestionResponse extends Question{
	
	List<CorrectAnswer> correctAnswers;
	
	public QuestionResponse(int index, String questionText, List<CorrectAnswer> correctAnswers){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswers = correctAnswers;
	}
	
	@Override
	public List<CorrectAnswer> getCorrectAnswer() {
		return correctAnswers;
	}
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public int getType() {
		return QUESTION_RESPONCE;
	}

}