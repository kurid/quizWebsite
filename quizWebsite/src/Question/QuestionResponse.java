package Question;

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
		return QUESTION_RESPONCE;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int addToDB() {
		return MyDB.addQuestionResponse(this);
	}
}