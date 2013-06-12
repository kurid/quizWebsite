package Question;

public class RecievedSingleAnswer extends RecievedAnswer{
	private String recivedAnswer;
	
	public RecievedSingleAnswer(String recivedAnswer){
		this.recivedAnswer = recivedAnswer;
	}
	
	@Override
	public String getRecievedAnswer() {
		return recivedAnswer;
	}
	
}
