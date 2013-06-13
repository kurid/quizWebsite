package web.Servlest;

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
import Question.MultipleAnswer;
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
		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");
		String answer3 = request.getParameter("answer3");
		String answer4 = request.getParameter("answer4");
		int score = Integer.parseInt(request.getParameter("Score"));		
		HttpSession session = request.getSession(true);
		int currentNumberOfQuestion = (Integer) session
				.getAttribute("currentNumberOfQuestion");
		int numberOfQuestions = (Integer) session
				.getAttribute("numberOfQuestions");
		List<Question> questions = (List<Question>) session.getAttribute("questions");
		List<String> answers = new ArrayList<String>();
		if(!answer1.equals(""))answers.add(answer1);
		if(!answer2.equals(""))answers.add(answer2);
		if(!answer3.equals(""))answers.add(answer3);
		if(!answer4.equals(""))answers.add(answer4);
		CorrectAnswer correctAnswer = new MultipleAnswer(answers);
		Question question = new QuestionResponse(currentNumberOfQuestion,
				questionText, correctAnswer, score);
		questions.add(question);
		String jsp = "ChooseQuestionToAdd.jsp";
		if(currentNumberOfQuestion==numberOfQuestions){
			jsp = "AddingFinished.jsp";
		}else{
			currentNumberOfQuestion++;
			session.setAttribute("currentNumberOfQuestion", currentNumberOfQuestion);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		//System.out.println(jsp);
		dispatcher.forward(request, response);
		// System.out.println("Kitxwa : "+questionText+" answer :  "+answer);
		// ai aq ukve mwirdeba MyDB s metodi romelic chaamatebs bazashi aset
		// kitxvas .
	}

}
