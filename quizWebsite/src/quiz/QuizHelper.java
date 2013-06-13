package quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import web.MyDB;

public class QuizHelper {
	/**
	 * 
	 * @param context
	 * aganerirebs popularul quizebs imis mixedvit ramden adamians aqvs es quiz gavlili
	 * 
	 */
	public static void updatePopularQuizes(ServletContext context){
		ResultSet resultset = MyDB.popularQuizzes();
		List<QuizDB> quizzes = new ArrayList<QuizDB>();
		try {
			while (resultset.next()) {
				String quizName = resultset.getString("name");
				String description = resultset.getString("description");
				int authorID = resultset.getInt("authorID");
				int quizID = resultset.getInt("quizID");
				quizzes.add(new QuizDB(quizName, description, authorID,quizID));
			}
		} catch (SQLException e) {
			System.out.println("Error in updatePopularQuizes");
			e.printStackTrace();
		}
		context.setAttribute("popularQuizzes", quizzes);
	}
	/**
	 * 
	 * @param context
	 * 
	 * agenerirebs bolos sheqminil, uaxles quizzes
	 */
	public static void updateNewQuizes(ServletContext context){
		ResultSet resultset = MyDB.newQuizzes();
		List<QuizDB> quizzes = new ArrayList<QuizDB>();
		try {
			while (resultset.next()) {
				String quizName = resultset.getString("name");
				String description = resultset.getString("description");
				int authorID = resultset.getInt("authorID");
				int quizID = resultset.getInt("quizID");
				quizzes.add(new QuizDB(quizName, description, authorID,quizID));
			}
		} catch (SQLException e) {
			System.out.println("Error in updateNewQuizes");
			e.printStackTrace();
		}
		context.setAttribute("newQuizzes", quizzes);
	}
}
