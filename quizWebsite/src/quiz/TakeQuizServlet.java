package quiz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Question.*;
import web.*;

/**
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeQuizServlet() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int qIndex = (Integer) session.getAttribute("qIndex");
		System.out.println(qIndex);
		String jsp=null;
		if (qIndex == 0) {
			QuizDB quiz = (QuizDB) session.getAttribute("quizDB");
			List<Question> qList = (ArrayList<Question>) quiz.generateQuestions();
			session.setAttribute("qList", qList);
			jsp = qList.get(qIndex).getJspName();
			ArrayList<Integer> answersCorrectness = new ArrayList<Integer>();
			session.setAttribute("answersCorrectness", answersCorrectness);
			session.setAttribute("startTime", System.currentTimeMillis());
		} else {
			List<Question> qList = (ArrayList<Question>) session.getAttribute("qList");
			checkAnswer(qList,request,qIndex);
			if (qIndex == qList.size()){
				Account account = (Account) session.getAttribute("account");
				int accountID = account.getId();
				int quizID = ((QuizDB) session.getAttribute("quizDB")).getID();
				long endTime = System.currentTimeMillis();
				long startTime = (Long) session.getAttribute("startTime");
				long quizTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
				int score = countCorrectAnswers((ArrayList<Integer>) session.getAttribute("answersCorrectness"));
				System.out.println(score+" scoreeeeee");
				//MyDB.addQuizResult(accountID,quizID,score,quizTimeInSeconds);
				QuizDB quiz = (QuizDB) session.getAttribute("quizDB");
				request.setAttribute("quizName", quiz.getName());
				request.setAttribute("score", score);
				request.setAttribute("fullScore", quiz.getTotalScore());
				request.setAttribute("quizTimeInSeconds", quizTimeInSeconds);
				session.removeAttribute("startTime");
				session.removeAttribute("answersCorrectness");
				session.removeAttribute("qList");
				session.removeAttribute("qIndex");
				session.removeAttribute("quizDB");
				jsp = "QuizDone.jsp";
			} else {
				jsp = qList.get(qIndex).getJspName();
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	private int countCorrectAnswers(ArrayList<Integer> answers) {
		int count=0;
		for (int i=0; i<answers.size(); i++)
			count+=answers.get(i);
		return count;
	}

	@SuppressWarnings({ "unchecked"})
	private void checkAnswer(List<Question> qList, HttpServletRequest request, int qIndex) {
		qIndex--;
		Question q = qList.get(qIndex);
		ArrayList<Integer> answersCorrectness = (ArrayList<Integer>) request.getSession(true).getAttribute("answersCorrectness");
		int score=0;
		switch(q.getType()){
		case QuestionFinals.QUESTION_RESPONSE:
		case QuestionFinals.IMAGE_QUESTION:
			String answer = (String) request.getParameter("field1");
			ArrayList<String> correctAnswers = (ArrayList<String>) q.getCorrectAnswer().getAnswer();
			System.out.println("amdeni shesadzlo swori pasuxi maqvs: "+correctAnswers.size());
			for (int i=0; i<correctAnswers.size(); i++){
				System.out.println(correctAnswers.get(i)+" 213moi1nuvenb98fbaw987dba");
				if (correctAnswers.get(i).equals(answer)){
					score = q.getScore();
					break;
				}
			}
			break;
		case QuestionFinals.MULTIPLE_CHOICE:
			answer = (String) request.getParameter("answer");
			if (answer != null && ((String)q.getCorrectAnswer().getAnswer()).equals(answer))
					score=q.getScore();
			break;
		case QuestionFinals.MULTIPLE_ANSWER:
			ArrayList<String> answers = (ArrayList<String>) request.getAttribute("answer");
			ArrayList<List<String> > correctAnswersMA = (ArrayList<List<String> >) q.getCorrectAnswer().getAnswer();
			int countCorrects=0;
			if (answers.size() == correctAnswersMA.size()){
				for (int i=0; i<answers.size(); i++){
					ArrayList<String> variants = (ArrayList<String>) correctAnswersMA.get(i);
					for (int j=0; i<variants.size(); j++){
						if (variants.get(j).equals(answers.get(i))){
							countCorrects++;
							break;
						}
					}
				}
				if (countCorrects==answers.size())
					score=q.getScore();
			}
			break;
		case QuestionFinals.MCMA:
			answers = (ArrayList<String>) request.getAttribute("answer");
			correctAnswers = (ArrayList<String>) q.getCorrectAnswer().getAnswer();
			if (answers.size() == correctAnswers.size()){
				Set<String> sAnswers = new HashSet<String>(answers);
				Set<String> sCorrectAnswers = new HashSet<String>(correctAnswers);
				if (sAnswers.containsAll(sCorrectAnswers))score = q.getScore();
			}
			break;
		case QuestionFinals.MATCHING:
			
			break;
		default:;
		}
		System.out.println(qIndex+ " am kitxvaze "+score+" es score aighe");
		answersCorrectness.add(score);
		request.getSession().setAttribute("answersCorrectness", answersCorrectness);
	}

}
