package quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PopularQuizzes
 */
@WebServlet("/PopularQuizzes")
public class PopularQuizzes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopularQuizzes() {
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
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		List<QuizDB> quizzes = new ArrayList<QuizDB>();
		for (int i = 0; i < 10 ; i ++){
			try {
				resultset.next();
				String quizName = resultset.getString("");
				String description = resultset.getString("");
				int authorID = resultset.getInt("");
				quizzes.add(new QuizDB(quizName, description, authorID));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("quizzes", quizzes);
		String jsp = "";
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp );
		dispatcher.forward(request, response);
	}

}
