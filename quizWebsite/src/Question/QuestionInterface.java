package Question;

public interface QuestionInterface{
	/** returns index of the question.*/
	int getIndex();
	
	/** returns question text.*/
	String getQuestionText();

	/** returns type of the question.*/
	int getType();
	
	/** returns correct answer of the question.*/
	CorrectAnswer getCorrectAnswer();
	
	/** returns score of the question*/
	int getScore();
	
	/** adds question to database*/
	int addToDB();
	
	/** returns jsp name of the question.*/
	String getJspName();
	
	/** checks if received answer is correct, or not.*/
	int checkAnswer(ReceivedAnswer answer);
}
