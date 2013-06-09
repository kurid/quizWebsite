package quiz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Question.Question;

import web.*;

/**
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeQuizServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int qIndex = (Integer) session.getAttribute("qIndex");
		String jsp=null;
		if (qIndex == 0) {
			int quizID = (Integer) session.getAttribute("quizID");
			ResultSet rs = MyDB.getQuizInfo(quizID);
			int authorID = -1;
			String name = null, description = null;
			try {
				authorID = rs.getInt("authorID");
				name = rs.getString("name");
				description = rs.getString("description");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			QuizDB quiz = new QuizDB(name, description, authorID);
			session.setAttribute("quiz", quiz);
			session.removeAttribute("quizID");
			List<Question> qList = (ArrayList<Question>) quiz.generateQuestions();
			session.setAttribute("qList", qList);
			jsp = qList.get(qIndex).getJspName();
			ArrayList<Integer> answersCorrectness = new ArrayList<Integer>();
			session.setAttribute("answersCorrectness", answersCorrectness);
			session.setAttribute("startTime", System.currentTimeMillis());
		} else {
			List<Question> qList = (ArrayList<Question>) session.getAttribute("qList");
			if (qIndex == qList.size()){
				Account account = (Account) session.getAttribute("account");
				int accountID = (account).getId();
				int quizID = (Integer) session.getAttribute("quizID");
				long endTime = System.currentTimeMillis();
				long startTime = (Long) session.getAttribute("startTime");
				long quizTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				java.util.Date utilDate = new java.util.Date();
				String date = sdf.format(utilDate);
				int score = countCorrectAnswers((ArrayList<Integer>) session.getAttribute("answersCorrectness"));
				MyDB.addQuizResult(accountID,quizID,score,quizTimeInSeconds,date);
				QuizDB quiz = (QuizDB) session.getAttribute("quiz");
				request.setAttribute("quiz", quiz);
				request.setAttribute("score", score);
				request.setAttribute("fullScore", quiz.getTotalScore());
				request.setAttribute("quizTimeInSeconds", quizTimeInSeconds);
				session.removeAttribute("startTime");
				session.removeAttribute("answersCorrectness");
				session.removeAttribute("qList");
				session.removeAttribute("qIndex");
				session.removeAttribute("quiz");
				jsp = "quizDone.jsp";
			} else {
				checkAnswer(qList,request,qIndex);
				jsp = qList.get(qIndex).getJspName();
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	private int countCorrectAnswers(ArrayList<Integer> answers) {
		int count=0;
		for (int i=0; i<answers.size(); i++)
			count+=answers.get(i);
		return count;
	}

	private void checkAnswer(List<Question> qList, HttpServletRequest request, int qIndex) {
		
		
	}

}
