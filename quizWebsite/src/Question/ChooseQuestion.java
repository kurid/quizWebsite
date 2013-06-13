package Question;

import java.util.Set;

/**
 * Interface of questions, which have several possible answers already given. 
 */

public interface ChooseQuestion {
	Set<String>  getPossibleAnswers();
}
