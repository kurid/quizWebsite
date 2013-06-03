package Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

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
		case MCMA:
		case IMAGE:
		case MATCHING:
		case AUTO_GENERATED:
		default: q=null;
		}
		
		return q;
	}
}
