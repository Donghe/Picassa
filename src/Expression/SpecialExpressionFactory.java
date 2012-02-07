package Expression;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;

/**
 * determine and create the Expression of special operator
 * 
 * @author Donghe Zhao
 */
public class SpecialExpressionFactory extends ExpressionFactory {

	int myCurrentPosition;
	ArrayList<String> Special = new ArrayList<String>(); // the list of the
															// expression with
															// special operator

	public SpecialExpressionFactory() {
		Special.add("color");
		Special.add("random");
		Special.add("if");
	}

	// expression begins with a left paren followed by the command name,
	// which is a sequence of alphabetic characters
	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-z]+)");

	@Override
	public Expression CreateExpression(String Input, int CurrentPosition) {
		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(Input);
		expMatcher.find(CurrentPosition);
		String commandName = expMatcher.group(1); // the command
		myCurrentPosition = expMatcher.end();

		if (currentCharacter(Input, myCurrentPosition) == ')') { // determine
																	// whether
																	// it is a
																	// command
																	// without
																	// any
																	// parameter
			myCurrentPosition++;
			return new SpecialExpression(commandName, null, null, null);
		}

		Expression left = Parser.getExpressionType(Input, myCurrentPosition); // parameter
																			// one
		myCurrentPosition = Parser.getMyCurrentPosition();
		Expression right = Parser.getExpressionType(Input, myCurrentPosition); // parameter
																				// two
		myCurrentPosition = Parser.getMyCurrentPosition();
		Expression last = Parser.getExpressionType(Input, myCurrentPosition); // parameter
																			// three
		myCurrentPosition = Parser.getMyCurrentPosition();
		skipWhiteSpace(Input, myCurrentPosition);

		if (currentCharacter(Input, myCurrentPosition) == ')') {
			myCurrentPosition++;
			return new SpecialExpression(commandName, left, right, last);
		}

		else {
			throw new ParserException("Expected close paren, instead found "
					+ Input.substring(myCurrentPosition));
		}

	}

	public boolean isThisKindOfExpression(String Input, int CurrentPosition) {
		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(Input);
		expMatcher.find(CurrentPosition);
		String commandName = expMatcher.group(1);
		for (String s : Special) {
			if (commandName.equals(s))
				return true;
		}
		return false;
	}

	public int getMyCurrentPosition() {
		return myCurrentPosition;
	}
}
