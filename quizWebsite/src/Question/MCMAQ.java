package Question;

import java.util.Set;

public class MCMAQ extends Question implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	
	public MCMAQ(int index, String questionText, Set<String> possibleAnswers,
				CorrectAnswer correctAnswer, int score){
		this.questionText = questionText;
		this.index = index;
		this.correctAnswer = correctAnswer;
		this.possibleAnswers = possibleAnswers;
		this.score = score;
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
	public int getScore() {
		return score;
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
