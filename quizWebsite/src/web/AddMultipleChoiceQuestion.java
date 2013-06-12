package web;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Question.CorrectAnswer;
import Question.ImageQuestion;
import Question.MultipleChoiceQuestion;
import Question.Question;
import Question.SingleAnswer;

/**
 * Servlet implementation class AddMultipleChoiceQuestion
 */
@WebServlet("/AddMultipleChoiceQuestion")
public class AddMultipleChoiceQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMultipleChoiceQuestion() {
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
		String jsp = "ChooseQuestionToAdd.jsp";
		int score = Integer.parseInt(request.getParameter("Score"));
		if (request.getParameter("choice") == null) {
			String errorText = "Choose one choise as an answer";
			session.setAttribute("errorText", errorText);
			jsp="AddMultipleChoiceQuestion.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
			return;
			//System.out.println("mafasf");
		} else {
			int c = Integer.parseInt(request.getParameter("choice"));
			HashSet<String> choices = new HashSet<String>();
			CorrectAnswer correctAnswer = null;
			for (int i = 1; i <= 5; i++) {
				String choice = request.getParameter("" + i);
				if (c == i && choice.equals("")) {
					String errorText = "Choose one choise as an answer";
					session.setAttribute("errorText", errorText);
					jsp="AddMultipleChoiceQuestion.jsp";
					RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
					dispatcher.forward(request, response);
					return;
				}else if (c == i) {
					correctAnswer = new SingleAnswer(choice);					
				}
				if(!choice.equals(""))choices.add(choice);
			}
			String questionText = request.getParameter("questionText");
			List<Question> questions = (List<Question>) session
					.getAttribute("questions");
			int currentNumberOfQuestion = (Integer) session
					.getAttribute("currentNumberOfQuestion");
			int numberOfQuestions = (Integer) session
					.getAttribute("numberOfQuestions");
			Question question = new MultipleChoiceQuestion(
					currentNumberOfQuestion, questionText, choices,
					correctAnswer, score);
			questions.add(question);
			if(currentNumberOfQuestion==numberOfQuestions){
				jsp = "AddingFinished.jsp";
			}else{
				currentNumberOfQuestion++;
				session.setAttribute("currentNumberOfQuestion", currentNumberOfQuestion);
			}
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
