package sg.edu.nus.comp.cs4218.misc;

public final class SignChecker {
	public final static String NEGATE = "-";
	public final static String EQUALSTR = "=";
	public final static String NOTEQUALSTR = "!=";
	public final static String GREATERSTR = ">";
	public final static String LESSSTR = "<";
	public final static String NOTSTR = "!";
	public final static String ANDSTR = "&&";
	public final static String ORSTR = "||";
	public final static char EQUAL = '=';
	public final static char GREATER = '>';
	public final static char LESS = '<';
	public final static char PLUS = '+';
	public final static char MINUS = '-';
	public final static char DIVIDE = '/';
	public final static char TIMES = '*';
	public final static char POW = '^';
	public final static char OPENBRAC = '(';
	public final static char NOT_SIGN = '!';
	public final static char AND_SIGN = '&';
	public final static char OR_SINGLE_SIGN = '|';
	public final static String DOT = ".";
	public final static int ZERO = 0;
	public final static int ONE = 1;
	public final static int TWO = 2;

	private SignChecker() {
	}

	/**
	 * This method checks if a string is either an integer or a float
	 * 
	 * @param input
	 *            input string
	 * @return boolean True if it is an integer/float else false
	 */
	public static boolean isNumeric(String input) {
		boolean result = true;
		if (input.contains(DOT)) {
			try {
				Float.parseFloat(input);
			} catch (Exception e) {
				result = false;
			}
		} else {
			for (int i = 0; i < input.length(); i++) {
				if (!Character.isDigit(input.charAt(i))) {
					result = false;
				}
			}
		}

		return result;
	}

	/**
	 * This method performs the negation of an expression.
	 * 
	 * @param input
	 *            input string
	 * @return string
	 * 
	 */
	public static String negation(String input) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (input.charAt(ZERO) == MINUS) {
			stringBuilder.append(input.substring(ONE, input.length()));
		} else {
			stringBuilder.append('-').append(input);
		}
		return stringBuilder.toString();
	}

	/**
	 * This method checks if a character is an operator of the following without
	 * quotes " + - / ^ "
	 * 
	 * @param currChar
	 *            input character
	 * @return boolean
	 */
	public static boolean isOperator(char currChar) {
		boolean result = false;
		if (currChar == PLUS || currChar == MINUS || currChar == DIVIDE
				|| currChar == TIMES || currChar == POW) {
			result = true;
		}
		return result;
	}

	/**
	 * This method checks if the input character is part of a
	 * relational/conditional character. It is defined as one of the following
	 * without quotes "< > ? ! ="
	 * 
	 * @param currChar
	 * @return
	 */
	public static boolean isRelationCondtional(char currChar) {
		boolean result = false;
		if (currChar == LESS || currChar == GREATER || currChar == EQUAL
				|| currChar == NOT_SIGN || currChar == AND_SIGN
				|| currChar == OR_SINGLE_SIGN) {
			result = true;
		}
		return result;
	}
}
