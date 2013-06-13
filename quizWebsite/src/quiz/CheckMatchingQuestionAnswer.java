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

import Question.CorrectAnswer;
import Question.Question;
import Question.ReceivedAnswer;
import Question.ReceivedMatchingAnswer;

/**
 * Servlet implementation class CheckMatchingQuestionAnswer
 */
@WebServlet("/CheckMatchingQuestionAnswer")
public class CheckMatchingQuestionAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckMatchingQuestionAnswer() {
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
		CorrectAnswer correctAnswer = q.getCorrectAnswer();
		List<List<String>> answers = (List<List<String>>)correctAnswer.getAnswer();
		List<List<String>> recivedAnswers = new ArrayList<List<String>>();
		for(int i=0;i<answers.size();i++){
			String matching1 = answers.get(i).get(0);
			String matching2 = request.getParameter(matching1);
			List<String> pair = new ArrayList<String>();
			pair.add(matching1);
			pair.add(matching2);
			recivedAnswers.add(pair);
		}
		ReceivedAnswer recivedAnswer = new ReceivedMatchingAnswer(recivedAnswers);
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
