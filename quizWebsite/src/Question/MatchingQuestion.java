package Question;

import java.util.List;

import sun.reflect.generics.visitor.Reifier;
import web.MyDB;

public class MatchingQuestion extends Question {
	
	public MatchingQuestion(int index, String questionText, CorrectAnswer correctAsnwer, int score){
		this.index = index;
		this.questionText = questionText;
		this.correctAnswer = correctAsnwer;
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
		return MATCHING;
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
		return MyDB.addMatchingQuestion(this);
	}
	
	@Override
	public String getJspName() {
		return MATCHING_JSP;
	}
	
	@Override
	public int checkAnswer(RecievedAnswer answer){
		MMAnswer mma = (MMAnswer)correctAnswer;
		List<List<String> > correctAnswers = mma.getAnswer();
		List<List<String> > recieved = (List<List<String> >) answer.getRecievedAnswer();
		int finalScore = 0;
		for(int i = 0; i < recieved.size(); i++){
			if(pairIsCorrect(correctAnswers, recieved.get(i))){
				finalScore += 1;
			}
		}
		
		return finalScore;
	}

	private boolean pairIsCorrect(List<List<String>> correctAnswers,
			List<String> pair) {
		for(int i = 0; i < correctAnswers.size(); i++){
			if(correctAnswers.get(i).get(0).equals(pair.get(0)) 
					&& correctAnswers.get(i).get(1).equals(pair.get(1))){
				return true;
			}
		}
		return false;
	}
}
