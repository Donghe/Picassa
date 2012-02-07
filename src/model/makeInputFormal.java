package model;

import java.util.ArrayList;

/**
 * change the input into another type which replace all "let" command and its
 * vername with the value such as "(let foo 3 (plus   foo (  let foo 2 foo)))"
 * will be made in formal to "(plus 3 2)" in this case we can make the "let"
 * operator work well
 * 
 * @author Donghe Zhao
 */
public class makeInputFormal {

	public static String changeLetToNumber(String input) {
		String result = input;

		while (containsLet(result)) {
			String thisResult = "";
			int ParenthesesNum = 0; // count how many openning parentheses exsit
			int LetPossition = 0; // mark where the "let" is
			String[] words = changeInputToStringArray(result);
			for (int i = 0; i < words.length; i++) { // to find where "let" is
				if (words[i + 1].equals("let")) {
					LetPossition = i + 1;
					break;
				} else {
					thisResult = thisResult + words[i] + " "; // copy all the
																// words before
																// "let"
				}
			}

			// have found "let"

			String varname = words[LetPossition + 1]; // the varname
			String value = words[LetPossition + 2]; // the value start position
			int ExprWithVarnameStart = LetPossition + 3; // the expression with
															// varname start
															// position

			if (value.equals("(")) { // if the value is made up of parentheses
				int num = 1;
				int i = LetPossition + 3;
				while (num > 0) {
					if (words[i].equals("("))
						num++; // count how many openning parentheses exsits
					else if (words[i].equals(")"))
						num--;
					value = value + words[i] + " ";
					i++;
				}
				ExprWithVarnameStart = i; // update the expression with varname
											// start position
			}

			int ExprWithVarnameEnd = ExprWithVarnameStart; // mark the
															// expression with
															// varname end
															// position

			for (int i = ExprWithVarnameStart; i < words.length; i++) {
				if (words[i].equals("("))
					ParenthesesNum++;
				else if (words[i].equals(")"))
					ParenthesesNum--;
				if (words[i].equals(varname))
					thisResult = thisResult + value + " "; // replace varname
															// with value string
				else
					thisResult = thisResult + words[i] + " "; // others will be
																// copied
				if (ParenthesesNum == -1) { // check whether the parentheses is
											// closed, if closed we should stop
					ExprWithVarnameEnd = i + 1;
					break;
				}
			}
			for (int i = ExprWithVarnameEnd; i < words.length; i++) { // after
																		// the
																		// expression
																		// with
																		// varname,
																		// just
																		// copy
				thisResult = thisResult + words[i] + " ";
			}
			result = thisResult;
		}
		return makeResultFormal(result);
	}

	/**
	 * delete the unnecessary parentheses and white space
	 */
	public static String makeResultFormal(String input) {
		String result = "";
		String[] temp = changeInputToStringArray(input);
		String lastWord = "";
		int num = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("(")) {
				result = result + "(";
				num++;
			} else if (temp[i].equals(")")) {
				if (num > 0)
					result = result + ")";
				num--;
			} else {
				if (!lastWord.equals("(") && !lastWord.equals(")"))
					result = result + " ";
				result = result + temp[i] + "";
			}
			lastWord = temp[i];

		}
		return result;
	}

	/**
	 * change the input to an array of string "(" and ")" will both count for an
	 * element even if it is next to other characters
	 */
	public static String[] changeInputToStringArray(String input) {
		ArrayList<String> a = new ArrayList<String>();
		int i = 0;
		while (i < input.length()) {
			if (input.charAt(i) == ' ')
				i++;
			else {
				if (input.charAt(i) == ')' || input.charAt(i) == '(') {
					a.add("" + input.charAt(i));
					i++;
				} else {
					String temp = "";
					while (input.charAt(i) != ' ' && input.charAt(i) != ')'
							&& input.charAt(i) != '(') {
						temp = temp + input.charAt(i);
						i++;
						if (i == input.length())
							break;
					}
					a.add(temp);
				}
			}
		}
		String[] result = new String[a.size()];
		for (int k = 0; k < result.length; k++) {
			result[k] = a.get(k);
		}
		return result;
	}

	/**
	 * check whether the input has "let" command
	 */
	public static boolean containsLet(String input) {
		if (input.length() < 3)
			return false;
		for (int i = 0; i < input.length() - 2; i++) {
			if (input.substring(i, i + 3).equals("let"))
				return true;
		}
		return false;
	}

}
