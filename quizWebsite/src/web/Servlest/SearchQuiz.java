package web.Servlest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quiz.QuizDB;
import web.MyDB;

/**
 * Servlet implementation class SearchQuiz
 */
@WebServlet("/SearchQuiz")
public class SearchQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchQuiz() {
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
		ResultSet resultset = MyDB.searchQuiz((String)request.getParameter("quizzName"));
		List<QuizDB> searchesQuizzes = new ArrayList<QuizDB>();
		try {
			while(resultset.next()){
				String quizName = resultset.getString("name");
				String description = resultset.getString("description");
				int authorID = resultset.getInt("authorID");
				searchesQuizzes.add(new QuizDB(quizName, description, authorID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("searchedQuizzes", searchesQuizzes);
		String jsp = "Quizzes.jsp";
		if((Boolean)request.getSession(true).getAttribute("goingToAdminPanel")){
			jsp = "AdminQuizzes.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
}
