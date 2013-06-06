package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Question.CorrectAnswer;
import Question.Question;
import Question.QuestionResponse;
import Question.SingleAnswer;

/**
 * Servlet implementation class AddSingleAnswerQuestion
 */
@WebServlet("/AddSingleAnswerQuestion")
public class AddSingleAnswerQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSingleAnswerQuestion() {
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
		String questionText = request.getParameter("questionText");
		String answer = request.getParameter("answer");
		
		HttpSession session = request.getSession(true);
		int currentNumberOfQuestion = (Integer) session
				.getAttribute("currentNumberOfQuestion");
		int numberOfQuestions = (Integer) session
				.getAttribute("numberOfQuestions");
		List<Question> questions = (List<Question>) session.getAttribute("questions");
		CorrectAnswer correctAnswer = new SingleAnswer(answer);
		Question question = new QuestionResponse(currentNumberOfQuestion,
				questionText, correctAnswer, 1);
		questions.add(question);
		if(currentNumberOfQuestion==numberOfQuestions){
			
		}
		// System.out.println("Kitxwa : "+questionText+" answer :  "+answer);
		// ai aq ukve mwirdeba MyDB s metodi romelic chaamatebs bazashi aset
		// kitxvas .
	}

}
