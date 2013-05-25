package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MyDB {
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "1234";
	private static final String MYSQL_DATABASE_SERVER = "localhost";
	private static final String MYSQL_DATABASE_NAME = "quizWebsite";
	
	private static Connection con;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/" + MYSQL_DATABASE_NAME;
			con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("CS108 student: Update the MySQL constants to correct values!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("CS108 student: Add the MySQL jar file to your build path!");
		}
	}

	
	public List getFriends(int id){
		//TODO:
		return null;
	}
	
	public String getName(int id){
		//TODO:
		return null;
	}	
	
	
	public String getSurname(int id){
		//TODO:
		return null;
	}
		
	public String getNickName(int id){
		//TODO:
		return null;
	}
	
	
	/*
	 * abrunebs marto ricxvebs
	 */
	public int[] getNotifications(){
		//TODO:
		return null;
	}
	
	
	public List getChallenges(int id){
		//TODO:
		return null;	
	}
	
	
	public List getMassages(int id){
		//TODO:
		return null;
	}
	
	
	public List getRequest(int id){
		//TODO:
		return null;	
	}
	
	public void sendMassage(int id1, int id2, String text){
		//TODO:		
	}
	
	
	public void sendFriendRequest(int id1, int id2){
		//TODO:
	}
	
	public void sendChallenge(int id1, int id2, int quizId){
		//TODO:
	}
	
	
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}


