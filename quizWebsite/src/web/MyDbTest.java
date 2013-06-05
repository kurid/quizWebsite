package web;

import junit.framework.TestCase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class MyDbTest extends TestCase {
	
	
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
	
	public void testNewAccount() {
		String name = "vaska";
		String surName = "kutuxovi";
		String nickName = "vaska19";
		String password = "123456";
		String mail = "vaska12@kvernadzeuni.edu.ge";
		MyDB.addAccount(name, surName, nickName, password, mail);
		assertEquals(true, MyDB.nickNameExist(nickName));
		MyDB.deleteAccount(nickName);
	}
	
	public void testNewAccount2() {
		assertEquals(false, MyDB.nickNameExist("Yoda"));
	}
	
	public void testMail() {
		String name = "vaska";
		String surName = "kutuxovi";
		String nickName = "vaska19";
		String password = "123456";
		String mail = "vaska12@kvernadzeuni.edu.ge";
		MyDB.addAccount(name, surName, nickName, password, mail);
		assertEquals(true, MyDB.mailExist(mail));
		MyDB.deleteAccount(nickName);
	}
	
	public void testGetter() {
		String name = "vaska";
		String surName = "kutuxovi";
		String nickName = "vaska19";
		String password = "123456";
		String mail = "vaska12@kvernadzeuni.edu.ge";
		MyDB.addAccount(name, surName, nickName, password, mail);
		int id = MyDB.getId(nickName);
		assertEquals(name, MyDB.getName(id));
		assertEquals(surName, MyDB.getSurname(id));
		assertEquals(nickName, MyDB.getNickName(id));
		assertEquals(mail, MyDB.getMail(id));
		MyDB.deleteAccount(nickName);
	}
	
	public void testSendMessage(){
		MyDB.addAccount("Deidara", "surname1", "nickName1", "password1", "mail1@gmail.com");
		MyDB.addAccount("Sasori", "surname2", "nickName2", "password2", "mail2@gmail.com");
		int id1 = MyDB.getId("nickName1");
		int id2 = MyDB.getId("nickName2");
		MyDB.sendMessage(id2, id1, "Hello, my man Sasori!");
		MyDB.sendMessage(id1, id2, "Where have you been Deidara?");
		MyDB.sendMessage(id2, id1, "Demonstrating art, Sasori my man!");
		assertEquals(1, MyDB.getNotifications(id1)[0]);
		assertEquals(2, MyDB.getNotifications(id2)[0]);
		MyDB.deleteAccount("nickName1");
		MyDB.deleteAccount("nickName2");
	}
	
	public void testPasswordHash(){
		String name = "Kakashi";
		String surName = "Hatake";
		String nickName = "CopyNinja";
		String password = "123456";
		String mail = "khatake@mail.ko";
		Manager mng = new AccountManager();
		mng.addAccount(name, surName, nickName, password, mail);
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update(password.getBytes());
			String passwordHash = hexToString(messageDigest.digest());
			String realPass = MyDB.getPassword(MyDB.getId(nickName));
			assertEquals(realPass, passwordHash);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error");
		}
		MyDB.deleteAccount(nickName);
	}
	
	
	public void testGetMessages(){
		MyDB.addAccount("Itachi", "Uchiha", "nickName1", "password1", "mail1@gmail.com");
		MyDB.addAccount("Sasuke", "Uchiha", "nickName2", "password2", "mail2@gmail.com");
		int itachi = MyDB.getId("nickName1");
		int sasuke = MyDB.getId("nickName2");
		String message = "Forgive me Sasuke… …It ends with this.";
		MyDB.sendMessage(sasuke, itachi, message);
		List<Message> chat = MyDB.getMessages(sasuke);
		assertEquals(1, chat.size());
		assertTrue(chat.get(0).getText().equals(message));
		MyDB.deleteAccount("nickName1");
		MyDB.deleteAccount("nickName2");
	}
	
	public void testAddAndGetFriend(){
		MyDB.addAccount("Shikamaru", "Nara", "nickName1", "password1", "mail1@gmail.com");
		MyDB.addAccount("Choji", "Akimichi", "nickName2", "password2", "mail2@gmail.com");
		MyDB.addAccount("Ino", "Yamanaka", "nickName3", "password3", "mail3@gmail.com");
		int shika = MyDB.getId("nickName1");
		int cho = MyDB.getId("nickName2");
		int ino =  MyDB.getId("nickName3");
		MyDB.addFriend(shika, cho);
		MyDB.addFriend(ino, shika);
		assertEquals(2,  MyDB.getFriends(shika).size());
		assertEquals(1,  MyDB.getFriends(ino).size());
		assertEquals(1,  MyDB.getFriends(cho).size());
		MyDB.deleteAccount("nickName1");
		MyDB.deleteAccount("nickName2");
		MyDB.deleteAccount("nickName3");
	}
	
//	public void testGetURl(){
//		assertEquals("asd", MyDB.getURL(1));
//		MyDB.getMatching(1);
//	}
	
//	public void testCreateQuiz(){
//		MyDB.createQuiz("saxeli", "agwera", 1);
//		assertEquals(3,MyDB.getQuizId("askc", 1) );
//	}
}
