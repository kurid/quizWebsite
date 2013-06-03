package Question;

public class ImageQuestion extends Question{
	
	private String URL;
	
	public ImageQuestion(int index, String questionText, String URL, CorrectAnswer correctAnswer){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
		this.URL = URL;
	}
	
	public String getURL(){
		return URL;
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
		return IMAGE;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}

}
