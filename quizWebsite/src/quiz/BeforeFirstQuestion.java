package quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Question.Question;

/**
 * Servlet implementation class BeforeFirstQuestion
 */
@WebServlet("/BeforeFirstQuestion")
public class BeforeFirstQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BeforeFirstQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String jsp;
		if (!(Boolean) session.getAttribute("isLoggedIn")) {
			jsp = "Login.jsp";
			session.setAttribute("enterText",
					"You have to log in before you take a quiz.");
		} else {
			session.setAttribute("qIndex", 0);
			QuizDB quiz = (QuizDB) session.getAttribute("quizDB");
			List<Question> qList = (ArrayList<Question>) quiz
					.generateQuestions();
			jsp = qList.get(0).getJspName();
			session.setAttribute("qList", qList);
			ArrayList<Integer> answersCorrectness = new ArrayList<Integer>();
			session.setAttribute("answersCorrectness", answersCorrectness);	
			session.setAttribute("startTime", System.currentTimeMillis());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
