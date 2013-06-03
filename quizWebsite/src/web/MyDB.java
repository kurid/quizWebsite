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
	public static int[] getNotifications(int id) {
		int[] arr = new int[3];
		arr[0] = getNotificationCount(id, "messages");
		arr[1] = getNotificationCount(id, "friendRequests");
		arr[2] = getNotificationCount(id, "challenges");
		return arr;
	}
	
	private static int getNotificationCount(int id, String table){
		int count = 0;
		try {
			ResultSet rs = 
					statement.executeQuery
					("SELECT COUNT(*) FROM "+ table + " WHERE accountIdTo = " + id + ";");
			rs.next();
			count = Integer.parseInt(rs.getString(1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
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
			String query = "INSERT INTO messages (accountIdTo,accountIdFrom,text,read_unread ,sendTime )VALUES("
					+ idTo
					+ ","
					+ idFrom
					+ ",\""
					+ text
					+ "\",false,\"" + date + "\");";
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public static int createQuiz(String name, String description, int accountId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		String query = "INSERT INTO quizes(authorID,name,quiz_date,description) values(" + accountId + ",\"" + name + "\",\""  + date + "\",\"" + description + "\" );" ;
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getQuizId(name, accountId);
	}

	
	
	
	
	public static int getQuizId(String name, int accountId) {
		try {
			res = statement.executeQuery("SELECT quizID from quizes" +
								" where name = \"askc\" and authorID = 1; ");
			if(res.next()){
			return res.getInt(1);
			}else return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}


	
	
	public void sendFriendRequest(int id1, int id2) {
		// TODO:
	}

	public static void sendChallenge(int idTo, int idFrom, int quizId) {

		try {
			String query = "INSERT INTO challenges (accountIdTo,accountIdFrom,quizID )VALUES("
					+ idTo + "," + idFrom + "," + quizId + ");";
			statement.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("shesabamisi id-is qvizi an accounti ar aris sheqmnili");
//			e.printStackTrace();
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