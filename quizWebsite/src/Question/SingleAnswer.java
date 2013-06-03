package Question;

public class SingleAnswer extends CorrectAnswer{

	private String correctAnswer;
	
	public SingleAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String getAnswer(){
		return correctAnswer;
	}
	
}
