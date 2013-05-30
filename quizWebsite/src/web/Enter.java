package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mbeans.UserMBean;

import com.sun.xml.internal.ws.client.dispatch.MessageDispatch;

/**
 * Servlet implementation class Enter
 */
@WebServlet(description = "when user enters", urlPatterns = { "/Enter" })
public class Enter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enter() {
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
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		AccountManager manager  = new AccountManager();
		String jsp = "unknownUser.jsp";//es jsp etyvis users rom arasworad sheiyvana da axlidan scados
		if(manager.isCorrect(nickname, password)){
			jsp = "HomePage.jsp";
			request.getSession(true).setAttribute("account", new Account(MyDB.getId(nickname)));
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

}
