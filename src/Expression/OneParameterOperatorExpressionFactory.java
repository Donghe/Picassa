package Expression;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;

/**
 * determine and create the Expression of operators with one parameter
 * 
 * @author Donghe Zhao
 */
public class OneParameterOperatorExpressionFactory extends ExpressionFactory {

	int myCurrentPosition;
	private ArrayList<String> Operator = new ArrayList<String>(); // the list of
																	// Expression
																	// with one
																	// parameter

	public OneParameterOperatorExpressionFactory() {
		Operator.add("neg");
		Operator.add("floor");
		Operator.add("ceil");
		Operator.add("abs");
		Operator.add("clamp");
		Operator.add("wrap");
		Operator.add("sin");
		Operator.add("cos");
		Operator.add("tan");
		Operator.add("atan");
		Operator.add("log");
		Operator.add("rgbToYCrCb");
		Operator.add("yCrCbtoRGB");
		Operator.add("!");
	}

	// expression begins with a left paren followed by the command name,
	// which is a sequence of alphabetic characters
	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-zA-Z!]+)");

	public Expression CreateExpression(String Input, int CurrentPosition) {
		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(Input);
		expMatcher.find(CurrentPosition);
		String commandName = expMatcher.group(1); // the command
		myCurrentPosition = expMatcher.end();

		Expression left = Parser.getExpressionType(Input, myCurrentPosition); // the
																				// parameter
																				// one
		myCurrentPosition = Parser.getMyCurrentPosition();

		skipWhiteSpace(Input, myCurrentPosition);
		if (currentCharacter(Input, myCurrentPosition) == ')') {
			myCurrentPosition++;
			return new OneParameterOperatorExpression(commandName, left);
		}

		else {
			throw new ParserException("Expected close paren, instead found "
					+ Input.substring(myCurrentPosition));
		}

	}

	public boolean isThisKindOfExpression(String Input, int CurrentPosition) {
		if (Input.charAt(CurrentPosition) != '(')
			return false;
		String commandName = "";
		int position = CurrentPosition + 1;
		while (Input.charAt(position) != ' ' && Input.charAt(position) != '(') {
			commandName += Input.charAt(position);
			position++;
		}
		System.out.println(commandName);

		for (String s : Operator) {
			if (commandName.equals(s))
				return true;
		}
		return false;
	}

	public int getMyCurrentPosition() {
		return myCurrentPosition;
	}
}
