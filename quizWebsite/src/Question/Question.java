package Question;

public class Question implements QuestionInterface, Finals {
	protected int index;
	protected int score;
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

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public int addToDB() {
		return 0;
	}

	@Override
	public String getJspName() {
		return null;
	}
	
	@Override
	public int checkAnswer(RecievedAnswer answer){
		return 0;
	}

}
