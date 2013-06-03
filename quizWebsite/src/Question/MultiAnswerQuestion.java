package Question;

public class MultiAnswerQuestion extends Question{

	public MultiAnswerQuestion(int index, String questionText, CorrectAnswer correctAnswer){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
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
		return MULTIPLE_ANSWER;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	
}
