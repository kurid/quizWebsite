package Question;

public interface QuestionInterface{
	int getIndex();
	
	String getQuestionText();
	
	int getType();
	
	CorrectAnswer getCorrectAnswer();
	
	int getScore();
	
	int addToDB();
	
	String getJspName();
}
