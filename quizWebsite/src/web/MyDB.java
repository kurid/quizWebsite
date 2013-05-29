package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDB {
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "1234";
	private static final String MYSQL_DATABASE_SERVER = "localhost";
	private static final String MYSQL_DATABASE_NAME = "quizWebsite";

	private static Connection con;
	private static Statement statement;
	private static String query;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/"
					+ MYSQL_DATABASE_NAME;
			con = DriverManager.getConnection(url, MYSQL_USERNAME,
					MYSQL_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err
					.println("CS108 student: Update the MySQL constants to correct values!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err
					.println("CS108 student: Add the MySQL jar file to your build path!");
		}
		
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Integer> getFriends(int id) {
		List<Integer> friends = new ArrayList<Integer>();
		ResultSet res;
		try {
			statement = con.createStatement();
			res = statement
					.executeQuery("SELECT accountID2 FROM friendships where accountID1 = \""
							+ id + "\"");
			while (res.next()) {
				friends.add(res.getInt("accountID2"));
			}
			res = statement
					.executeQuery("SELECT accountID1 FROM friendships where accountID2 = \""
							+ id + "\"");
			while (res.next()) {
				friends.add(res.getInt("accountID1"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friends;
	}

	public static String getName(int id) {
		ResultSet res;
		String name = "";
		try {
			statement = con.createStatement();
			res = statement
					.executeQuery("SELECT name FROM accounts where accountID = \""
							+ id + "\"");
			res.next();
			name = res.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	public static String getSurname(int id) {
		ResultSet res;
		String surname = "";
		try {
			statement = con.createStatement();
			res = statement
					.executeQuery("SELECT surname FROM accounts where accountID = \""
							+ id + "\"");
			res.next();
			surname = res.getString("surname");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return surname;
	}

	public static int getAccounId(String nick){
		
		//return ;		
	}
	
	public static String getNickName(int id) {
		ResultSet res;
		String nick = "";
		try {
			statement = con.createStatement();
			res = statement
					.executeQuery("SELECT nick FROM accounts where accountID = \""
							+ id + "\"");
			res.next();
			nick = res.getString("nick");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nick;
	}

	/*
	 * abrunebs marto ricxvebs
	 */
	public int[] getNotifications() {
		// TODO:
		return null;
	}

	public List getChallenges(int id) {
		// TODO:
		return null;
	}

	public List getMassages(int id) {
		// TODO:
		return null;
	}

	public List getRequest(int id) {
		// TODO:
		return null;
	}

	public static void sendMassage(int idTo, int idFrom, String text) {
		int res;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();		
		String date = sdf.format(utilDate);
		try {
			statement = con.createStatement();
			res = statement
					.executeUpdate("insert into massages (accountIdTo,accountIdFrom,text,read_unread ,sendTime )values("+idTo+","+idFrom+",\""+text+"\",false,\""+date+"\")");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendFriendRequest(int id1, int id2) {
		// TODO:
	}

	public static void sendChallenge(int idTo, int idFrom, int quizId) {
		int res;
		try {
			statement = con.createStatement();
			res = statement
					.executeUpdate("insert into challenges (accountIdTo,accountIdFrom,quizID )values("+idTo+","+idFrom+","+quizId+")");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List getQuiz(int QuizId) {
		// TODO:
		return null;
	}

	
	
	public void addAccount(String name, String surname, String nickName,
			String Password, String mail){
		//TODO:
	}
	
	
	public static boolean nicknameExist(String nickname){
		query = "select accountId from accounts where nick = \"" + nickname + "\" ;" ;
		ResultSet set = null;
		try {
			set = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("query is incorect.  method: nicknamExist");
		}
		System.out.println("set  " + set);
		try {
			return set.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
