package Question;

import java.util.List;
import java.util.Set;

import web.MyDB;

public class MultipleChoiceQuestion extends Question 
	implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	
	public MultipleChoiceQuestion(int index, String questionText,
			Set<String> possibleAnswers, CorrectAnswer correctAnswer, int score){
		this.questionText = questionText;
		this.index = index;
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
		this.score = score;
	}
	
	@Override
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	@Override
	public int getType() {
		return MULTIPLE_CHOICE;
	}
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int addToDB() {
		return MyDB.addMultipleChoiceQuestion(this);
	}
	
	@Override
	public String getJspName() {
		return MULTIPLE_CHOICE_JSP;
	}
	
	@Override
	public int checkAnswer(RecievedAnswer answer) {
		SingleAnswer sa = (SingleAnswer)correctAnswer;
		String correctAnswer = sa.getAnswer();
		String recieved = (String) answer.getRecievedAnswer();
		if(correctAnswer.equals(recieved))
			return score;
		return 0;
	}
	
}
