package web;

public interface Manager {

	public static final int EMPTY_FIELD = -1;
	public static final int NICKNAME_EXISTS = -2;
	public static final int INCORRECT_MAIL = -3;
	public static final int MAIL_EXISTS = -4;
	public static final int SHORT_NICKNAME = -5;
	public static final int LONG_NICKNAME = -6;

	/**
	 * checks if password and nickName pare is correct
	 */
	public boolean isCorrect(String nickName, String Password);
	
	
	/**
	 * adding new account, returns error code if it's impossible for some reason
	 */
	public int addAccount(String name, String surname, String nickName, String password, String mail);
	
	
	/**
	 * Delete Account
	 */
	public void deleteAccount(String nickName);
	
}
