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

import Question.Question;

/**
 * Servlet implementation class CreateQuiz
 */
@WebServlet("/CreateQuiz")
public class CreateQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuiz() {
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
		String quizName = request.getParameter("quizName");
		String description = request.getParameter("description");
		int numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
		HttpSession session = request.getSession(true);
		List<Question> questions= new ArrayList<Question>();
		session.setAttribute("quizName",quizName);
		session.setAttribute("quizDescription",description);
		session.setAttribute("numberOfQuestions",numberOfQuestions);		
		session.setAttribute("questions",questions);
		session.setAttribute("currentNumberOfQuestion",1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChooseQuestionToAdd.jsp");
		dispatcher.forward(request, response);
	}

}
