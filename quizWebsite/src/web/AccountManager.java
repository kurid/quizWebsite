package web;

public class AccountManager implements Manager{
	private int id;
	
	public AccountManager() {
		// TODO Auto-generated constructor stub
	}

	private boolean nickNameExist(String nickName){
		//TODO:
		return false;
	}
	
	private boolean passwordIsCorrect(String nickName, String password){
		//TODO:
		return false;
	}
	
	@Override
	public boolean isCorrect(String nickName, String password) {
		return nickNameExist(nickName) && passwordIsCorrect(nickName, password);
	}
	
	private int addInDataBase(String name, String surname, String nickName,
			String Password, String mail) {
		return 0;
	}
	
	private boolean mailExists(String mail) {
		// TODO Auto-generated method stub
		return false;
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
