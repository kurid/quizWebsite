package web;

import java.util.Date;

public class Notification implements NotificationInterface{
	
	protected int recieverID;
	protected int sender;
	protected Date date;
	
	@Override
	public int sender() {
		return sender;
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
