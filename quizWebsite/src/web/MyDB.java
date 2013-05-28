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
	}

	public static List<Integer> getFriends(int id) {
		List<Integer> friends = new ArrayList<Integer>();
		Statement stat;
		ResultSet res;
		try {
			stat = con.createStatement();
			res = stat
					.executeQuery("SELECT accountID2 FROM friendships where accountID1 = \""
							+ id + "\"");
			while (res.next()) {
				friends.add(res.getInt("accountID2"));
			}
			res = stat
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
		Statement stat;
		ResultSet res;
		String name = "";
		try {
			stat = con.createStatement();
			res = stat
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
		Statement stat;
		ResultSet res;
		String surname = "";
		try {
			stat = con.createStatement();
			res = stat
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

	public static String getNickName(int id) {
		Statement stat;
		ResultSet res;
		String nick = "";
		try {
			stat = con.createStatement();
			res = stat
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
		Statement stat;
		int res;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();		
		String date = sdf.format(utilDate);
		System.out.println(date);
		try {
			stat = con.createStatement();
			res = stat
					.executeUpdate("insert into massages (accountIdTo,accountIdFrom,text,read_unread ,sendTime )values("+idTo+","+idFrom+",\""+text+"\",false,\""+date+"\")");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendFriendRequest(int id1, int id2) {
		// TODO:
	}

	public void sendChallenge(int id1, int id2, int quizId) {
		// TODO:
	}

	public List getQuiz(int QuizId) {
		// TODO:
		return null;
	}

	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
