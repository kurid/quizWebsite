package Question;

import java.util.List;
import java.util.ArrayList;

public class MultipleAnswer extends CorrectAnswer{
	
	private List<String> correctAnswers;
		
	public MultipleAnswer(ArrayList<String> correctAnswers){
		this.correctAnswers = correctAnswers;
	}
	
	@Override
	public List<String> getAnswer(){
		return correctAnswers;
	}
	
}
