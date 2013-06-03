package Question;

import java.util.Set;

public class MultipleChoiceQuestion extends Question 
	implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	
	public MultipleChoiceQuestion(int index, String questionText,
			Set<String> possibleAnswers, CorrectAnswer correctAnswer, int score){
		this.questionText = questionText;
		this.index = index;
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
		this.score = score;
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
	
	@Override
	public int getScore() {
		return score;
	}

}
