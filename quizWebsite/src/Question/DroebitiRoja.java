package Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import web.MyDB;

public class DroebitiRoja implements QuestionFinals{
	public Question getFullQuestionFromID(int questionID) throws SQLException{
		ResultSet res = MyDB.getQuestionInfo(questionID);
		int type = res.getInt("type");
		String text = res.getString("questionText");
		int score = res.getInt("score");
		int num = res.getInt("num");
		Question q;
		ResultSet resHelper;
		switch(type){
		case QUESTION_RESPONCE:
			resHelper = MyDB.answers(questionID);
			ArrayList<String> arr = new ArrayList<String>();
			while(resHelper.next()){
				String tmp = resHelper.getString("answer");
				arr.add(tmp);
			}
			CorrectAnswer ca = new MultipleAnswer(arr);
			q = new QuestionResponse(num,text,ca,score);
			break;
		case MULTIPLE_CHOICE:
			resHelper = MyDB.MultipleChoice(questionID);
			String answer = null;
			arr = new ArrayList<String>();
			while (resHelper.next()){
				String ans = resHelper.getString("answer");
				boolean isCorrect = resHelper.getBoolean("isCorrect");
				if (isCorrect) answer = ans;
				arr.add(ans);
			}
			ca = new SingleAnswer(answer);
			q = new MultipleChoiceQuestion(num,text,new HashSet<String>(arr),ca,score);
		case MULTIPLE_ANSWER:
			resHelper = MyDB.answers(questionID);
			arr = new ArrayList<String>();
			ArrayList<Integer> arrHelper = new ArrayList<Integer>();
			while (resHelper.next()){
				String tmp = resHelper.getString("answer");
				arr.add(tmp);
				int tmp2 = resHelper.getInt("answerNum");
				arrHelper.add(tmp2);
			}
			HashMap<Integer, ArrayList<String> > hm = new HashMap<Integer, ArrayList<String> >();
			for (int i=0; i<arr.size(); i++){
				ArrayList<String> tmp;
				if (hm.containsKey(arrHelper.get(i)))
					tmp = hm.get(arrHelper.get(i));
				else
					tmp = new ArrayList<String>();
				tmp.add(arr.get(i));
				hm.put(arrHelper.get(i), tmp);
			}
			Iterator<Integer> it = hm.keySet().iterator();
			List<List<String>> answers = new ArrayList<List<String> >();
			while (it.hasNext())
				answers.add(hm.get(it.next()));
			ca = new MMAnswer(answers);
			q = new MultiAnswerQuestion(num,text,ca,score);
		case MCMA:
			resHelper = MyDB.MultipleChoice(questionID);
			arr = new ArrayList<String>();
			ArrayList<String> arrCorrect = new ArrayList<String>();
			while (resHelper.next()){
				String ans = resHelper.getString("answer");
				boolean isCorrect = resHelper.getBoolean("isCorrect");
				if (isCorrect) arrCorrect.add(ans);
				arr.add(ans);
			}
			ca = new MultipleAnswer(arrCorrect);
			q = new MCMAQ(num,text,new HashSet<String>(arr),ca,score);			
		case IMAGE:
			resHelper = MyDB.answers(questionID);
			arr = new ArrayList<String>();
			while(resHelper.next()){
				String tmp = resHelper.getString("answer");
				arr.add(tmp);
			}
			ca = new MultipleAnswer(arr);
			String URL = MyDB.getURL(questionID);
			q = new ImageQuestion(num,text,URL,ca,score);
		case MATCHING:
			resHelper = MyDB.getMatching(questionID);
			
		case AUTO_GENERATED:
		default: q=null;
		}
		
		return q;
	}
}
