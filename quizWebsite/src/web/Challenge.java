package web;

import java.sql.*;
import java.util.Date;

import quiz.QuizDB;

public class Challenge extends Notification{
	
	private QuizDB quiz;
	
	public Challenge(int to, int from, Date date, int quizID){
		sender = from;
		recieverID = to;
		this.date = date;
		createQuiz(quizID);
	}
	
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
	
	public QuizDB getQuiz(){
		return quiz;
	}

}
