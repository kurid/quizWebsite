package web;

import java.util.List;
import java.util.Set;

public class Question implements QuestionInterface{
	protected int index;
	protected String questionText;
	
	
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
	public List<CorrectAnswer> getCorrectAnswer() {
		return null;
	}

}
