package Question;

import java.util.List;

/**
 * received answer which may be Matching, or Multiple answer question.
 */

public class ReceivedMatchingAnswer extends ReceivedAnswer{
	
	private List<List<String>> userAnswersList; 
	
	public ReceivedMatchingAnswer(List<List<String>> answerList){
		userAnswersList = answerList;
	}
	
	@Override
	public List<List<String>> getRecievedAnswer(){
		return userAnswersList;
	}
	
}
