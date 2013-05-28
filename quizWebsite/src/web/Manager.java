package web;

public interface Manager {

	
	// amowmebs sworia tu ara sheyvanili nickname da password
	public boolean isCorrect(String nickName, String Password);
	
	/*
	 * ambrunebs int-s imis shesabamisad ra 
	 * shedegit dasrulda account-is sheqmis mcdeloboba
	 */
	public int addAccount(String name, String surname, String nickName, String password, String mail);
}
