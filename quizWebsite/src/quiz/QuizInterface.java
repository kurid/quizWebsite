package quiz;

import java.util.List;

import Question.Question;
/**
 * 
 * @author User 
 * Quiz interface
 */
public interface QuizInterface {

	public int getID();
	public String getName();
	public String getDescription();
	public int getAuthor();
	public List<Question> generateQuestions();
	public int getTotalScore();
}
