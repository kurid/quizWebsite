package Question;

public class MatchingQuestion extends Question {
	
	public MatchingQuestion(int index, String questionText, CorrectAnswer correctAsnwer, int score){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAsnwer;
		this.score = score;
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
		return MATCHING;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	
	@Override
	public int getScore() {
		return score;
	}

}
