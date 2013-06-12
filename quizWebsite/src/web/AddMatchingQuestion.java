package web;

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

import com.sun.org.apache.xalan.internal.xsltc.dom.MatchingIterator;

import Question.CorrectAnswer;
import Question.MMAnswer;
import Question.MatchingQuestion;
import Question.Question;

/**
 * Servlet implementation class AddMatchingQuestion
 */
@WebServlet("/AddMatchingQuestion")
public class AddMatchingQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMatchingQuestion() {
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
		List<List<String>> answers = new ArrayList<List<String>>();
		for (int i = 2; i <= 8; i += 2) {
			String matching1 = request.getParameter("field" + (i - 1));
			String matching2 = request.getParameter("field" + i);
			if (matching1.equals("") == false && matching2.equals("") == false) {
				ArrayList<String> pair = new ArrayList<String>();
				pair.add(matching1);
				pair.add(matching2);
				answers.add(pair);
			}
		}
		String jsp = "ChooseQuestionToAdd.jsp";
		HttpSession session = request.getSession(true);
		if (answers.size() < 2) {
			String errorText = "Choose at least two matchings";
			session.setAttribute("errorText", errorText);
			jsp = "AddMatchingQuestion.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
			return;
		}
		String questionText = request.getParameter("questionText");
		List<Question> questions = (List<Question>) session
				.getAttribute("questions");
		int currentNumberOfQuestion = (Integer) session
				.getAttribute("currentNumberOfQuestion");
		int numberOfQuestions = (Integer) session
				.getAttribute("numberOfQuestions");
		int score = Integer.parseInt(request.getParameter("Score"));
		CorrectAnswer correctAnswer = new MMAnswer(answers);
		Question question = new MatchingQuestion(currentNumberOfQuestion,
				questionText, correctAnswer, score);
		questions.add(question);
		if(currentNumberOfQuestion==numberOfQuestions){
			jsp = "AddingFinished.jsp";
		}else{
			currentNumberOfQuestion++;
			session.setAttribute("currentNumberOfQuestion", currentNumberOfQuestion);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
