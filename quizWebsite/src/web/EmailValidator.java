package web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
	
	/**
	 * 
	 * @author User kurid
	 * this class will help to check if mail is valid or not
	 */
	public class EmailValidator {
		 
		private Pattern pattern;
		private Matcher matcher;
	 
		private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 
		public EmailValidator() {
			pattern = Pattern.compile(EMAIL_PATTERN);
		}
		
		public boolean emailIsValid(String email){
			matcher = pattern.matcher(email);
			return matcher.matches();
		}
}
