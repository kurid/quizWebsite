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

import web.*;
import Question.Finals;

/**
 * Servlet implementation class ChallengeServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
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
		HttpSession Session = request.getSession(true);
		Account myAccount = (Account)Session.getAttribute("account");
		int clickedID = Integer.parseInt(request.getParameter("ID"));
		boolean isMyAccount = false;
		if (clickedID == myAccount.getId()){
			isMyAccount=true;
			
		} else {
			Account userAccount = new Account(clickedID);
			Session.setAttribute("userAccount", userAccount);
			ArrayList<Integer> friendList = (ArrayList<Integer>) MyDB.getFriends(myAccount.getId());
			boolean isFriend = checkFriend(friendList,userAccount.getId());
			Session.setAttribute("isFriend", isFriend);
			if (!isFriend){
				boolean counterFriendRequestExists = MyDB.friendRequestExists(userAccount.getId(),myAccount.getId());
				Session.setAttribute("counterFriendRequestExists", counterFriendRequestExists);
				boolean friendRequestExists = MyDB.friendRequestExists(myAccount.getId(),userAccount.getId());
				Session.setAttribute("friendRequestExists",friendRequestExists);
			}
		}
		String achievement = getAchievement(clickedID);
		Session.setAttribute("achievement", achievement);
		Session.setAttribute("isLookingUp", !isMyAccount);
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

	private boolean checkFriend(ArrayList<Integer> friendList, int id) {
		for (int i=0; i<friendList.size(); i++){
			if (friendList.get(i)==id) return true;
		}
		return false;
	}

}
