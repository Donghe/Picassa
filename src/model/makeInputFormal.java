package model;

/**
 * change the input into another type which replace all "let" command and its
 * vername with the value such as "(let foo 3 (plus   foo (  let foo 2 foo)))"
 * will be made in formal to "(plus 3 2)" in this case we can make the "let"
 * operator work well
 * 
 * @author Donghe Zhao
 */
public class makeInputFormal {

	private static int letStart;
	private static int letEnd;
	private static String LetMiddle;
	private static int CurrentPosition;
	
	public static String changeLetToNumber(String input) {
		String result = input;
		while (result.indexOf("let") >= 0) {
			String before = beforeLet(result);
			String after = afterLet(result);
			String middle = inLet(result);
			result = before + middle + after;
		}
		return result;
	}

	/**
	 * return the substring before the "let" issue
	 */
	public static String beforeLet(String input) {
		String before = input.substring(0, input.indexOf("let"));
		letStart = before.lastIndexOf('(');
		return before.substring(0, letStart);
	}

	/**
	 * return the substring in the "let" issue
	 */
	public static String inLet(String input) {
		LetMiddle = input.substring(letStart, letEnd);
		CurrentPosition = LetMiddle.indexOf("let") + "let".length();
		String varname = getOneExpression();
		String value = getOneExpression();
		String expressionWithVarname = LetMiddle.substring(CurrentPosition,
				LetMiddle.lastIndexOf(")"));
		return expressionWithVarname.replace(varname, value);
	}

	/**
	 * return the substring after the "let" issue
	 */
	public static String afterLet(String input) {
		int index = input.indexOf("let");
		letEnd = indexOfBracketEnd(input, index);
		return input.substring(letEnd);
	}

	/**
	 * from the index of the input string find the end position where its
	 * Brackets are in pair
	 */
	public static int indexOfBracketEnd(String input, int index) {
		int myindex = index;
		int leftBracketNum = 0;
		while (leftBracketNum >= 0) {
			if (input.charAt(myindex) == '(')
				leftBracketNum++;
			else if (input.charAt(myindex) == ')')
				leftBracketNum--;
			myindex++;
		}
		return myindex;
	}

	/**
	 * return a piece of string for varname or value work in inLet
	 */
	public static String getOneExpression() {
		skipWhiteSpace();
		String value = "";
		int indexValueEnd = 0;
		// if it has Bracket; else if it does not have any Bracket
		if (LetMiddle.charAt(CurrentPosition) == '(') {
			indexValueEnd = indexOfBracketEnd(LetMiddle, CurrentPosition + 1);
			value = LetMiddle.substring(CurrentPosition, indexValueEnd);
		} else {
			indexValueEnd = LetMiddle.indexOf(" ", CurrentPosition);
			value = LetMiddle.substring(CurrentPosition, indexValueEnd);
		}
		CurrentPosition = indexValueEnd;
		skipWhiteSpace();
		System.out.println("The value: " + value);
		return value;
	}

	/**
	 * skip white spaces
	 */
	public static void skipWhiteSpace() {
		while (LetMiddle.charAt(CurrentPosition) == ' ') {
			CurrentPosition++;
		}
	}

}
