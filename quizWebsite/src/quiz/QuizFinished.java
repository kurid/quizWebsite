package quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.Account;
import web.MyDB;

/**
 * Servlet implementation class QuizFinished
 */
@WebServlet("/QuizFinished")
public class QuizFinished extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizFinished() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Account account = (Account) session.getAttribute("account");
		int accountID = account.getId();
		int quizID = ((QuizDB) session.getAttribute("quizDB")).getID();
		long endTime = System.currentTimeMillis();
		long startTime = (Long) session.getAttribute("startTime");
		long quizTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
		int score = countCorrectAnswers((ArrayList<Integer>) session.getAttribute("answersCorrectness"));
		MyDB.addQuizResult(accountID,quizID,score,quizTimeInSeconds);
		QuizDB quiz = (QuizDB) session.getAttribute("quizDB");
		request.setAttribute("quizName", quiz.getName());
		request.setAttribute("score", score);
		request.setAttribute("fullScore", quiz.getTotalScore());
		request.setAttribute("quizTimeInSeconds", quizTimeInSeconds);
		session.removeAttribute("startTime");
		session.removeAttribute("answersCorrectness");
		session.removeAttribute("qList");
		session.removeAttribute("qIndex");
		session.removeAttribute("quizDB");
		String jsp = "QuizDone.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	private int countCorrectAnswers(ArrayList<Integer> answers) {
		int count=0;
		for (int i=0; i<answers.size(); i++)
			count+=answers.get(i);
		return count;
	}
	
}
