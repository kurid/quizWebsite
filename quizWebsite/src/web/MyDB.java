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

import quiz.QuizDB;

import Question.*;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

public class MyDB {
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "1234";
	private static final String MYSQL_DATABASE_SERVER = "localhost";
	private static final String MYSQL_DATABASE_NAME = "quizWebsite";

	private static Connection connection;
	private static Statement statement;
	public static Object notificationsNum;

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
			System.err.println("Update the MySQL constants to correct values!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Add the MySQL jar file to your build path!");
		}

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("connection can't create statement");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param accountID
	 * @return accountis Ids mixedvit igebs am eqauntis mier ganvlil qvizebs
	 */
	public static ResultSet getDoneQuizzes(int accountID) {
		ResultSet res = null;
		String query = "SELECT * FROM doneQuizzes WHERE accountID = ? ;";
		try {
			PreparedStatement prst = (PreparedStatement) connection
					.prepareStatement(query);
			prst.setInt(1, accountID);
			res = prst.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error in getDoneQuizzes");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @return bazashi bolos chamatebuli 5 qvizi
	 */
	public static ResultSet newQuizzes() {
		ResultSet res = null;
		String query = "SELECT * FROM quizes ORDER BY quiz_create_date DESC LIMIT 0,5;";
		try {
			PreparedStatement statement = (PreparedStatement) connection
					.prepareStatement(query);
			res = statement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error in newQuizzes");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @return yvelaze popularuli 5 qvizi
	 */
	public static ResultSet popularQuizzes() {
		ResultSet res = null;
		try {
			String query = "SELECT quizes.quizID,authorID, name,description FROM quizes, popularQuizes "
					+ " WHERE quizes.quizID = popularQuizes.quizID "
					+ " ORDER BY count DESC;";
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error in popularQuizzes");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @return lists of friends
	 */
	public static List<Integer> getFriends(int id) {
		List<Integer> friends = new ArrayList<Integer>();
		ResultSet res;
		try {
			PreparedStatement stat1 = (PreparedStatement) connection
					.prepareStatement("SELECT accountID2 FROM friendships WHERE accountID1 = ? ;");
			stat1.setInt(1, id);
			res = stat1.executeQuery();
			while (res.next()) {
				friends.add(res.getInt("accountID2"));
			}
			PreparedStatement stat2 = (PreparedStatement) connection
					.prepareStatement("SELECT accountID1 FROM friendships where accountID2 = ? ;");
			stat2.setInt(1, id);
			res = stat2.executeQuery();
			while (res.next()) {
				friends.add(res.getInt("accountID1"));
			}
		} catch (SQLException e) {
			System.out
					.println("getFriends-is dros moxda shecdoma. query-s gashvebisas");
			e.printStackTrace();
		}
		return friends;
	}

	/**
	 * svadasxva type-ebis( name, surname, nickname...) geteri.
	 */
	private static String getter(int id, String type) {
		String result = "";
		ResultSet res;
		PreparedStatement stat = null;
		try {
			String sql = "SELECT " + type
					+ " FROM accounts WHERE accountID = ?";
			stat = (PreparedStatement) connection.prepareStatement(sql);
			stat.setInt(1, id);
			res = stat.executeQuery();
			res.next();
			result = res.getString(type);
		} catch (SQLException e) {
			System.out.println("query-s gashvebisas moxda shecdoma. (get"
					+ type + ")");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param id
	 * @return Name according ID
	 */
	public static String getName(int id) {
		return getter(id, "name");
	}

	/**
	 * 
	 * @param id
	 * @return surname according ID
	 */
	public static String getSurname(int id) {
		return getter(id, "surname");
	}

	/**
	 * 
	 * @param id
	 * @return nickname according ID
	 */
	public static String getNickName(int id) {
		return getter(id, "nick");
	}

	/**
	 * 
	 * @param id
	 * @return password according ID
	 */
	public static String getPassword(int id) {

		return getter(id, "password");
	}

	/**
	 * 
	 * @param id
	 * @return mail according ID
	 */
	public static String getMail(int id) {
		return getter(id, "mail");
	}

	public static Boolean isAdmin(int id) {
		String admin = getter(id, "Achievements");
		if (admin.equals("admin")) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param nickName
	 * @return accountID
	 */
	public static int getId(String nickName) {
		int id = 0;
		ResultSet res;
		try {
			String sql = "SELECT accountID FROM accounts WHERE nick = ?";
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setString(1, nickName);
			res = stat.executeQuery();
			res.next();
			id = res.getInt("accountID");
		} catch (SQLException e) {
			System.out.println("query-s gashvebisas moxda shecdoma. (getId");
			e.printStackTrace();
		}
		return id;
	}

	public static void promoteAsAdmin(int id) {
		try {
			String sql = "update accounts set Achievements = \""
					+ Finals.ADMIN_ACHIEVEMENT + "\" where accountID=\""+id+"\";";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("query-s gashvebisas moxda shecdoma. (getId");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param type
	 *            mail or nickname
	 * @param stringToSearch
	 * @return true or false if exist string whit type 'type'
	 */
	private static boolean exist(String type, String stringToSearch) {
		boolean b = false;
		ResultSet res;
		try {
			String sql = "SELECT * FROM accounts WHERE " + type + " = ? ;";
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setString(1, stringToSearch);
			res = stat.executeQuery();
			b = res.next();
		} catch (SQLException e) {
			System.out.println("query-s gashvebisas moxda shecdoma. (exist"
					+ type + ")");
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 
	 * @param nickName
	 * @return nickName exist in data base
	 */
	public static boolean nickNameExist(String nickName) {
		return exist("nick", nickName);
	}

	/**
	 * 
	 * @param mail
	 * @return mail exist in data base
	 */
	public static boolean mailExist(String mail) {
		return exist("mail", mail);
	}

	/**
	 * 
	 * @param nickName
	 * 
	 *            deletes account
	 */
	public static void deleteAccount(String nickName) {
		try {
			statement.executeUpdate("DELETE FROM accounts WHERE nick = \""
					+ nickName + "\";");
		} catch (SQLException e) {
			System.out
					.println("query-s gashvebisas moxda shecdoma. (deleteAccount).");
			e.printStackTrace();
		}
	}

	/**
	 * returns only numbers
	 */
	public static int[] getNotifications(int id) {
		int[] arr = new int[3];
		arr[0] = getNotificationCount(id, "messages");
		arr[1] = getNotificationCount(id, "friendRequests");
		arr[2] = getNotificationCount(id, "challenges");
		return arr;
	}

	/**
	 * @param id
	 * @param table
	 * @return counts Notifications
	 */
	private static int getNotificationCount(int id, String table) {
		int count = 0;
		ResultSet res;
		try {
			String query = "SELECT COUNT(*) FROM " + table
					+ " WHERE accountIdTo = " + id;
			if (table == "messages") {
				query += " and read_unread = false";
			}
			query += ";";
			res = statement.executeQuery(query);
			res.next();
			count = Integer.parseInt(res.getString(1));
		} catch (Exception e) {
			System.out
					.println("query-s gashvebisas moxda shecdoma. (getNotifications).");
		}
		return count;
	}

	/**
	 * 
	 * @param idTo
	 * @return abrunebs challenges romelic am account-s miuvida
	 */
	public static List<Challenge> getChallenges(int idTo) {
		String query = "SELECT * FROM challengeQuiz WHERE accountIdTo = "
				+ idTo + ";";
		ResultSet res;
		List<Challenge> challanges = new ArrayList<Challenge>();
		try {
			res = statement.executeQuery(query);
			while (res.next()) {
				QuizDB quiz = new QuizDB(res.getString("name"),
						res.getString("description"), res.getInt("authorID"),
						res.getInt("quizID"));
				challanges.add(new Challenge(idTo, res.getInt("accountIdFrom"),
						res.getDate("sendTime"), quiz));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challanges;
	}

	/**
	 * 
	 * @param idTo
	 * @return abrunebs messages romelic am account-s miuvida
	 */
	public static List<Message> getMessages(int idTo) {
		ResultSet res;
		String query = "SELECT * FROM messages WHERE accountIdTo = ? ;";
		List<Message> messages = new ArrayList<Message>();
		try {
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(query);
			stat.setInt(1, idTo);
			res = stat.executeQuery();
			while (res.next()) {
				messages.add(new Message(idTo, res.getInt("accountIdFrom"), res
						.getDate("sendTime"), res.getString("text"), !res
						.getBoolean("read_unread")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	/**
	 * 
	 * @param idTo
	 * @return abrunebs friend request romelic am account-s miuvida
	 */
	public static List<FriendRequest> getRequest(int idTo) {
		ResultSet res;
		String query = "SELECT * FROM friendrequests WHERE accountIdTo = "
				+ idTo + ";";
		List<FriendRequest> friendRequests = new ArrayList<FriendRequest>();
		try {
			res = statement.executeQuery(query);
			while (res.next()) {
				friendRequests.add(new FriendRequest(idTo, res
						.getInt("accountIdFrom"), res.getDate("sendTime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendRequests;
	}

	/**
	 * 
	 * @param idTo
	 * @param idFrom
	 * @param text
	 *            send Message, saves in data base
	 */
	public static void sendMessage(int idTo, int idFrom, String text) {
		String date = getCurrentTime();
		try {
			String sql = "INSERT INTO messages VALUES(?, ?, ?, false, ?);";
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setInt(1, idTo);
			stat.setInt(2, idFrom);
			stat.setString(3, text);
			stat.setString(4, date);
			stat.executeUpdate();
		} catch (SQLException e) {
			System.out
					.println("query-s gashvebisas moxda shecdoma. (sendMessage).");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param name
	 * @param description
	 * @param accountID
	 * @return QuizId after creating quiz
	 */
	public static int createQuiz(String name, String description, int accountID) {
		String date = getCurrentTime();
		String sql = "INSERT INTO quizes(authorID,name,quiz_create_date,description) VALUES(?,?,?,?);";
		try {
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setInt(1, accountID);
			stat.setString(2, name);
			stat.setString(3, date);
			stat.setString(4, description);
			stat.executeUpdate();
		} catch (SQLException e) {
			System.out
					.println("query-s gashvebisas moxda shecdoma. (createQuiz).");
			e.printStackTrace();
		}
		return getQuizId(name, accountID);
	}

	/**
	 * @param name
	 * @param accountID
	 * @return quizID
	 */
	public static int getQuizId(String name, int accountID) {
		ResultSet res;
		String sql = "SELECT quizID FROM quizes WHERE name = ? AND authorID = ? ;";
		try {
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setString(1, name);
			stat.setInt(2, accountID);
			res = stat.executeQuery();
			if (res.next())
				return res.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			System.out
					.println("query-s gashvebisas moxda shecdoma. (getQuizId).");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @param quizID
	 * @return information about quiz
	 */
	public static ResultSet getQuizInfo(int quizID) {
		ResultSet res = null;
		try {
			res = statement.executeQuery("SELECT * FROM quizes WHERE quizID = "
					+ quizID + ";");
		} catch (SQLException e) {
			System.out.println("Error in getQuizInfo");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @param questionID
	 * @return information about question
	 */
	public static ResultSet getQuestionInfo(int questionID) {
		ResultSet res = null;
		String query = "SELECT * FROM questions WHERE questionID ="
				+ questionID + ";";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @param questionID
	 * @return Multiple Choice quistions
	 */
	public static ResultSet MultipleChoice(int questionID) {
		ResultSet res = null;
		String query = "SELECT * FROM multiplechoice WHERE questionID ="
				+ questionID + ";";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @param questionID
	 * @return correct answers for questions
	 */
	public static ResultSet answers(int questionID) {
		ResultSet res = null;
		String query = "SELECT * FROM answers WHERE questionID =" + questionID
				+ ";";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @param idTo
	 * @param idFrom
	 * @param quizID
	 *            sends challenge, quiz
	 */

	public static void sendChallenge(int idTo, int idFrom, int quizID) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		String query = "INSERT INTO challenges VALUES(" + idTo + ", " + idFrom
				+ ", " + quizID + ", " + "\"" + date + "\");";
		try {
			statement.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(query);
			System.out
					.println("shesabamisi id-is qvizi an accounti ar aris sheqmnili");
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param name
	 * @param surName
	 * @param nickName
	 * @param password
	 * @param mail
	 *            add account in data base
	 */
	public static void addAccount(String name, String surName, String nickName,
			String password, String mail) {
		String achievement = "Newbie";
		String sql = "INSERT INTO accounts (nick,name,surname,password,mail,Achievements) values(?,?,?,?,?,?)";
		try {
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setString(1, nickName);
			stat.setString(2, name);
			stat.setString(3, surName);
			stat.setString(4, password);
			stat.setString(5, mail);
			stat.setString(6, achievement);
			stat.executeUpdate();
		} catch (SQLException e) {
			System.out
					.println("query-s gashvebisas moxda shecdoma. (addAccount).");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param idTo
	 * @param idFrom
	 *            send friend request
	 */
	public static void sendFriendRequest(int idTo, int idFrom) {
		String date = getCurrentTime();
		String query = "INSERT INTO  friendrequests VALUES (" + idTo + ", "
				+ idFrom + ", \"" + date + "\");";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param questionId
	 * @return matching question
	 */
	public static ResultSet getMatching(int questionId) {
		ResultSet res = null;
		String query = "SELECT * FROM matching  WHERE questionID = "
				+ questionId + " ;";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 
	 * @param questionId
	 * @return url fror question
	 */
	public static String getURL(int questionId) {
		ResultSet res = null;
		String query = "SELECT url FROM imageQuestion  WHERE questionID = "
				+ questionId + " ;";
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

	/**
	 * 
	 * @param id1
	 * @param id2
	 *            makes friend sheap
	 */
	public static void addFriend(int id1, int id2) {
		try {
			CallableStatement cs = (CallableStatement) connection
					.prepareCall("{call addFriend(?,?)}");
			cs.setInt(1, id1);
			cs.setInt(2, id2);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param q
	 * @return questionResponse id after adding in data base
	 */
	public static int addQuestionResponse(Question q) {
		int questionID = addQuestion(q);
		MultipleAnswer answer = (MultipleAnswer) q.getCorrectAnswer();
		List<String> answers = answer.getAnswer();
		addAnswers(answers, questionID, 1);
		return questionID;

	}

	/**
	 * 
	 * @param q
	 * @return MultiAnswerQuestion id after adding in data base
	 */
	public static int addMultiAnswerQuestion(Question q) {
		int questionID = addQuestion(q);
		MMAnswer answer = (MMAnswer) q.getCorrectAnswer();
		List<List<String>> correctAnswers = answer.getAnswer();
		for (int i = 0; i < correctAnswers.size(); i++) {
			addAnswers(correctAnswers.get(i), questionID, i + 1);
		}
		return questionID;
	}

	/**
	 * 
	 * @param q
	 * @return ImageQuestion id after adding in data base
	 */
	public static int addImageQuestion(ImageQuestion q) {
		int questionID = addQuestion(q);
		MultipleAnswer answer = (MultipleAnswer) q.getCorrectAnswer();
		List<String> answers = answer.getAnswer();
		addAnswers(answers, questionID, 1);
		String url = q.getURL();
		String sql = "INSERT INTO imageQuestion VALUES(?, ?);";
		try {
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setInt(1, questionID);
			stat.setString(2, url);
			stat.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error in a addImageQuestion");
			e.printStackTrace();
		}
		return questionID;
	}

	/**
	 * 
	 * @param q
	 * @return MatchingQuestion id after adding in data base
	 */
	public static int addMatchingQuestion(Question q) {
		int questionID = addQuestion(q);
		MMAnswer answer = (MMAnswer) q.getCorrectAnswer();
		List<List<String>> correctMatches = answer.getAnswer();
		for (int i = 0; i < correctMatches.size(); i++) {
			try {
				String answer1 = correctMatches.get(i).get(0);
				String answer2 = correctMatches.get(i).get(1);
				String sql = "INSERT INTO matching VALUES (?,?,?);";
				PreparedStatement stat = (PreparedStatement) connection
						.prepareStatement(sql);
				stat.setInt(1, questionID);
				stat.setString(2, answer1);
				stat.setString(3, answer2);
				stat.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error in addMatchingQuestion");
				e.printStackTrace();
			}
		}
		return questionID;
	}

	/**
	 * 
	 * @param q
	 * @return MultipleChoiceQuestion id after adding in data base
	 */
	public static int addMultipleChoiceQuestion(MultipleChoiceQuestion q) {
		int questionID = addQuestion(q);
		SingleAnswer ans = (SingleAnswer) q.getCorrectAnswer();
		Set<String> possibleAnswers = q.getPossibleAnswers();
		String answer = ans.getAnswer();
		String isCorrect = "false";
		try {
			for (String possibleAnswer : possibleAnswers) {
				if (answer.equals(possibleAnswer))
					isCorrect = "true";
				else
					isCorrect = "false";
				String sql = "INSERT INTO multipleChoice VALUES (?,?, "
						+ isCorrect + ");";
				PreparedStatement stat = (PreparedStatement) connection
						.prepareStatement(sql);
				stat.setInt(1, questionID);
				stat.setString(2, possibleAnswer);
				stat.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error in addMultipleChoiceQuestion");
			e.printStackTrace();
		}
		return questionID;
	}

	/**
	 * 
	 * @param q
	 * @return MCMAQuestion id after adding in data base
	 */
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
				String sql = "INSERT INTO multipleChoice VALUES (?, ?, "
						+ isCorrect + ");";
				PreparedStatement stat = (PreparedStatement) connection
						.prepareStatement(sql);
				stat.setInt(1, questionID);
				stat.setString(2, possibleAnswer);
				stat.executeUpdate();
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

			String sql = "SELECT addQuestion(?, ?, ?, ?);";
			PreparedStatement stat = (PreparedStatement) connection
					.prepareStatement(sql);
			stat.setInt(1, q.getType());
			stat.setString(2, q.getQuestionText());
			stat.setInt(3, q.getScore());
			stat.setInt(4, q.getIndex());
			ResultSet res = stat.executeQuery();
			res.next();
			questionID = res.getInt(1);
		} catch (SQLException e) {
			System.out.println("Error in addQuestion");
			e.printStackTrace();
		}
		return questionID;
	}

	/**
	 * 
	 * @param answers
	 * @param questionID
	 * @param answerNumber
	 *            adds ansswer
	 */
	private static void addAnswers(List<String> answers, int questionID,
			int answerNumber) {
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

	/**
	 * 
	 * @param quizID
	 * @param questionID
	 *            adds question to quiz
	 */
	public static void addQuestionToQuiz(int quizID, int questionID) {
		try {
			statement
					.executeUpdate("INSERT INTO questiontoquiz(quizID, questionID) VALUES ("
							+ quizID + " ," + questionID + ");");
		} catch (SQLException e) {
			System.out.println("Error in addQuestionToQuiz");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param subName
	 * @return list of searched Users according nick name
	 */
	public static List<Integer> searchUser(String subName) {
		List<Integer> result = new ArrayList<Integer>();
		String query = "SELECT accountID FROM accounts WHERE nick LIKE \"%"
				+ subName + "%\";";
		ResultSet res;
		try {
			res = statement.executeQuery(query);
			while (res.next())
				result.add(res.getInt(1));
		} catch (SQLException e) {
			System.out.println("Error in searchUser.");
			System.out.println("Search query: " + query);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param subName
	 * @return list of searched quizzes according quiz name
	 */
	public static ResultSet searchQuiz(String subName) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM quizes WHERE name LIKE \"%" + subName
					+ "%\";";
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error in searchQuiz.");
			e.printStackTrace();
		}
		return result;
	}

	public static List<Integer> getQuestions(int quizID) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "SELECT questionID FROM questionToQuiz WHERE quizID = "
					+ quizID + ";";
			ResultSet res = statement.executeQuery(query);
			while (res.next()) {
				result.add(res.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("Error in getQuestions");
		}
		return result;
	}

	private static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String date = sdf.format(utilDate);
		return date;
	}

	public static void addQuizResult(int accountID, int quizID, int score,
			long quizTimeInSeconds) {
		String date = getCurrentTime();
		String update = "INSERT INTO takenQuizes VALUES " + "(" + accountID
				+ ", " + quizID + ", " + score + ", " + quizTimeInSeconds
				+ ", \"" + date + "\");";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println("Error in addQuizResult");
			e.printStackTrace();
		}
	}

	public static ResultSet getTakenQuizes(int accountID) {
		ResultSet res = null;
		String query = "SELECT * FROM quizes q, takenQuizes tq"
				+ " WHERE q.quizID = tq.quizID AND tq.accountID = " + accountID
				+ ";";
		try {
			res = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void deleteFriendship(int id1, int id2) {
		String update = "DELETE FROM friendships WHERE " + "(accountID1 = "
				+ id1 + " AND accountID2 = " + id2 + ") OR (accountID2 = "
				+ id1 + " AND accountID1 = " + id2 + ");";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean friendRequestExists(int idFrom, int idTo) {
		String query = "SELECT * FROM friendRequests WHERE accountIdTo = "
				+ idTo + " AND accountIdFrom = " + idFrom + ";";
		try {
			ResultSet res = statement.executeQuery(query);
			if (res.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void deleteFriendRequest(int id1, int id2) {
		String update = "DELETE FROM friendRequests WHERE " + "(accountIDTo = "
				+ id1 + " AND accountIDFrom = " + id2
				+ ") OR (accountIDFrom = " + id1 + " AND accountIDTo = " + id2
				+ ");";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println("Error in deleteFriendRequest");
			System.out.println(update);
			e.printStackTrace();
		}
	}

	public static void addTagToQuiz(int quizID, int tagID) {
		String update = "INSERT INTO tagToQuiz VALUES(" + quizID + ", " + tagID
				+ ");";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println(update);
			e.printStackTrace();
		}
	}

	public static void makeAsAdmin(int userID) {
		String update = "UPDATE accounts SET Achievements = \"Admin\" WHERE accountID = "
				+ userID + ";";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println(update);
			e.printStackTrace();
		}
	}

	public static ResultSet getCreatedQuizes(int authorID) {
		ResultSet res = null;
		String sql = "SELECT * FROM quizes WHERE authorID = " + authorID + ";";
		try {
			res = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		return res;
	}

	public static void deleteQuiz(int quizID) {
		String sql = "DELETE FROM quizes WHERE quizID = " + quizID + ";";
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
	}

	public static void markAsRead(int idTo) {
		String update = "UPDATE messages SET read_unread = true WHERE accountIdTo = "
				+ idTo + ";";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println(update);
			e.printStackTrace();
		}
	}

	public static int notificationsNum(int id) {
		int[] a = getNotifications(id);
		return a[0] + a[1] + a[2];
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}