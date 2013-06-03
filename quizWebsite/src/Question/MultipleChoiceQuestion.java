package Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleChoiceQuestion extends Question 
	implements ChooseQuestion{
	
	private Set<String> possibleAnswers;
	private List<CorrectAnswer> answer;
	
	public MultipleChoiceQuestion(String questionText, int index,
			Set<String> possibleAnswers, CorrectAnswer answer){
		this.questionText = questionText;
		this.index = index;
		this.possibleAnswers = possibleAnswers;
		this.answer = new ArrayList<CorrectAnswer>();
		this.answer.add(answer);
	}
	
	@Override
	public List<CorrectAnswer> getCorrectAnswer() {
		return answer;
	}

	@Override
	public Set<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	@Override
	public int getType() {
		return MULTIPLE_CHOICE;
	}

}
