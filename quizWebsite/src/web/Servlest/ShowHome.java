package web.Servlest;

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

import web.Account;
import web.MyDB;

/**
 * Servlet implementation class ShowHome
 */
@WebServlet("/ShowHome")
public class ShowHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHome() {
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
		HttpSession Session = request.getSession(true);
		Session.setAttribute("isLookingUp", false);
		Session.setAttribute("isFriend", false);
		Session.setAttribute("userAccount", null);
		Session.setAttribute("friendRequestExists", false);
		Account myAccount = (Account) Session.getAttribute("account");
		request.getSession().setAttribute("notificationNum", MyDB.notificationsNum(myAccount.getId()));
		Session.setAttribute("achievement", getAchievement(myAccount.getId()));
		RequestDispatcher dispatcher = request.getRequestDispatcher("AccountWindow.jsp");
		dispatcher.forward(request, response);		
	}
	
	private String getAchievement(int clickedID) {
		ResultSet res = MyDB.getDoneQuizzes(clickedID);
		int count=0;
		try {
			while (res.next())
				count++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count<Question.Finals.NEWBIE) return "No Achievements";
		if (count<Question.Finals.QUIZ_SOLVER) return "Newbie";
		if (count<Question.Finals.EXPERIENCED_SOLVER) return "Quiz solver";
		if (count<Question.Finals.NOT_BAD) return "Experienced solver";
		else return "Not bad, human! let's fuck.";
	}

}
