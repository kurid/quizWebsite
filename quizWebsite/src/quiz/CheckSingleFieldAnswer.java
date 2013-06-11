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
import Question.RecievedAnswer;
import Question.RecievedSingleAnswer;

/**
 * Servlet implementation class QuestionResponse
 */
@WebServlet("/CheckSingleFieldAnswer")
public class CheckSingleFieldAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckSingleFieldAnswer() {
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
		int qIndex = (Integer) session.getAttribute("qIndex");
		List<Question> qList = (ArrayList<Question>) session
				.getAttribute("qList");
		Question q = qList.get(qIndex - 1);
		ArrayList<Integer> answersCorrectness = (ArrayList<Integer>) session
				.getAttribute("answersCorrectness");
		String answer = request.getParameter("field1");
		RecievedAnswer recivedAnswer = new RecievedSingleAnswer(answer);
		answersCorrectness.add(q.checkAnswer(recivedAnswer));
		session.setAttribute("answersCorrectness", answersCorrectness);
		String jsp = null;
		if (qIndex == qList.size()) {
			jsp = "QuizFinished";
		} else {
			jsp = qList.get(qIndex).getJspName();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
}
