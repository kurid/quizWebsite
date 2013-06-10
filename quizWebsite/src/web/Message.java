package web;

import java.util.Date;

public class Message extends Notification{
	
	String text;
	boolean isUnread;
	
	public Message(int to, int from, Date date, String text, boolean isUnread){
		sender = new Account(from);
		recieverID = to;
		this.date = date;
		this.text = text;
		this.isUnread = isUnread;
	}
	
	public String getText(){
		return text;
	}
	
	public boolean isUnread(){
		return isUnread;
	}
}
