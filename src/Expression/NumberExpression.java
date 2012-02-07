package Expression;

import model.RGBColor;

/**
 * the Expression of number
 * 
 * @author Donghe Zhao
 */
public class NumberExpression extends Expression {

	private RGBColor myValue;
	private String myCommand;

	public NumberExpression(String Command, RGBColor Value) {
		myCommand = Command;
		myValue = Value;
	}

	public NumberExpression() {
	}

	public RGBColor evaluate(double DomainX, double DomainY, double CurrentTime) {
		if (myCommand == null) {
			return myValue;
		} else
			return null;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(myValue);

		return result.toString();
	}

}
