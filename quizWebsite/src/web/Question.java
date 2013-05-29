package web;

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

}
