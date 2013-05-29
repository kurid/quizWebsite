package web;

public class AccountManager implements Manager{
	private int id;
	
	public AccountManager() {
		// TODO Auto-generated constructor stub
	}

	private boolean nickNameExist(String nickName){
		return MyDB.nickNameExist(nickName);
	}
	
	private boolean passwordIsCorrect(String nickName, String password){
		String realPass = MyDB.getPassword(MyDB.getId(nickName));
		return realPass.equals(password);
	}
	
	@Override
	public boolean isCorrect(String nickName, String password) {
		return nickNameExist(nickName) && passwordIsCorrect(nickName, password);
	}
	
	private int addInDataBase(String name, String surName, String nickName,
			String password, String mail) {
		 MyDB.addAccount(name, surName, nickName, password, mail);
		 return MyDB.getId(nickName);
	}
	
	private boolean mailExists(String mail) {		
		return MyDB.mailExist(mail);
	}
	
	
	public static final int NICKNAME_EXISTS = -2;
	public static final int INCORRECT_MAIL = -3;
	public static final int MAIL_EXISTS = -4;

	@Override
	public int addAccount(String name, String surname, String nickName,
			String Password, String mail) {
		if(!mail.contains("@")) return INCORRECT_MAIL;
		if(nickNameExist(nickName)) return NICKNAME_EXISTS;
		if(mailExists(mail))return MAIL_EXISTS;
		
		return addInDataBase(name, surname, nickName, Password, mail);
	}

	

}
