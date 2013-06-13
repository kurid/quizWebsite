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
		Account user = (Account)Session.getAttribute("account");
		int clickedID = Integer.parseInt(request.getParameter("ID"));
		boolean isMyAccount = false;
		if (clickedID == user.getId()){
			isMyAccount=true;
		} else {
			Account userAccount = new Account(clickedID);
			Session.setAttribute("userAccount", userAccount);
			ArrayList<Integer> friendList = (ArrayList<Integer>) MyDB.getFriends(user.getId());
			boolean isFriend = checkFriend(friendList,userAccount.getId());
			Session.setAttribute("isFriend", isFriend);
		}
		Session.setAttribute("isLookingUp", isMyAccount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AccountWindow.jsp");
		dispatcher.forward(request, response);
	}

	private boolean checkFriend(ArrayList<Integer> friendList, int id) {
		for (int i=0; i<friendList.size(); i++){
			if (friendList.get(i)==id) return true;
		}
		return false;
	}

}
