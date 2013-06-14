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

import web.Account;
import web.Message;
import web.MyDB;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
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
		Account user = (Account)request.getSession(true).getAttribute("account");
		List<Message> messages = MyDB.getMessages(user.getId());
		request.setAttribute("messages", messages);
		List<String> names = new ArrayList<String>();
		for(int i = 0; i < messages.size(); i++){
			names.add(MyDB.getNickName(messages.get(i).sender()));
			MyDB.markAsRead(messages.get(i).reciever());
		}
		request.setAttribute("names", names);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ShowMessages.jsp");
		dispatcher.forward(request, response);
	}

}
