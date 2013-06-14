package web;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountManagerTest {
	private static MessageDigest messageDigest;

	@Test
	public void testAddAccount1() {
		AccountManager acc = new AccountManager();
		
		int a = acc.addAccount("", "as", "as", "pass", "kurid11@freeuni.edu.ge");
		assertEquals(Manager.EMPTY_FIELD, a);

		a = acc.addAccount("aa", "as", "as", "pass", "kurid11@freeun");
		assertEquals(Manager.INCORRECT_MAIL, a);
	}

	public void testAddAccount2() {
		AccountManager acc = new AccountManager();
		
		acc.addAccount("aa", "as", "as", "pass", "kurid11@freeuni.edu.ge");
		int a = acc.addAccount("aasss", "vinme", "vinme", "pass","kurid11@freeuni.edu.ge");
		assertEquals(Manager.MAIL_EXISTS, a);
		acc.deleteAccount("as");

		acc.addAccount("aa", "as", "as", "pass", "kurid11@freeuni.edu.ge");
		a = acc.addAccount("aasss", "vinme", "as", "pass","kurid@freeuni.edu.ge");
		assertEquals(Manager.NICKNAME_EXISTS, a);
		acc.deleteAccount("as");
	}
	
	public void testAddAccount3() {
		AccountManager acc = new AccountManager();
		
		int a = acc.addAccount("aasss", "vinme", "v", "pass","kurid11@freeuni.edu.ge");
		assertEquals(Manager.SHORT_NICKNAME, a);

		a = acc.addAccount("aasss", "vinme", "aassaassaassaassaas", "pass","kurid@freeuni.edu.ge");
		assertEquals(Manager.LONG_NICKNAME, a);
	}

	@Test
	public void testPasswordIsCorrect() {
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		AccountManager acc = new AccountManager();
		int a = acc.addAccount("name", "surname", "nick", "pass", "kurid11@freeuni.edu.ge");
		String password = "pass";
		messageDigest.update(password.getBytes());
		String passwordHash = AccountManager.hexToString(messageDigest.digest());
		assertEquals(passwordHash, MyDB.getPassword(a));
		acc.deleteAccount("nick");
	}

}
