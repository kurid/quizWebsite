package web;

import java.util.Date;
/**
 * 
 * @author User
 *	massage class extends Notification
 */
public class Message extends Notification{
	
	String text;
	boolean isUnread;
	
	public Message(int to, int from, Date date, String text, boolean isUnread){
		sender = from;
		recieverID = to;
		this.date = date;
		this.text = text;
		this.isUnread = isUnread;
	}
	/**
	 * 
	 * @return massage text
	 */
	public String getText(){
		return text;
	}
	
	
	/**
	 * 
	 * @return is this massage read or not
	 */
	public boolean isUnread(){
		return isUnread;
	}
}
