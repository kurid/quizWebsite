package Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionResponse extends Question{

	public QuestionResponse(int index, String questionText, CorrectAnswer correctAnswer){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
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
		return QUESTION_RESPONCE;
	}

}