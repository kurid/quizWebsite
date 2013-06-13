package web.Servlest;

import java.util.Date;

import web.Notification;

public class FriendRequest extends Notification{
	
	public FriendRequest(int to, int from, Date date){
		sender = from;
		recieverID = to;
		this.date = date;
	}
}
