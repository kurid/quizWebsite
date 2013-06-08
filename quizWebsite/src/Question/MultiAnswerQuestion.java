package Question;

import web.MyDB;

public class MultiAnswerQuestion extends Question{

	public MultiAnswerQuestion(int index, String questionText, CorrectAnswer correctAnswer, int score){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
		this.score = score;
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
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	
	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public void addToDB() {
		MyDB.addMultiAnswerQuestion(this);
	}
}
