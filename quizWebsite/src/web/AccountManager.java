package web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccountManager implements Manager {

	private int id;
	private static MessageDigest messageDigest;

	public AccountManager() {
		// TODO Auto-generated constructor stub
	}

	private boolean nickNameExist(String nickName) {
		return MyDB.nickNameExist(nickName);
	}

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

	@Override
	public boolean isCorrect(String nickName, String password) {
		return nickNameExist(nickName) && passwordIsCorrect(nickName, password);
	}

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

	/*
	 * Given a byte[] array, produces a hex String, such as "234a6f". with 2
	 * chars for each byte in the array. (provided code)
	 */
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

	private boolean mailExists(String mail) {
		return MyDB.mailExist(mail);
	}

	/*
	 * shegvidzlia davamatot damatebiti shemowmebebi imis dasadgenad maili
	 * validuria tu ara
	 */
	private boolean mailIsValid(String mail) {
		return mail.contains("@") && mail.indexOf("@") != 0;
	}

	private boolean filedIsEmpty(String name, String surname, String nickName,
			String password, String mail) {
		return name.length() == 0 || surname.length() == 0
				|| nickName.length() == 0 || password.length() == 0
				|| mail.length() == 0;
	}

	
	public static final int EMPTY_FIELD = -1;
	public static final int NICKNAME_EXISTS = -2;
	public static final int INCORRECT_MAIL = -3;
	public static final int MAIL_EXISTS = -4;
	public static final int SHORT_NICKNAME = -5;
	public static final int LONG_NICKNAME = -6;

	
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

	@Override
	public void deleteAccount(String nickName) {
		MyDB.deleteAccount(nickName);
	}

}