package Question;

import java.util.ArrayList;
import java.util.List;

public class ImageQuestion extends Question{
	
	private String URL;
	
	public ImageQuestion(int index, String questionText, String URL, CorrectAnswer correctAnswer){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
		this.URL = URL;
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
		return 0;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return null;
	}

}
