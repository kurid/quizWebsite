package Question;

import java.util.List;

public class RecievedMultiAnswer extends RecievedAnswer{
	
	private List<String> userAnswersList;
	
	public RecievedMultiAnswer(List<String> recievedAnswersList){
		userAnswersList = recievedAnswersList;
	}
	
	@Override
	public  List<String> getRecievedAnswer(){
		return userAnswersList;
	}
	
}
