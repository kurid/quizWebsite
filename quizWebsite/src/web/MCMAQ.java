package web;

import java.util.HashSet;
import java.util.Set;

public class MCMAQ extends Question implements ChooseQuestion, MultipleAnswerQuestion{
	
	private Set<String> possibleAnswers;
	private Set<String> correctAnswers;
	
	public MCMAQ(int index, String questionText, Set<String> possibleAnswers,
				Set<String> correctAnswers){
		this.questionText = questionText;
		this.index = index;
		this.correctAnswers = correctAnswers;
		this.possibleAnswers = possibleAnswers;
	}
	
	
	@Override
	public Set<String> getCorrectAnswers() {
		return correctAnswers;
	}

	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
	}

}
