package web;

import java.util.HashSet;
import java.util.Set;

public class MultipleChoiceQuestion extends Question 
	implements SingleAnswerQuestion, ChooseQuestion{
	
	private Set<String> possibleAnswers;
	private String correctAnswer;
	
	public MultipleChoiceQuestion(String questionText, int index,
			Set<String> possibleAnswers, String correctAnswer){
		this.questionText = questionText;
		this.index = index;
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
}
