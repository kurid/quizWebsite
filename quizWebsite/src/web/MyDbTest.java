package web;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyDbTest extends TestCase {

//	public void testGetFriends() {
//		// qveda shemowmeba shecdomas agdebs rato arvici .
//		// mydb.getfriends sworad abrunebs lists assertis bralia shecdoma
//		// assertEquals(Arrays.asList(new int[] { 2, 3 }),MyDB.getFriends(1) );
//	
//		assertEquals("saba9993", MyDB.getNickName(5));
//		assertEquals("saba", MyDB.getName(5));
//		assertEquals("gogolidze", MyDB.getSurname(5));
//		MyDB.sendMessage(1, 2, "a");
//		MyDB.sendChallenge(1, 2, 1);
//	}
	
	public void testCreateQuiz(){
		MyDB.createQuiz("saxeli", "agwera", 1);
		assertEquals(3,MyDB.getQuizId("askc", 1) );
	}
}
