package quiz;

import java.sql.Date;
import web.Account;

public class DoneQuize {
	private int accountID;
	private Account account;
	private QuizDB quiz;
	private int totalScore;
	private String time;
	private Date quizTakeDate;
	
	public DoneQuize(int accountID, String time, Date quizTakeDate, int totalScore, QuizDB quiz) {
		this.accountID = accountID;
		this.time = time;
		this.quizTakeDate = quizTakeDate;
		this.totalScore = totalScore;
		this.quiz = quiz;
	}

	public int getAccountID() {
		return accountID;
	}

	public Account getAccount() {
		return account;
	}

	public QuizDB getQuiz() {
		return quiz;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public String getTime() {
		return time;
	}

	public Date getQuizTakeDate() {
		return quizTakeDate;
	}

}
