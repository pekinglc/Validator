//student ID: 2034032  name: chang liu
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
	
	// get the ending of an email address
	// String strEmail : represent the email string to fetch the ending
	// assume it contains only one @ symbol
	public static String fetchAfterAt(String strEmail) {
		//store the position of @ symbol
		int atPostion = strEmail.indexOf("@");
		if(atPostion == strEmail.length() - 1)//if @ symbol is the last character of the email
			return "";
		else 
			return strEmail.substring(atPostion + 1);
	}
	
	// check if the start of a string is valid email prefix
	// String strEmail : represent the email string to validate
	public static boolean isPrefix(String strEmail) {
		// fetch the string before @ sign
		String strBeforeAt = fetchBeforeAt(strEmail);
		// calculate the length of the prefix of email
		int strLength = strBeforeAt.length();
		// if the prefix has no character or the first character is not alphanumeric,return false
		if(strLength == 0 || !isAlphaNum(strBeforeAt.charAt(0)))
			return false;
		// iterate every character of prefix	
		for(int i = 1; i < strLength; i ++) {
			char charEvery  = strBeforeAt.charAt(i);
			if(!isPrefixChar(charEvery))// if character is not prefix character,return false
					return false;
			else {
				if(isSpecialChar(charEvery, true)) {//if character is special character
					// if the special character is the last character of prefix, return false
					if(i == strLength - 1)
						return false;
					else {
						char specialAfter = strBeforeAt.charAt(i + 1);
						// if the character after the special character is alphanumeric,return false 
						if(!isAlphaNum(specialAfter))
							return false;
							
					}
				}
			}
		
		}
		// if no condition is broken, then return true	
		return true;
		
	}
	
	// check if the end of a string is a valid email domain
	// String strEmail : represent the email string to validate
	public static boolean isDomain(String strEmail) {
		// fetch the string after @ symbol
		String strDomain = fetchAfterAt(strEmail);
		// get the length of the domain string
		int domainLength = strDomain.length();
		// contain at least 4 characters:first portion one, second portion two, seperated by period
		if(domainLength < 4)
			return false;
		// get the position of last period since the second portion contains only letters
		int periodPostion = strDomain.lastIndexOf(".");
		// if no period or the number of characters after period is less than 2
		if(periodPostion <= 0 || periodPostion >= domainLength - 2)
			return false;
		// get the first portion of the domain
		String strBeforePeriod = strDomain.substring(0,periodPostion);
		// get the second portion of the domain
		String strAfterPeriod = strDomain.substring(periodPostion+1);
		// check if the first portion meet the conditions required
		for(int i = 0; i < strBeforePeriod.length(); i++) {
			char charAtI = strBeforePeriod.charAt(i);
			if(!isDomainChar(charAtI))// if the character is not domain character,return false
				return false;
			//period or dash must be followed by one or more alphanumeric characters
			if(isSpecialChar(charAtI, false)) {
				// if period or dash at the end, return false
				if(i == strBeforePeriod.length() - 1)
					return false;
				//if period or dash not followed by one or more alphanumeric characters,return false
				if(!isAlphaNum(strBeforePeriod.charAt(i + 1)))
					return false;
			}
				
			
		}
		// check if the second portion contains only letters
		for(int i = 0; i < strAfterPeriod.length(); i ++) {
			if(!Character.isLetter(strAfterPeriod.charAt(i)))
				return false;
			
		}
		// if all the conditions are met,return true
		return true;
	}
	
	// check if a string is a valid email 
	public static boolean isEmail(String strEmail) {
		if(!singleAtSign(strEmail))// must contain only one @ symbol
			return false;
		if(!isPrefix(strEmail))// prefix must be in acceptable format
			return false;
		if(!isDomain(strEmail))// domain must be in acceptable format
			return false;
		//if all the conditions are met	
		return true;
			
		
	}
	
	// check if a string is a valid username
	public static String isUsername(String strUsername) {
		char[] charUsername = strUsername.toCharArray();
		// flag whether contain alphanumeric character, initialize to false
		boolean booAlphaNum = false;
		// flag whether contains only alphanumeric, periods, dashes or an exclamation point.
		boolean booLegalLetter = true;
		// initialize the number of exclamation point variable to 0
		int countExclamation = 0;
		// must contain seven or less characters, but at least one
		if(charUsername.length > 7 || charUsername.length < 1)
			return "";//if condition not followed, return empty string
		// must start with a period or dash
		if(!isSpecialChar(charUsername[0], false))
			return "";
		
		for(int i = 0; i < charUsername.length; i ++) {
			char c = charUsername[i];
			if(isAlphaNum(c)) {// if alphanumeric character
				if(!booAlphaNum)
					booAlphaNum = true;
			}else {// if special character or an exclamation point
				if(!isSpecialChar(c, false) && !(c == '!'))
					return "";
				if(c == '!')// if exclamation point, count ++
					countExclamation ++;
			}
			
		}
		// if contains only alphanumeric, periods, dashes or an exclamation point. 
		// and at least one alphanumeric, return lower case valid username
		if(booAlphaNum && booLegalLetter && countExclamation <= 1)
			return strUsername.toLowerCase();
		else
			return "";
			
	}
	
	// check if a string is considered a safe password
	// String strPwd: represent the password to validate
	public static boolean safePassword(String strPwd) {
		char[] charPwd = strPwd.toCharArray();
		// flag whether contains alphanumeric character
		boolean booAlphaNum = false;
		//flag whether contains upper case letter
		boolean booUpperLetter = false;
		//flag whether contains lower case letter
		boolean booLowerLetter = false;
		//flag whether contains number
		boolean booNumber = false;
		//flag whether contains special character" period,dash or underscore
		boolean booSpecialChar = false;
		// must contains minimum 7 maximum 15 characters
		if(charPwd.length < 7 || charPwd.length > 15)
			return false;
		String patternIdenticalChar = "(.)\\1{2,}";
		Pattern strPattern = Pattern.compile(patternIdenticalChar);
		Matcher strMatcher = strPattern.matcher(strPwd);
		while(strMatcher.find()) {
			System.out.println(strMatcher.group());
			return false;
		}
		
		for(char c : charPwd) {
			if(Character.isUpperCase(c) && !booUpperLetter) {
				booUpperLetter = true;
				if(!booAlphaNum)// if character is uppercase and booAlphaNum is false
					booAlphaNum = true;
				continue;
			}
			// if character is lowercase and booLowerLetter is false
			if(Character.isLowerCase(c) && !booLowerLetter) {
				booLowerLetter = true;
				if(!booAlphaNum)
					booAlphaNum = true;
				continue;
			}
			// if character is digit and booNumber is false
			if(Character.isDigit(c) && !booNumber ) {
				booNumber = true;
				if(!booAlphaNum)
					booAlphaNum = true;
				continue;
				
			}
			// if character is period,dash or underscore and booSpecialChar is false
			if(isSpecialChar(c, true) && !booSpecialChar) {
				booSpecialChar = true;
				continue;
				
			}
				
		}
		// if all the conditions are met
		if(booAlphaNum && booUpperLetter && booLowerLetter && booSpecialChar && booNumber) 
			return true;
		else 
			return false;

	}
}