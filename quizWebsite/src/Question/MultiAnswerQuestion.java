package Question;

import java.util.ArrayList;
import java.util.List;

import web.MyDB;

/**
 * MultiAnswerQuestion is very similar to QuestionResponse.
 * only difference is that here may be several empty fields to fill, so
 * several correct answers are requested.
 */

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
	public int addToDB() {
		return MyDB.addMultiAnswerQuestion(this);
	}
	
	@Override
	public String getJspName() {
		return MULTIPLE_ANSWER_JSP;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int checkAnswer(RecievedAnswer answer){
		MMAnswer mma = (MMAnswer)correctAnswer;
		List<List<String> > correctAnswers = mma.getAnswer();
		List<String> recieved =  (List<String>) answer.getRecievedAnswer();
		recieved = filteredList(recieved);
		int resultScore = 0;
		for(int i = 0; i < recieved.size(); i++){
			if(currentAnswerIsCorrect(correctAnswers, recieved.get(i)))
				resultScore += score;
		}
		return resultScore;
	}

	private List<String>  filteredList(List<String> recieved) {
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < recieved.size(); i++){
			if(!result.contains(recieved.get(i)))
				result.add(recieved.get(i));
		}
		return result;
	}

	private boolean currentAnswerIsCorrect(List<List<String>> correctAnswers,
			String currentAnswer) {
		for(int i = 0; i < correctAnswers.size(); i++){
			if(correctAnswers.get(i).contains(currentAnswer)){
				correctAnswers.remove(i);
				return true;
			}
		}
		return false;
	}
}
