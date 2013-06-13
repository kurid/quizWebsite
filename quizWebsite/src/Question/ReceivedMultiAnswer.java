package Question;

import java.util.List;

/**
 * received answer with several strings.
 */

public class ReceivedMultiAnswer extends ReceivedAnswer{
	
	private List<String> userAnswersList;
	
	public ReceivedMultiAnswer(List<String> recievedAnswersList){
		userAnswersList = recievedAnswersList;
	}
	
	@Override
	public  List<String> getRecievedAnswer(){
		return userAnswersList;
	}
	
}
