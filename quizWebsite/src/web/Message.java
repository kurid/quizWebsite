package web;

import java.util.Date;

public class Message extends Notification{
	
	String text;
	
	public Message(int to, int from, Date date, String text){
		senderID = from;
		recieverID = to;
		this.date = date;
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
}
