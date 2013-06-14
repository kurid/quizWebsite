package web;

import java.sql.*;
import java.util.Date;

import quiz.QuizDB;
/**
 * 
 * @author User
 * Challenge class extends Notification  
 */

public class Challenge extends Notification{
	
	private QuizDB quiz;
	
	public Challenge(int to, int from, Date date, QuizDB quiz){
		sender = from;
		recieverID = to;
		this.date = date;
		this.quiz = quiz;
	}
	
	/**
	 * 
	 * @param quizID create quiz according quiz id
	 */
	private void createQuiz(int quizID){
		ResultSet rs = MyDB.getQuizInfo(quizID);
		System.out.println(rs);
		int authorID = -1;
		String name = null, description = null;
		try {
			if(rs.next()){
				authorID = rs.getInt("authorID");
				name = rs.getString("name");
				description = rs.getString("description");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		quiz = new QuizDB(name, description, authorID);
	}
	
	
	/**
	 * 
	 * @return quiz
	 */
	public QuizDB getQuiz(){
		return quiz;
	}

}
