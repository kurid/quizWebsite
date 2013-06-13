package Question;

import java.util.Set;

/**
 * Interface of questions, which has several possible answers already given. 
 */
public interface ChooseQuestion {
	Set<String>  getPossibleAnswers();
}
