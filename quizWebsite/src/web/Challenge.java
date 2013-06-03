package web;

import java.util.Date;

public class Challenge extends Notification{
	
	private int quizID;
	
	public Challenge(int to, int from, Date date, int quizID){
		senderID = from;
		recieverID = to;
		this.date = date;
		this.quizID = quizID;
	}
	
	public int getQuizID(){
		return quizID;
	}

}
