package web;

import java.util.Date;

public class Notification implements NotificationInterface{
	
	protected int recieverID;
	protected Account sender;
	protected Date date;
	
	@Override
	public Account sender() {
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
