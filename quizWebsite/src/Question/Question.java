package Question;

/**
 * parent question class.
 * every single question type extends this class. 
 */

public class Question implements QuestionInterface, Finals {
	
	/** It's index of the question in the quiz.*/
	protected int index;
	/** It's the score of this question in the quiz.*/
	protected int score;
	/** It's question text*/
	protected String questionText;
	/** correct answer of the question.*/
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
	public int checkAnswer(ReceivedAnswer answer){
		return 0;
	}

}
