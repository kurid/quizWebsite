package quiz;

import java.util.ArrayList;
import java.util.List;

import web.MyDB;

public class QuizDB implements QuizInterface{
	private String quizName;
	private String description;
	private int authorID;
	private List questions;
	private int quizId;
	
	public QuizDB(String quizName, String description,int authorID) {
		this.quizName = quizName;
		this.description = description;
		this.authorID = authorID;
		quizId = MyDB.getQuizId(quizName, authorID);
	}


	@Override
	public String getName() {
		return quizName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getAuthor() {
		return authorID; 
		
	}

	@Override
	public void getQuestions() {
		// TODO Auto-generated method stub
	}

	@Override
	public void generateQuestions() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getId() {
		return quizId;
	}

}
