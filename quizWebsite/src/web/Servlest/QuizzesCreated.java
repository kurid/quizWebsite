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
import javax.servlet.http.HttpSession;

import quiz.QuizDB;

import web.Account;
import web.MyDB;

/**
 * Servlet implementation class QuizesCreated
 */
@WebServlet("/QuizzesCreated")
public class QuizzesCreated extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizzesCreated() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		boolean isLookingUp = (Boolean)session.getAttribute("isLookingUp");
		Account user = (Account) session.getAttribute("account");
		if(isLookingUp){
			user = (Account)session.getAttribute("userAccount");
		}
		int ID = user.getId();
		ResultSet res = MyDB.getCreatedQuizes(ID);
		List<QuizDB> quizList = new ArrayList<QuizDB>();
		try {
			while (res.next()){
				int tmpID = res.getInt("quizID");
				int authorID = res.getInt("authorID");
				String tmpName = res.getString("name");
				String description = res.getString("description");
				QuizDB tmp = new QuizDB(tmpName,description,authorID,tmpID);
				quizList.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("quizList", quizList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("QuizzesCreated.jsp");
		dispatcher.forward(request, response);
	}

}
