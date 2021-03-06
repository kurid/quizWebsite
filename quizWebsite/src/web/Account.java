package web;

public class Account {
	
	private int id;
	private String name, surname, nickname;
	boolean isAdmin;
	public Account(int id){
		this.id = id;
		name = MyDB.getName(id);
		surname = MyDB.getSurname(id);
		nickname = MyDB.getNickName(id);
		isAdmin = MyDB.isAdmin(id);
	}
	/**
	 * 
	 * @return account id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return account name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return account surname
	 */
	public String getSurname() {
		return surname;
	}

	
	/**
	 * 
	 * @return account nickname
	 */
	public String getNickname() {
		return nickname;
	}
		
	public boolean isAdmin(){
		return isAdmin;
	}
	
}
