package Question;

public class MatchingQuestion extends Question {
	
	public MatchingQuestion(int index, String questionText, CorrectAnswer correctAsnwer){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAsnwer;
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

}
