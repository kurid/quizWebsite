package web;

import java.util.List;
import java.util.Set;

public interface QuestionInterface {
	public static final int QUESTION_RESPONCE = 1;
	public static final int MULTIPLE_CHOICE = 2;
	public static final int MULTIPLE_ANSWER = 3;
	public static final int MCMA = 4;
	public static final int IMAGE = 5;
	public static final int MATCHING = 6;
	public static final int AUTO_GENERATED = 7;
	
	int getIndex();
	
	String getQuestionText();
	
	int getType();
	
	List<CorrectAnswer> getCorrectAnswer();
}
