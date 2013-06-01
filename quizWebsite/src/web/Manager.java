package web;

public interface Manager {

	public static final int NICKNAME_EXISTS = -2;
	public static final int INCORRECT_MAIL = -3;
	public static final int MAIL_EXISTS = -4;

	// amowmebs sworia tu ara sheyvanili nickname da password
	public boolean isCorrect(String nickName, String Password);
	
	/*
	 * ambrunebs int-s imis shesabamisad ra 
	 * shedegit dasrulda account-is sheqmis mcdeloboba
	 */
	public int addAccount(String name, String surname, String nickName, String password, String mail);
	
	public void deleteAccount(String nickName);
	
}
