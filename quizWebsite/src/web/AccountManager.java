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
	
	private void addInDataBase(String name, String surname, String nickName,
			String Password, String mail) {
		// TODO Auto-generated method stub
		
	}
	
	private static final int successful_add = 1;
	private static final int already_exist = 2;
	private static final int mail_incorrect = 3; 

	@Override
	public int addAccount(String name, String surname, String nickName,
			String Password, String mail) {
		if(!mail.contains("@")) return mail_incorrect;
		if(nickNameExist(nickName)) return already_exist;
		addInDataBase(name, surname, nickName, Password, mail);
		return successful_add;
	}

}
