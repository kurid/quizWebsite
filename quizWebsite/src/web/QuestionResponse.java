package web;

import java.util.Set;

public class QuestionResponse extends Question
		implements MultipleAnswerQuestion{

	
	Set<String> correctAnswers;
	
	public QuestionResponse(int index, String questionText, Set<String> correctAnswers){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswers = correctAnswers;
	}
	
	
	@Override
	public Set<String> getCorrectAnswers() {
		return correctAnswers;
	}

}
