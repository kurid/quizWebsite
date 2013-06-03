package Question;

import java.util.List;
import java.util.ArrayList;

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
