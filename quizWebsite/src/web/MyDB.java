package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.CallableStatement;

public class MyDB {
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "1234";
	private static final String MYSQL_DATABASE_SERVER = "localhost";
	private static final String MYSQL_DATABASE_NAME = "quizWebsite";

	private static Connection connection;
	private static Statement statement;

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
		 ResultSet res;
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
			System.out.println("getFriends-is dros moxda shecdoma. query-s gashvebisas");
			e.printStackTrace();
		}
		return friends;
	}

	/*
	 * svadasxva type-ebis( name, surname, nickname...) geteri.
	 */
	private static String getter(int id, String type){
		String result = "";
		ResultSet res;
		try {
			String query = "SELECT "+type +" FROM accounts where accountID = \"" + id + "\"" ;
			res = statement.executeQuery(query);
			res.next();
			result = res.getString(type);
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (get" + type+ ")");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static String getName(int id) {
		return getter(id, "name");
	}

	public static String getSurname(int id) {
		return getter(id, "surname");
	}

	public static String getNickName(int id) {
		return getter(id, "nick");
	}
	
	public static String getPassword(int id) {
		
		return getter(id, "password");
	}
	
	public static String getMail(int id){
		return getter(id, "mail");
	}
	public static int getId(String nickName) {
		int id = 0;
		ResultSet res;
		try {
			String query = "SELECT accountID FROM accounts where nick = \"" + nickName + "\"";
			res = statement.executeQuery(query);
			res.next();
			id = res.getInt("accountID");
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (getId");
			e.printStackTrace();
		}
		return id;
	}
	
	
	private static boolean exist(String type, String stringToSearch){
		boolean b = false;
		ResultSet res;
		try {
			String query = "SELECT * FROM accounts where " + type + " = \"" + stringToSearch + "\" ;";
			res = statement .executeQuery(query);
			b = res.next();
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (exist" + type+ ")");
			e.printStackTrace();
		}
		return b;
	}
	
	public static boolean nickNameExist(String nickName) {
		return exist("nick", nickName);
	}

	public static boolean mailExist(String mail) {
		return exist("mail", mail);
	}

	
	public static void deleteAccount(String nickName){
		try {
			statement.executeUpdate("delete from accounts where nick = \""  + nickName + "\";");					
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (deleteAccount).");
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
		ResultSet res;
		try {
			String query = "SELECT COUNT(*) FROM "+ table + " WHERE accountIdTo = " + id + ";";
			res = statement.executeQuery(query);
			res.next();
			count = Integer.parseInt(res.getString(1));
		} catch (Exception e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (getNotifications).");
		}
		return count;
	}

	public static List<Challenge> getChallenges(int idTo) {
		String query = "select * from challenges where accountIdTo = " + idTo + ";";
		ResultSet res;
		List<Challenge> challanges = new ArrayList<Challenge>();
		try {
			res = statement.executeQuery(query);
			while(res.next()){
				challanges.add(new Challenge(idTo, res.getInt("accountIdFrom"), res.getDate("sendTime"), res.getInt("quizID")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challanges;
	}

	public static List<Message> getMessages(int idTo) {
		ResultSet res;
		String query = "select * from messages where accountIdTo = " + idTo + ";";
		List<Message> messages = new ArrayList<Message>();
		try {
			res = statement.executeQuery(query);
			while(res.next()){
				messages.add(new Message(idTo, res.getInt("accountIdFrom"), res.getDate("sendTime"), res.getString("text")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	public List<FriendRequest> getRequest(int idTo) {
		ResultSet res;
		String query = "select * from messages where accountIdTo = " + idTo + ";";
		List<FriendRequest> friendRequests = new ArrayList<FriendRequest>();
		try {
			res = statement.executeQuery(query);
			while(res.next()){
				friendRequests.add(new FriendRequest(idTo, res.getInt("accountIdFrom"), res.getDate("sendTime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendRequests;
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
			System.out.println( "query-s gashvebisas moxda shecdoma. (sendMessage).");
			e.printStackTrace();
			return;
		}
	}

	public static int createQuiz(String name, String description, int accountId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		String query = "INSERT INTO quizes(authorID,name,quiz_date,description) values(" 
				+ accountId + ",\"" + name + "\",\""  + date + "\",\"" + description + "\" );" ;
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (createQuiz).");
			e.printStackTrace();
		}
		return getQuizId(name, accountId);
	}

	
	public static int getQuizId(String name, int accountId) {
		ResultSet res;
		try {
			res = statement.executeQuery("SELECT quizID from quizes" +
								"where name = \"askc\" and authorID = 1; ");
			if(res.next()){
			return res.getInt(1);
			}else return 0;
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (getQuizId).");
			e.printStackTrace();
		}
		return 0;
	}


	public List getQuiz(int QuizId) {
		// TODO:
		return null;
	}
	
	public static ResultSet getQuestionInfo(int questionId){
		ResultSet res;
		String query ="select * from questions where questionID =" + questionId +";" ;
		try {
			res = statement.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet MultipleChoice(int questionId){
		ResultSet res;
		String query = "select * from multiplechoice where questionID =" + questionId + ";" ;
		try {
			res = statement.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet answers(int questionId){
		ResultSet res;
		String query = "select * from multiplechoice where questionID =" + questionId + ";" ;
		try {
			res = statement.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static ResultSet 
	
	public static void sendChallenge(int idTo, int idFrom, int quizId) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		try {
			String query = "INSERT INTO challenges (accountIdTo,accountIdFrom,quizID,sendDate )VALUES("
					+ idTo + "," + idFrom + "," + quizId + date +");";
			statement.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("shesabamisi id-is qvizi an accounti ar aris sheqmnili");
			e.printStackTrace();
		}

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
			System.out.println( "query-s gashvebisas moxda shecdoma. (addAccount).");
			e.printStackTrace();
		}
	}	
	
	
	public static void sendFriendRequest(int idTo, int idFrom) {
		String query = "INSERT INTO  friendrequests VALUES (" + idTo + "," + idFrom + ");";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getMatching(int questionId){
		ResultSet res = null;
		String query ="select * from matching  where questionID = " + questionId +" ;";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res; 
	}
	
	public static String getURL(int questionId){
		ResultSet res = null;
		String query ="select url from imigeQuestion  where questionID = " + questionId +" ;";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			res.next();
			return res.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void addFriend(int id1, int id2){
		try {
			CallableStatement cs = (CallableStatement) connection.prepareCall("{call addFriend(?,?)}");
			cs.setInt(1, id1);
			cs.setInt(2, id2);
			cs.execute();
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
