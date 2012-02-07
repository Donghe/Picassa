package Expression;

import model.RGBColor;
import model.util.ColorCombinations;

/**
 * the Expression of operators with two parameters
 * 
 * @author Donghe Zhao
 */

public class TwoParameterOperatorExpression extends Expression {

	private String myCommand;
	private Expression myOperand1;
	private Expression myOperand2;

	/**
	 * Create expression representing the given operation between the two given
	 * sub-expressions.
	 */
	public TwoParameterOperatorExpression(String Command, Expression Operand1,
			Expression Operand2) {
		myCommand = Command;
		myOperand1 = Operand1;
		myOperand2 = Operand2;
	}

	public TwoParameterOperatorExpression() {
		// TODO Auto-generated constructor stub
	}

	public RGBColor evaluate(double DomainX, double DomainY, double CurrentTime) {
		RGBColor left = myOperand1.evaluate(DomainX, DomainY, CurrentTime);
		RGBColor right = myOperand2.evaluate(DomainX, DomainY, CurrentTime);
		return ColorCombinations.doTwoParameterOperator(myCommand, left, right);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(myOperand1.toString());
		result.append(myOperand2.toString());
		result.append(")");
		return result.toString();
	}

}