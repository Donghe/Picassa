package Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.RGBColor;

/**
 * determine and create the Expression of Number
 * 
 * @author Donghe Zhao
 */
public class NumberExpressionFactory extends ExpressionFactory {

	int myCurrentPosition;

	private static final Pattern DOUBLE_REGEX = Pattern
			.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");

	public Expression CreateExpression(String Input, int CurrentPosition) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(Input);
		doubleMatcher.find(CurrentPosition);
		String numberMatch = Input.substring(doubleMatcher.start(),
				doubleMatcher.end()); // the value
		myCurrentPosition = doubleMatcher.end();
		// this represents the color gray of the given intensity
		double value = Double.parseDouble(numberMatch);
		RGBColor gray = new RGBColor(value);
		return new NumberExpression(null, gray);

	}

	public boolean isThisKindOfExpression(String Input, int CurrentPosition) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(Input
				.substring(CurrentPosition));
		return doubleMatcher.lookingAt();
	}

	public int getMyCurrentPosition() {
		return myCurrentPosition;
	}

}
