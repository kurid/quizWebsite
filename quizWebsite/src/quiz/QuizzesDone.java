package quiz;

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

import web.Account;
import web.MyDB;

/**
 * Servlet implementation class QuizzesDone
 */
@WebServlet("/QuizzesDone")
public class QuizzesDone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizzesDone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account user = (Account) request.getSession(true).getAttribute("account");
		ResultSet res = MyDB.getDoneQuizzes(user.getId());
		List<DoneQuize> quizzesDone = new ArrayList<DoneQuize>();
		try {
			while(res.next()){
				int quizID = res.getInt("quizID");
				int authorID = res.getInt("authorID");
				String quizName = res.getString("name");
				String description = res.getString("description");
				QuizDB quiz = new QuizDB(quizName, description, authorID,quizID);
				quizzesDone.add(new DoneQuize(res.getInt("accountID"), res.getString("quiz_time"),
						res.getDate("quiz_take_date"), res.getInt("total_score"), quiz));
			}
		} catch (SQLException e) {
			System.out.println("res.next dros moxda shecdoma");
			e.printStackTrace();
		}
		request.setAttribute("quizzesDone",quizzesDone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("QuizzesDone.jsp");
		dispatcher.forward(request, response);
	}

}
