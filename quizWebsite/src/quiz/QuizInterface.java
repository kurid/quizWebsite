package quiz;

import java.util.List;

import Question.Question;

public interface QuizInterface {

	public int getId();
	public String getName();
	public String getDescription();
	public int getAuthor();
	public List<Question> generateQuestions();
	public int getTotalScore();
}
