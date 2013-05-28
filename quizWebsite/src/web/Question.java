package web;

import java.util.ArrayList;

public interface Question {
	
	public void getQuestionText();
	public boolean checkAnswer(ArrayList<String> answers);
	public void getType(int id);
}
