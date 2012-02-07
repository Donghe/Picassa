package Expression;

import model.ParserException;
import model.RGBColor;
import model.ParserException.Type;

/**
 * the Expression of variables until now it is made up of "x", "y" and "t"
 * 
 * @author Donghe Zhao
 */
public class VariablesExpression extends Expression {

	private String myCommand;

	public VariablesExpression(String Command) {
		myCommand = Command;
	}

	public VariablesExpression() {
		// TODO Auto-generated constructor stub
	}

	public RGBColor evaluate(double DomainX, double DomainY, double CurrentTime) {
		if (myCommand.equals("x"))
			return new RGBColor(DomainX);
		else if (myCommand.equals("y")) {
			return new RGBColor(DomainY);
		} else if (myCommand.equals("t")) {
			return new RGBColor(CurrentTime);
		} else
			throw new ParserException("Unknown Command " + myCommand,
					Type.UNKNOWN_COMMAND);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(")");

		return result.toString();
	}

}