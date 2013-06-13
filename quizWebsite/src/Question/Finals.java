package Question;

/**
 * constants for the entire project.
 * their names give more precise description about them.
 */
public interface Finals {
	
	public static final int NEWBIE = 1;
	public static final int QUIZ_SOLVER = 10;
	public static final int EXPERIENCED_SOLVER = 20;
	public static final int NOT_BAD = 50;
	
	public static final int QUESTION_RESPONSE = 1;
	public static final int MULTIPLE_CHOICE = 2;
	public static final int MULTIPLE_ANSWER = 3;
	public static final int MCMA = 4;
	public static final int IMAGE_QUESTION = 5;
	public static final int MATCHING = 6;
	public static final int AUTO_GENERATED = 7;	
	
	public static final String QUESTION_RESPONSE_JSP = "QuestionResponse.jsp";
	public static final String MULTIPLE_CHOICE_JSP = "MultipleChoice.jsp";
	public static final String MULTIPLE_ANSWER_JSP = "MultipleAnswer.jsp";
	public static final String MCMA_JSP = "MCMA.jsp";
	public static final String IMAGE_QUESTION_JSP = "ImageQuestion.jsp";
	public static final String MATCHING_JSP = "MatchingQuestion.jsp";
	public static final String AUTO_GENERATED_JSP = "AutoGeneratedQuestion.jsp";
	
	public static final int lowestScore = 1;
	public static final int highestScore = 10;
	public static final int minNumberOfQuestions = 2;
	public static final int maxNumberOfQuestions = 20;
	public static final int maxNumberOfMatching = 6;
}
