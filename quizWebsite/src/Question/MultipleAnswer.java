package Question;

import java.util.List;

/**
 * correct answer type, which consists of several Strings.
 * for example MCMA, QuestionResponse etc.
 */

public class MultipleAnswer extends CorrectAnswer{
	
	private List<String> correctAnswer;
		
	public MultipleAnswer(List<String> correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public List<String> getAnswer(){
		return correctAnswer;
	}
	
}
