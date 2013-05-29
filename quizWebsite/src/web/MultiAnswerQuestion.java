package web;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiAnswerQuestion extends Question implements SameAnswerQuestion{

	List< List<String> > correctAnswers;
	
	public MultiAnswerQuestion(int index, String questionText, List< List< String> > correctAnswers){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswers = correctAnswers;
		
	}
	
	@Override
	public List< List<String>> getCorrectAnswers() {
		return correctAnswers;
	}
	
	
}
