package Question;

import java.util.List;

/**
 * correct answer, which consists of List<List<String> >
 * for example, MultiAnswer question and Matching.
 */

public class MMAnswer extends CorrectAnswer{
	
	private List<List<String> > correctAnswer;
	
	public MMAnswer(List<List<String> > correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public List<List<String> > getAnswer(){
		return correctAnswer;
	}

}
