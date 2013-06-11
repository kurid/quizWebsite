package quiz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.INTERNAL;

import web.MyDB;

/**
 * Servlet implementation class StartTackingQuiz
 */
@WebServlet("/StartTackingQuiz")
public class StartTackingQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTackingQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int quizID = Integer.parseInt(request.getParameter("ID"));//(Integer)request.getSession(true).getAttribute("ID");
		HttpSession session = request.getSession(true);
		ResultSet rs = MyDB.getQuizInfo(quizID);
		int authorID = -1;
		System.out.println(quizID);
		String name = null, description = null;
		try {
			rs.next();
			authorID = rs.getInt("authorID");
			name = rs.getString("name");
			description = rs.getString("description");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		QuizDB quiz = new QuizDB(name, description, authorID);
		session.setAttribute("quizDB", quiz);
		session.setAttribute("qIndex", 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StartTakingQuiz.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
