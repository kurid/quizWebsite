package web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quiz.QuizDB;

/**
 * Servlet implementation class QuizzesHistory
 */
@WebServlet("/QuizzesHistory")
public class QuizzesHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizzesHistory() {
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
		int ID = (Integer) request.getSession().getAttribute("account");
		ResultSet resultset = MyDB.getTakenQuizes(ID);
		List<QuizDB> quizzes = new ArrayList<QuizDB>();
		try {
			while (resultset.next()) {
				String quizName = resultset.getString("name");
				String description = resultset.getString("description");
				int authorID = resultset.getInt("authorID");
				quizzes.add(new QuizDB(quizName, description, authorID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("newQuizzes", quizzes);
	}
}
