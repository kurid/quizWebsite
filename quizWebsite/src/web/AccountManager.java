package web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author User Kurid 
 * class witch implements Manager interface 
 * using data base
 *
 */
public class AccountManager implements Manager {

	private static MessageDigest messageDigest;

	
	/**
	 * 
	 * @param nickName nickName
	 * @return true if nickNameExist in data base and return false if it's not
	 */
	private boolean nickNameExist(String nickName) {
		return MyDB.nickNameExist(nickName);
	}

	
	
	/**
	 * 
	 * @param nickName nickName
	 * @param password password
	 * @return checks if users password is correct
	 * 
	 * uses SHA algorithm for hashing password 
	 */
	
	private boolean passwordIsCorrect(String nickName, String password) {
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(password.getBytes());
		String passwordHash = hexToString(messageDigest.digest());
		String realPass = MyDB.getPassword(MyDB.getId(nickName));
		return realPass.equals(passwordHash);
	}

	
	
	/**
	 * checks if password and nickName pare is correct
	 */
	@Override
	public boolean isCorrect(String nickName, String password) {
		return nickNameExist(nickName) && passwordIsCorrect(nickName, password);
	}
	
	
	
	/**
	 * @param name
	 * @param surName
	 * @param nickName
	 * @param password
	 * @param mail
	 * @return tries adding new account in data base returns int according the result
	 */
	private int addInDataBase(String name, String surName, String nickName,
			String password, String mail) {
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(password.getBytes());
		String passwordHash = hexToString(messageDigest.digest());
		MyDB.addAccount(name, surName, nickName, passwordHash, mail);
		return MyDB.getId(nickName);
	}

	
	/**
	 * Given a byte[] array, produces a hex String, such as "234a6f". with 2
	 * chars for each byte in the array. (provided code)
	 **/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	
	
	/**
	 * 
	 * @param mail mail
	 * @return checks if such mail already exist
	 */
	private boolean mailExists(String mail) {
		return MyDB.mailExist(mail);
	}

	
	
	/**
	 * 
	 * @param mail mail
	 * @return true or false, check if mail is valid or not  
	 */
	private boolean mailIsValid(String mail) {
		return new EmailValidator().emailIsValid(mail);
	}

	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @param nickName
	 * @param password
	 * @param mail
	 * @return checks if one of the filed is Empty 
	 */
	private boolean filedIsEmpty(String name, String surname, String nickName,
			String password, String mail) {
		return name.length() == 0 || surname.length() == 0
				|| nickName.length() == 0 || password.length() == 0
				|| mail.length() == 0;
	}

	
	
	/**
	 * adding new account, returns error code if it's impossible for some reason
	 */
	@Override
	public int addAccount(String name, String surname, String nickName,
			String Password, String mail) {
		if (filedIsEmpty(name, surname, nickName, Password, mail))return EMPTY_FIELD;
		if (!mailIsValid(mail))	 return INCORRECT_MAIL;
		if (nickNameExist(nickName)) return NICKNAME_EXISTS;
		if (mailExists(mail)) return MAIL_EXISTS;
		if(nickName.length() < 2) return SHORT_NICKNAME;
		if(nickName.length() >= 16) return LONG_NICKNAME;

		return addInDataBase(name, surname, nickName, Password, mail);
	}

	
	/**
	 * Delete Account
	 */
	@Override
	public void deleteAccount(String nickName) {
		MyDB.deleteAccount(nickName);
	}

}