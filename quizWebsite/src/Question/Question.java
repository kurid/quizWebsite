package Question;

public class Question implements QuestionInterface{
	protected int index;
	protected String questionText;
	protected CorrectAnswer correctAnswer;
	
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
		return 0;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return null;
	}

}
