package Question;

/**
 * This is the type of correct answer, where may be only one correct answer.
 * for example Multiple Choice question.
 */

public class SingleAnswer extends CorrectAnswer{

	private String correctAnswer;
	
	public SingleAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String getAnswer(){
		return correctAnswer;
	}
	
}
