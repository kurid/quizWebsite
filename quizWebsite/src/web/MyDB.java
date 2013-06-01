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

	private static Connection connection;
	private static Statement statement;
	private static ResultSet res;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/"
					+ MYSQL_DATABASE_NAME;
			connection = DriverManager.getConnection(url, MYSQL_USERNAME,
					MYSQL_PASSWORD);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err
					.println("Update the MySQL constants to correct values!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err
					.println("Add the MySQL jar file to your build path!");
		}

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("connection can't create statement");
			e.printStackTrace();
		}
	}

	public static List<Integer> getFriends(int id) {
		List<Integer> friends = new ArrayList<Integer>();

		try {
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
		String name = "";
		try {
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
		String surname = "";
		try {
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

	public static String getNickName(int id) {
		String nick = "";
		try {
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

	public static boolean nickNameExist(String nickName) {
		boolean b = false;
		try {
			res = statement
					.executeQuery("SELECT * FROM accounts where nick = \""
							+ nickName + "\"");
			b = res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public static boolean mailExist(String mail) {
		boolean b = false;
		try {
			res = statement
					.executeQuery("SELECT * FROM accounts where mail = \""
							+ mail + "\"");
			b = res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	
	public static void deleteAccount(String nickName){
		try {
			statement.executeUpdate("delete from accounts where nick = \""  + nickName + "\";");					
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public List getMessages(int id) {
		// TODO:
		return null;
	}

	public List getRequest(int id) {
		// TODO:
		return null;
	}

	public static void sendMessage(int idTo, int idFrom, String text) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		try {
			statement
					.executeUpdate("insert into messages (accountIdTo,accountIdFrom,text,read_unread ,sendTime )values("
							+ idTo
							+ ","
							+ idFrom
							+ ",\""
							+ text
							+ "\",false,\"" + date + "\")");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendFriendRequest(int id1, int id2) {
		// TODO:
	}

	public static void sendChallenge(int idTo, int idFrom, int quizId) {

		try {
			statement
					.executeUpdate("insert into challenges (accountIdTo,accountIdFrom,quizID )values("
							+ idTo + "," + idFrom + "," + quizId + ")");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List getQuiz(int QuizId) {
		// TODO:
		return null;
	}

	public static int getId(String nickName) {
		int id = 0;
		try {
			res = statement
					.executeQuery("SELECT accountID FROM accounts where nick = \""
							+ nickName + "\"");
			res.next();
			id = res.getInt("accountID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public static String getPassword(int id) {
		String pass = "";
		try {
			// statement = con.createStatement();
			res = statement
					.executeQuery("SELECT password FROM accounts where accountID = \""
							+ id + "\"");
			res.next();
			pass = res.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass;
	}

	public static void addAccount(String name, String surName, String nickName,
			String password, String mail) {
		String Achievements = "Glexi";
		try {
			statement
					.executeUpdate("insert into accounts (nick,name,surname,password,mail,Achievements) values(\""
							+ nickName
							+ "\",\""
							+ name
							+ "\",\""
							+ surName
							+ "\",\""
							+ password
							+ "\",\""
							+ mail
							+ "\",\""
							+ Achievements + "\")");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}