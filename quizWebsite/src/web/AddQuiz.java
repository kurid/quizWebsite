package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Question.Question;
import Question.Finals;

/**
 * Servlet implementation class AddQuize
 */
@WebServlet("/AddQuiz")
public class AddQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuiz() {
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
		List<Question> questions = (List<Question>) session.getAttribute("questions");
		String quizName = (String) session.getAttribute("quizName");
		String description = (String) session.getAttribute("quizDescription");
		Account author = (Account) session.getAttribute("account");
		int quizId = MyDB.createQuiz(quizName, description, author.getId());
		addQuestions(questions, quizId);
		response.sendRedirect("HomePage");
	}

	private void addQuestions(List<Question> questions, int quizId) {
		for(int i = 0; i < questions.size(); i++){
//			if(questions.get(i).getType() == QuestionFinals.QUESTION_RESPONCE){	
//				int questionId = MyDB.addQuestionResponse(questions.get(i));
//				MyDB.addQuestionToQuiz(quizId, questionId);
//			}
			int questionId = questions.get(i).addToDB();
			MyDB.addQuestionToQuiz(quizId, questionId);
		}
	}

}
