package web.Servlest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manager m = new AccountManager();
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String nickname = request.getParameter("nickname");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		int result = m.addAccount(name, surname, nickname, password, mail);
		String jsp = "Registration.jsp";
		String errorText = "";
		if(result == Manager.EMPTY_FIELD){
			errorText = "text field is empty";
		}else if (result == Manager.NICKNAME_EXISTS) {
			errorText = "Such nickname already exists. "
					+ "Please try another one";
		}else if (result == Manager.MAIL_EXISTS) {
			errorText = "Such mail already exists. "
					+ "Please try another one";		
		}else if (result == Manager.INCORRECT_MAIL) {
			errorText = "Email addres is not valid.";		
		}else if(result == Manager.SHORT_NICKNAME){
			errorText = "Nickname is too short";
		}else if(result == Manager.LONG_NICKNAME){
			errorText = "Nickname is too long";
		}else{		
			request.getSession(true).setAttribute("account", new Account(result));
			request.getSession(true).setAttribute("isLoggedIn", true);			
			jsp = "HomePage";		
		}
		request.getSession(true).setAttribute("registerText", errorText);
		response.sendRedirect(jsp);
	}

}
