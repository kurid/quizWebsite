package Question;

import java.util.Set;

public class MultipleChoiceQuestion extends Question 
	implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	
	public MultipleChoiceQuestion(String questionText, int index,
			Set<String> possibleAnswers, CorrectAnswer correctAnswer){
		this.questionText = questionText;
		this.index = index;
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	@Override
	public int getType() {
		return MULTIPLE_CHOICE;
	}

}
