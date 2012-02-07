package model;

import java.util.ArrayList;
import java.util.List;

import Expression.Expression;
import Expression.ExpressionFactory;
import Expression.MultipleParameterOperatorExpressionFactory;
import Expression.NumberExpressionFactory;
import Expression.OneParameterOperatorExpressionFactory;
import Expression.TwoParameterOperatorExpressionFactory;
import Expression.SpecialExpressionFactory;
import Expression.VariablesExpressionFactory;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * 
 * @author Donghe Zhao
 */
public class Parser {

	// state of the parser
	private static int myCurrentPosition;
	private static String myInput;
	private static List<ExpressionFactory> ExpressionKinds = new ArrayList<ExpressionFactory>(); // the
																							// list
																							// of
																							// children
																							// class
																							// of
																							// Expression
																							// Factory

	public Parser() {
		ExpressionKinds.add(new NumberExpressionFactory());	
		ExpressionKinds.add(new VariablesExpressionFactory());
		ExpressionKinds.add(new TwoParameterOperatorExpressionFactory());
		ExpressionKinds.add(new OneParameterOperatorExpressionFactory());
		ExpressionKinds.add(new MultipleParameterOperatorExpressionFactory());
		ExpressionKinds.add(new SpecialExpressionFactory());
		

	}

	/**
	 * Converts given string into expression tree.
	 * 
	 * @param input
	 *            expression given in the language to be parsed
	 * @return expression tree representing the given formula
	 */
	public Expression makeExpression(String input) {
		myInput = input;
		myCurrentPosition = 0;
		Expression result = getExpressionType(myInput, myCurrentPosition);
		skipWhiteSpace();
		if (notAtEndOfString()) {
			throw new ParserException(
					"Unexpected characters at end of the string: "
							+ myInput.substring(myCurrentPosition),
					ParserException.Type.EXTRA_CHARACTERS);
		}
		return result;
	}

	/**
	 * determine which type of expression and create it
	 */
	public static Expression getExpressionType(String Input, int CurrentPosition) {
		myCurrentPosition = CurrentPosition;
		myInput = Input;
		skipWhiteSpace();

		for (ExpressionFactory e : ExpressionKinds) {
			if (e.isThisKindOfExpression(Input, myCurrentPosition)) {
				Expression result = e
						.CreateExpression(Input, myCurrentPosition);
				myCurrentPosition = e.getMyCurrentPosition();
				return result;
			}
		}
		throw new ParserException("Unexpected Character " + currentCharacter());
	}

	private static void skipWhiteSpace() {
		while (notAtEndOfString() && Character.isWhitespace(currentCharacter())) {
			myCurrentPosition++;
		}
	}

	private static char currentCharacter() {
		return myInput.charAt(myCurrentPosition);
	}

	private static boolean notAtEndOfString() {
		return myCurrentPosition < myInput.length();
	}

	public static int getMyCurrentPosition() {
		return myCurrentPosition;
	}
}
