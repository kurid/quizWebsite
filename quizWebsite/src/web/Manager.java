package web;

public interface Manager {

	public boolean isCoreckt(String nickName, String Password);
	
	public int addAccount(String name, String surname, String nickName, String Password, String mail);
}
