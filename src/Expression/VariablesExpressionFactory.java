package Expression;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * determine and create the Expression of variables
 * 
 * @author Donghe Zhao
 */
public class VariablesExpressionFactory extends ExpressionFactory {

	int myCurrentPosition;

	ArrayList<String> Variable = new ArrayList<String>();

	public VariablesExpressionFactory() {
		Variable.add("x");
		Variable.add("y");
		Variable.add("t");
	}

	// expression of x or y,
	private static final Pattern EXPRESSION_VARIABLES_REGEX = Pattern
			.compile("([a-zA-Z])");

	@Override
	public Expression CreateExpression(String Input, int CurrentPosition) {
		Matcher variableMatcher = EXPRESSION_VARIABLES_REGEX.matcher(Input);
		variableMatcher.find(CurrentPosition);
		String variableName = variableMatcher.group(1);
		myCurrentPosition = variableMatcher.end();
		return new VariablesExpression(variableName);

	}

	public boolean isThisKindOfExpression(String Input, int CurrentPosition) {
		String variableName = "" + Input.charAt(CurrentPosition);

		for (String s : Variable) {
			if (variableName.equals(s))
				return true;
		}
		return false;
	}

	public int getMyCurrentPosition() {
		return myCurrentPosition;
	}

}
