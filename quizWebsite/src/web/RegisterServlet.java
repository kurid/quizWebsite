package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// HttpSession ses = request.getSession(true);
		
		// Account acc = new Account(request.getParameter("id"));
		AccountManager m = new AccountManager();
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String nickname = request.getParameter("nickname");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		int result = m.addAccount(name, surname, nickname, password, mail);
		String jsp = "Registration.jsp";
		String errorText = "";
		if (result == AccountManager.NICKNAME_EXISTS) {
			errorText = "Such nickname already exists. "
					+ "Please try another one";
		}else if (result == AccountManager.MAIL_EXISTS) {
			errorText = "Such mail already exists. "
					+ "Please try another one";		
		}else if (result == AccountManager.INCORRECT_MAIL) {
			errorText = "Email addres is not valid.";		
		}else{		
			request.getSession(true).setAttribute("account", new Account(result));
			jsp = "HomePage.jsp";		
		}
		System.out.println(errorText);
		request.getSession(true).setAttribute("RegisterText", errorText);
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
