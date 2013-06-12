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
	private int totalScore;
	
	public QuizDB(String quizName, String description,int authorID) {
		this(quizName,description,authorID,0);
	}
	
	public QuizDB(String quizName, String description,int authorID, int quizID) {
		this.quizID = quizID;
		this.quizName = quizName;
		this.description = description;
		this.authorID = authorID;
		questions = new ArrayList<Question>();
		totalScore=-1;
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
		ArrayList<Integer> questionIDs = (ArrayList<Integer>) MyDB.getQuestions(getID());
		totalScore=0;
		for (int i=0; i<questionIDs.size(); i++)
			try {
				Question q = QuestionHelper.getFullQuestionFromID(questionIDs.get(i));
				questions.add(q);
				totalScore+=q.getScore();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return questions;
	}

	@Override
	public int getID() {
		if(quizID == 0){
			quizID = MyDB.getQuizId(quizName, authorID);
		}
		return quizID;
	}


	@Override
	public int getTotalScore() {
		return totalScore;
	}

}
