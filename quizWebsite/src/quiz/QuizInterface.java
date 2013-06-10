package quiz;

import java.util.List;

import Question.Question;

public interface QuizInterface {

	public int getID();
	public String getName();
	public String getDescription();
	public int getAuthor();
	public List<Question> generateQuestions();
	public int getTotalScore();
}
