package web;

import java.util.Date;
/**
 * 
 * @author User
 * notification class implements Notification Interface 
 * used data base
 */
public class Notification implements NotificationInterface{
	
	protected int recieverID;
	protected int sender;
	protected Date date;
	
	/**
	 * @ return sender ID
	 */
	@Override
	public int sender() {
		return sender;
	}
	
	/**
	 * @ return receiver ID
	 */
	@Override
	public int reciever() {
		return recieverID;
	}
	
	
	/**
	 * @ return notification send data
	 */
	@Override
	public Date date() {
		return date;
	}

}
