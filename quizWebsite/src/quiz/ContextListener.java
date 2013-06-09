package quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import web.MyDB;

/**
 * Application Lifecycle Listener implementation class ContexttListener
 * 
 */
@WebListener
public class ContextListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent request) {
		ServletContext context = request.getServletContext();
		popularQuizzes(context);
		newQuizzes(context);
	}

	private void newQuizzes(ServletContext context) {
		ResultSet resultset = MyDB.newQuizzes();
		List<QuizDB> quizzes = new ArrayList<QuizDB>();
		try {
			while (resultset.next()) {
				String quizName = resultset.getString("name");
				String description = resultset.getString("description");
				int authorID = resultset.getInt("authorID");
				quizzes.add(new QuizDB(quizName, description, authorID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		context.setAttribute("newQuizzes", quizzes);

	}

	private void popularQuizzes(ServletContext context) {
		ResultSet resultset = MyDB.popularQuizzes();
		List<QuizDB> quizzes = new ArrayList<QuizDB>();
		try {
			while (resultset.next()) {
				String quizName = resultset.getString("name");
				System.out.println(quizName);
				String description = resultset.getString("description");
				int authorID = resultset.getInt("authorID");
				quizzes.add(new QuizDB(quizName, description, authorID));
			}
		} catch (SQLException e) {
			System.out.println("exeption");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("morcha");
		context.setAttribute("popularQuizzes", quizzes);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

}
