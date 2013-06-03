package Question;

import java.util.Set;

public class MCMAQ extends Question implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	
	public MCMAQ(int index, String questionText, Set<String> possibleAnswers,
				CorrectAnswer correctAnswer){
		this.questionText = questionText;
		this.index = index;
		this.correctAnswer = correctAnswer;
		this.possibleAnswers = possibleAnswers;
	}
	
	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
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
		return MCMA;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}

}
