package Question;

import java.util.List;

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
