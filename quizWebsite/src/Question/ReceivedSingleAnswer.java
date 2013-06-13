package Question;

/**
 * received answer with only one string.
 */
public class ReceivedSingleAnswer extends ReceivedAnswer{
	private String recivedAnswer;
	
	public ReceivedSingleAnswer(String recivedAnswer){
		this.recivedAnswer = recivedAnswer;
	}
	
	@Override
	public String getRecievedAnswer() {
		return recivedAnswer;
	}
	
}
