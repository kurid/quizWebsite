package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Question.*;

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

	public static ResultSet newQuizzes(){
		ResultSet res = null;
		try {
			res  = statement.executeQuery("SELECT * FROM quizes ORDER BY quiz_date LIMIT 0,5;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static ResultSet popularQuizzes(){
		ResultSet res = null;
		try {
			String query = "SELECT authorID, name,description FROM quizes, popularQuizes " +
					" WHERE quizes.quizID = popularQuizes.quizID " +
					" ORDER BY count DESC;";
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
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
		String query = "SELECT * FROM challenges WHERE accountIdTo = " + idTo + ";";
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
		String query = "SELECT * FROM messages WHERE accountIdTo = " + idTo + ";";
		List<Message> messages = new ArrayList<Message>();
		try {
			res = statement.executeQuery(query);
			while(res.next()){
				messages.add(new Message(idTo, res.getInt("accountIdFrom"), 
						res.getDate("sendTime"), res.getString("text"), res.getBoolean("read_unread")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	public static List<FriendRequest> getRequest(int idTo) {
		ResultSet res;
		String query = "SELECT * FROM messages WHERE accountIdTo = " + idTo + ";";
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
		String date = getCurrentTime();
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

	public static int createQuiz(String name, String description, int accountID){
		String date = getCurrentTime();
		String query = "INSERT INTO quizes(authorID,name,quiz_date,description) values(" 
				+ accountID + ",\"" + name + "\",\""  + date + "\",\"" + description + "\" );" ;
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (createQuiz).");
			e.printStackTrace();
		}
		return getQuizId(name, accountID);
	}

	
	public static int getQuizId(String name, int accountID) {
		ResultSet res;
		try {
			res = statement.executeQuery("SELECT quizID FROM quizes " +
								"WHERE name = \"" + name + "\" AND authorID = " + accountID + "; ");
			if (res.next()) 
				return res.getInt(1);
			else return 0;
		} catch (SQLException e) {
			System.out.println( "query-s gashvebisas moxda shecdoma. (getQuizId).");
			e.printStackTrace();
		}
		return 0;
	}


	public static ResultSet getQuizInfo(int quizID) {
		ResultSet res = null;
		try {
			statement.executeQuery("SELECT * FROM quizes WHERE quizID = " + quizID+ ";");
		} catch (SQLException e) {
			System.out.println("Error in getQuizInfo");
			e.printStackTrace();
		}
		return res;
	}
	
	public static ResultSet getQuestionInfo(int questionID){
		ResultSet res = null;
		String query ="SELECT * FROM questions WHERE questionID =" + questionID +";" ;
		try {
			res = statement.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static ResultSet MultipleChoice(int questionID){
		ResultSet res = null;
		String query = "SELECT * FROM multiplechoice WHERE questionID =" + questionID + ";" ;
		try {
			res = statement.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static ResultSet answers(int questionID){
		ResultSet res = null;
		String query = "SELECT * FROM multiplechoice WHERE questionID =" + questionID + ";" ;
		try {
			res = statement.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
//	public static ResultSet 
	
	public static void sendChallenge(int idTo, int idFrom, int quizID) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		try {
			String query = "INSERT INTO challenges (accountIdTo,accountIdFrom,quizID,sendDate )VALUES("
					+ idTo + "," + idFrom + "," + quizID + date +");";
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
					.executeUpdate("INSERT INTO accounts (nick,name,surname,password,mail,Achievements) values(\""
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
		String query ="SELECT * FROM matching  WHERE questionID = " + questionId +" ;";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res; 
	}
	
	public static String getURL(int questionId){
		ResultSet res = null;
		String query ="SELECT url FROM imigeQuestion  WHERE questionID = " + questionId +" ;";
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
	
	public static int addQuestionResponse(Question q) {
		int questionID = addQuestion(q);
		MultipleAnswer answer = (MultipleAnswer) q.getCorrectAnswer();
		List<String> answers = answer.getAnswer();
		addAnswers(answers, questionID, 1);
		return questionID;

	}
	
	public static int addMultiAnswerQuestion(Question q){
		int questionID = addQuestion(q);
		MMAnswer answer = (MMAnswer) q.getCorrectAnswer();
		List<List<String> > correctAnswers = answer.getAnswer();
		for(int i = 0; i < correctAnswers.size(); i++){
			addAnswers(correctAnswers.get(i), questionID, i+1);
		}
		return questionID;
	}
	
	public static int addImageQuestion(ImageQuestion q){
		int questionID = addQuestion(q);
		MultipleAnswer answer = (MultipleAnswer) q.getCorrectAnswer();
		List<String> answers = answer.getAnswer();
		addAnswers(answers, questionID, 1);
		String url = q.getURL();
		try {
			statement.executeUpdate("INSERT INTO imageQuestion VALUES("+questionID+", \""+url+"\");" );
		} catch (SQLException e) {
			System.out.println("Error in a addImageQuestion");
			e.printStackTrace();
		}
		return questionID;
	}
	
	public static int addMatchingQuestion(Question q) {
		int questionID = addQuestion(q);
		MMAnswer answer = (MMAnswer) q.getCorrectAnswer();
		List<List<String> > correctMatches = answer.getAnswer();
		for(int i = 0; i < correctMatches.size(); i++){
			try{
				String answer1 = correctMatches.get(i).get(0);
				String answer2 = correctMatches.get(i).get(1);
				String query = "INSERT INTO matching VALUES (" + questionID + ", "
						+ answer1 + ", " + answer2 + ");";
				statement.executeUpdate(query);
			} catch (SQLException e){
				System.out.println("Error in addMatchingQuestion");
				e.printStackTrace();
			}
		}
		return questionID;
	}

	public static int addMultipleChoiceQuestion(MultipleChoiceQuestion q) {
		int questionID = addQuestion(q);
		SingleAnswer ans = (SingleAnswer) q.getCorrectAnswer();
		Set<String> possibleAnswers = q.getPossibleAnswers();
		String answer = ans.getAnswer();
		String isCorrect = "false";
		try {
			for(String possibleAnswer: possibleAnswers){
				if (answer.equals(possibleAnswer))
					isCorrect = "true";
				else
					isCorrect = "false";
				String query = "INSERT INTO multipleChoice VALUES (" + questionID
						+ ", " + possibleAnswer + ", " + isCorrect + ");";
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			System.out.println("Error in addMultipleChoiceQuestion");
			e.printStackTrace();
		}
		return questionID;
	}

	public static int addMCMAQuestion(MCMAQ q) {
		int questionID = addQuestion(q);
		MultipleAnswer ma = (MultipleAnswer) q.getCorrectAnswer();
		Set<String> possibleAnswers = q.getPossibleAnswers();
		String isCorrect = "false";
		List<String> correctAnswers = ma.getAnswer();
		try {
			for (String possibleAnswer : possibleAnswers) {
				if (correctAnswers.contains(possibleAnswer))
					isCorrect = "true";
				else
					isCorrect = "false";
				String query = "INSERT INTO multipleChoice VALUES ("
						+ questionID + ", " + possibleAnswer + ", " + isCorrect
						+ ");";
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			System.out.println("Error in addMCMAQuestion");
			e.printStackTrace();
		}
		return questionID;
	}
	
	private static int addQuestion(Question q) {
		int questionID = -1;
		try {
			String query = "SELECT addQuestion(" + q.getType() + ", \""
					+ q.getQuestionText() + "\", " + q.getScore() + ", "
					+ q.getIndex() + ");";
			ResultSet res = statement.executeQuery(query);
			res.next();
			questionID = res.getInt(1);
		} catch (SQLException e) {
			System.out.println("Error in addQuestion");
			e.printStackTrace();
		}
		return questionID;
	}
	
	private static void addAnswers(List<String> answers, int questionID, int answerNumber){
		try {
			CallableStatement cs;
			for (int i = 0; i < answers.size(); i++) {
				cs = (CallableStatement) connection
						.prepareCall("{call addAnswer(?,?,?)}");
				cs.setInt(1, answerNumber);
				cs.setString(2, answers.get(i));
				cs.setInt(3, questionID);
				cs.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error in addAnswers.");
			e.printStackTrace();
		}
	}
	
	public static void addQuestionToQuiz(int quizID, int questionID ){
		try {
			statement.executeUpdate("INSERT INTO questiontoquiz(quizID, questionID) VALUES (" + quizID +" ," + questionID + ");");
		} catch (SQLException e) {
			System.out.println("Error in addQuestionToQuiz");
			e.printStackTrace();
		}
	}
	
	public static List<Integer> searchUser(String subName){
		List<Integer> result = new ArrayList<Integer>();
		String query = "SELECT accountID FROM accounts WHERE nick like \"%" + subName + "%\";";
		ResultSet res;
		try {
			res = statement.executeQuery(query);
			while(res.next())
				result.add(res.getInt(1));
		} catch (SQLException e) {
			System.out.println("Error in searchUser.");
			System.out.println("Search query: " + query);
			e.printStackTrace();
		}
		return result;
	}
	
	public static ResultSet searchQuiz(String subName){
		ResultSet result = null;
		try {
			String query = "SELECT * FROM quizes WHERE name LIKE \"%" + subName + "%\";";
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error in searchQuiz.");
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Integer> getQuestions(int quizID) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "SELECT questionID FROM questionToQuiz WHERE quizID = " + quizID + ";";
			ResultSet res = statement.executeQuery(query);
			while(res.next()){
				result.add(res.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("Error in getQuestions");
		}
		return result;
	}
	
	private static String getCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		return date;
	}

	public static void addQuizResult(int accountID, int quizID, int score,
			long quizTimeInSeconds) {
		String date = getCurrentTime();
		String update = "INSERT INTO takenQuizes VALUES " +
				"(" + accountID + ", " + quizID + ", " + score 
					+ ", " + quizTimeInSeconds + ", " + date + ");";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println("Error in addQuizResult");
			e.printStackTrace();
		}
	}
}
