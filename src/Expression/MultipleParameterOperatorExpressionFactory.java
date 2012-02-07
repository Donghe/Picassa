package Expression;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;

/**
 * determine and create the Expression of operators with two parameters
 * 
 * @author Donghe Zhao
 */
public class MultipleParameterOperatorExpressionFactory extends ExpressionFactory {

	int myCurrentPosition;
	private ArrayList<String> Operator = new ArrayList<String>(); // the list of
																	// the
																	// Expression
																	// of
																	// operators
																	// with two
																	// parameters

	public MultipleParameterOperatorExpressionFactory() {
		Operator.add("sum");
		Operator.add("product");
		Operator.add("average");
		Operator.add("min");
		Operator.add("max");
	}

	// expression begins with a left paren followed by the command name,
	// which is a sequence of alphabetic characters
	 private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
	 .compile("\\(([a-zA-Z]+)");

	public Expression CreateExpression(String Input, int CurrentPosition) {
		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(Input);
		expMatcher.find(CurrentPosition);
		String commandName = expMatcher.group(1); // the command
		myCurrentPosition = expMatcher.end();
		
		ArrayList<Expression> OperandList=new ArrayList<Expression>();
		
		while(currentCharacter(Input, myCurrentPosition) != ')'){
			OperandList.add(Parser.getExpressionType(Input, myCurrentPosition));
			myCurrentPosition = Parser.getMyCurrentPosition();
		}

		skipWhiteSpace(Input, myCurrentPosition);
		if (currentCharacter(Input, myCurrentPosition) == ')') {
			myCurrentPosition++;
			return new MultipleParameterOperatorExpression(commandName, OperandList);
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
