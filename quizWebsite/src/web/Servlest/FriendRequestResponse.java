package web.Servlest;

import java.io.IOException;

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
 * Servlet implementation class FriendRequestResponse
 */
@WebServlet("/FriendRequestResponse")
public class FriendRequestResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequestResponse() {
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
		boolean accept = false;
		String answer = request.getParameter("act");
		if (answer.equals("Accept"))
			accept=true;
		Account myAccount = (Account) Session.getAttribute("account");
		Boolean isLookingUp = (Boolean) Session.getAttribute("isLookingUp");
		String destination = null;
		if (isLookingUp){
			Account userAccount = (Account) Session.getAttribute("userAccount");
			if (accept)
				MyDB.addFriend(myAccount.getId(), userAccount.getId());
			MyDB.deleteFriendRequest(userAccount.getId(),myAccount.getId());
			destination = "UserProfile.jsp";
			Session.removeAttribute("counterFriendRequestExists");
			Session.setAttribute("isFriend", accept);
		} else {
			destination = "FriendRequestServlet";
			int senderID = Integer.parseInt(request.getParameter("senderID"));
			if (accept)
				MyDB.addFriend(myAccount.getId(), senderID);
			MyDB.deleteFriendRequest(senderID,myAccount.getId());		
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}
