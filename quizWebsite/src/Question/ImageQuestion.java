package Question;

import java.util.List;

import web.MyDB;

public class ImageQuestion extends Question{
	
	private String URL;
	
	public ImageQuestion(int index, String questionText, String URL, CorrectAnswer correctAnswer, int score){
		this.index = index;
		this.questionText = questionText;
		this.URL = URL;
		this.correctAnswer = correctAnswer;
		this.score = score;
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
		return IMAGE_QUESTION;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	
	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public int addToDB() {
		return MyDB.addImageQuestion(this);
	}
	
	
	@Override
	public String getJspName() {
		return IMAGE_QUESTION_JSP;
	}
	
	@Override
	public int checkAnswer(RecievedAnswer answer) {
		MultipleAnswer ma = (MultipleAnswer)correctAnswer;
		List<String> answers = ma.getAnswer();
		String recieved = (String) answer.getRecievedAnswer();
		if(answers.contains(recieved))
			return score;
		return 0;
	}
}
