package web;

import java.util.Date;


public class FriendRequest extends Notification{
	
	public FriendRequest(int to, int from, Date date){
		sender = from;
		recieverID = to;
		this.date = date;
	}
}
