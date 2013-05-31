package web;

public class Account {
	
	private int id;
	private String name, surname, nickname;
	
	public Account(int id){
		this.id = id;
		name = MyDB.getName(id);
		surname = MyDB.getSurname(id);
		nickname = MyDB.getNickName(id);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getNickname() {
		return nickname;
	}
		
	
}
