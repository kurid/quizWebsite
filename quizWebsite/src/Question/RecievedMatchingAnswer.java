package Question;

import java.util.List;

public class RecievedMatchingAnswer extends RecievedAnswer{
	
	private List<List<String>> userAnswersList; 
	
	public RecievedMatchingAnswer(List<List<String>> answerList){
		userAnswersList = answerList;
	}
	
	@Override
	public List<List<String>> getRecievedAnswer(){
		return userAnswersList;
	}
	
}
