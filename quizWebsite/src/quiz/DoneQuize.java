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
	
	/**
	 * 
	 * @param accountID
	 * @param time
	 * @param quizTakeDate
	 * @param totalScore
	 * @param quiz
	 * creates object doneQuiz
	 */
	public DoneQuize(int accountID, String time, Date quizTakeDate, int totalScore, QuizDB quiz) {
		this.accountID = accountID;
		this.time = time;
		this.quizTakeDate = quizTakeDate;
		this.totalScore = totalScore;
		this.quiz = quiz;
	}

	/**
	 * 
	 * @return account id how done this quiz
	 */
	public int getAccountID() {
		return accountID;
	}
	
	/**
	 * 
	 * @return account  how done this quiz
	 */
	public Account getAccount() {
		return account;
	}
	
	
	/**
	 * 
	 * @return quiz how done this quiz
	 */
	public QuizDB getQuiz() {
		return quiz;
	}
	
	
	/**
	 * 
	 * @return qula romelic am qvizshi aigo
	 */
	public int getTotalScore() {
		return totalScore;
	}

	
	/**
	 * 
	 * @return ramden xanshi gaiara
	 */
	public String getTime() {
		return time;
	}

	
	/**
	 * 
	 * @return tarigi roca es qvizi gaaketa
	 */
	public Date getQuizTakeDate() {
		return quizTakeDate;
	}

}
