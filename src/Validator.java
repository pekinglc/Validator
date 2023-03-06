//student ID: 2034032  name: chang liu
import java.util.HashMap;


public class Validator {
	

	public static void main(String[] args) {
//		System.out.println(isAlphaNum(' '));
//		System.out.println(isSpecialChar('s', false));
//		System.out.println(isPrefixChar('A'));
//		System.out.println(isDomainChar('9'));
//		System.out.println(singleAtSign("abcSabc@@"));
//		System.out.println(fetchBeforeAt("abcd@abc"));

	}
	
	// Check if a character is alphanumeric.
	// char charAlphaNum: represent the character to validate
	public static boolean isAlphaNum(char charAlphaNum) {
		return Character.isLetterOrDigit(charAlphaNum);
	}
	
	// Check if a character is an acceptable special character
	// char charSpecial: represent the character to validate
	// boolean underScoreAllow: indicate if underscore is considered as a special character
	public static boolean isSpecialChar(char charSpecial, boolean underScoreAllow) {
		if (underScoreAllow) {//if underscore is considered a special character
			// if character is a dash, period or underscore, it is a special character
			if (charSpecial == '-' || charSpecial == '.' || charSpecial == '_') 
				return true;
			else 
				return false;
		}else {// if underscore is not considered a special character
			// if character is a dash or period, it is a special character
			if (charSpecial == '-' || charSpecial == '.') 
				return true;
			else
				return false;
		}
		
	}
	
	// check if a character is a character allowed in the prefix
	// char prefixChar : represent the character to validate
	public static boolean isPrefixChar(char prefixChar) {
		// if a character is alphanumeric or special character(includes underscore)
		// then it is a prefix character
		return isAlphaNum(prefixChar) || isSpecialChar(prefixChar, true);
		
	}
	
	// check if a character is a character allowed in the domain(second portion)
	// char domainChar : represent the character to validate
	public static boolean isDomainChar(char domainChar) {
		// if a character is alphanumeric or dashes or periods, then it is a domain character
		return isAlphaNum(domainChar) || isSpecialChar(domainChar, false);
	}
	
	// check if a string contain a single at sign(@)
	// String strEmail : represent the string to validate
	public static boolean singleAtSign(String strEmail) {
		//declare and initialize the variable which stores the number of at signs in the email
		int countAtSign = 0;
		// convert the email string to a char array
		char[] emailChar = strEmail.toCharArray();
		// check every character in the string
		for(char c : emailChar) {
			if(c == '@' ) {
				// if the character is at sign, increase the count variable
				countAtSign ++;
				// if the number of at sign is more than one,return false
				if(countAtSign > 1)
					return false;
			}
		}
		//if the email string contain only one at sign, return true
		if(countAtSign == 1)
			return true;
		else 
			return false;
	}
	
	// get the beginning of an email address
	// String strEmail : represent the email string to fetch the beginning
	// assume it contains only one @ symbol
	public static String fetchBeforeAt(String strEmail) {
		// get the position of the @ symbol in the email address
		int atPostion = strEmail.indexOf("@");
		return strEmail.substring(0, atPostion);
	}
}