package web;

import java.util.Date;

public class Notification implements NotificationInterface{
	
	protected int senderID, recieverID;
	protected Date date;
	
	@Override
	public int sender() {
		return senderID;
	}

	@Override
	public int reciever() {
		return recieverID;
	}

	@Override
	public Date date() {
		return date;
	}

}
