package web.Servlest;

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

import web.Account;
import web.MyDB;

/**
 * Servlet implementation class GetFriendsServlet
 */
@WebServlet("/GetFriendsServlet")
public class GetFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFriendsServlet() {
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
		HttpSession Session = request.getSession(true);
		Account account = (Account) Session.getAttribute("account");
		List<Integer> friendListIDs = MyDB.getFriends(account.getId());
		List<Account> friendList = new ArrayList<Account>();
		for (int i=0; i<friendListIDs.size(); i++){
			Account tmp = new Account(friendListIDs.get(i));
			friendList.add(tmp);
		}
		request.setAttribute("friendList", friendList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Friends.jsp");
		dispatcher.forward(request, response);
	}

}
