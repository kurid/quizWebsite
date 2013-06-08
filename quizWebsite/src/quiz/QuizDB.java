package quiz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Question.Question;

import web.MyDB;

public class QuizDB implements QuizInterface{
	private String quizName;
	private String description;
	private int authorID;
	private List<Question> questions;
	private int quizID;
	
	public QuizDB(String quizName, String description,int authorID) {
		this.quizName = quizName;
		this.description = description;
		this.authorID = authorID;
		quizID = MyDB.getQuizId(quizName, authorID);
		questions = new ArrayList<Question>();
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
	public List<Question> generateQuestions(){
		ArrayList<Integer> questionIDs = (ArrayList<Integer>) MyDB.getQuestions(quizID);
		for (int i=0; i<questionIDs.size(); i++)
			try {
				questions.add(DroebitiRoja.getFullQuestionFromID(questionIDs.get(i)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return questions;
	}

	@Override
	public int getId() {
		return quizID;
	}

}
