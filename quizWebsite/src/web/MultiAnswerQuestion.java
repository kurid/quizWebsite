package web;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiAnswerQuestion extends Question{

	List<CorrectAnswer> correctAnswer;
	
	public MultiAnswerQuestion(int index, String questionText, List<CorrectAnswer> correctAnswer){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
	}
	
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
		return MULTIPLE_ANSWER;
	}

	@Override
	public List<CorrectAnswer> getCorrectAnswer() {
		return correctAnswer;
	}
	
}
