package Question;

import java.util.List;
import java.util.Set;

import web.MyDB;

/**
 * This is Multiple Choice and Multiple Answer type question, where
 * there are several possible answers and from them may be several
 * correct answers.
 * That's why it implements ChooseQuestion and it's correctAnswer
 * is MultipleAnswer.
 */

public class MCMAQ extends Question implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	
	public MCMAQ(int index, String questionText, Set<String> possibleAnswers,
				CorrectAnswer correctAnswer, int score){
		this.questionText = questionText;
		this.index = index;
		this.correctAnswer = correctAnswer;
		this.possibleAnswers = possibleAnswers;
		this.score = score;
	}
	
	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
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
	public int getScore() {
		return score;
	}

	@Override
	public int getType() {
		return MCMA;
	}

	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	
	@Override
	public int addToDB() {
		return MyDB.addMCMAQuestion(this);
	}
	
	@Override
	public String getJspName() {
		return MCMA_JSP;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int checkAnswer(ReceivedAnswer answer){
		MultipleAnswer ma = (MultipleAnswer) correctAnswer;
		List<String> correctAnswers = ma.getAnswer();
		List<String> recieved = (List<String>) answer.getRecievedAnswer();
		int finalScore = 0;
		for(int i = 0; i < recieved.size(); i++){
			if(correctAnswers.contains(recieved.get(i)))
				finalScore += score;
		}
		return finalScore;
	}

}
