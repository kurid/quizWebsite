package Question;

import java.util.List;

import web.MyDB;

public class QuestionResponse extends Question{

	public QuestionResponse(int index, String questionText, CorrectAnswer correctAnswer, int score){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
		this.score = score;
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
		return QUESTION_RESPONSE;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int addToDB() {
		return MyDB.addQuestionResponse(this);
	}
	
	@Override
	public String getJspName() {
		return QUESTION_RESPONSE_JSP;
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