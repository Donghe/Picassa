package Expression;

import model.RGBColor;
import model.util.ColorCombinations;

/**
 * the Expression of operators with one parameter
 * 
 * @author Donghe Zhao
 */

public class OneParameterOperatorExpression extends Expression {
	
	private String myCommand;
	private Expression myOperand1;
	
	/**
	 * Create expression representing the given operation between the two given
	 * sub-expressions.
	 */
	public OneParameterOperatorExpression(String Command, Expression Operand1) {
		myCommand = Command;
		myOperand1 = Operand1;
	}

	public OneParameterOperatorExpression() {
		// TODO Auto-generated constructor stub
	}

	public RGBColor evaluate(double DomainX, double DomainY,double CurrentTime) {
			RGBColor left = myOperand1.evaluate(DomainX, DomainY,CurrentTime);
			return ColorCombinations.doOneParameterOperator(myCommand, left);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(myOperand1.toString());
		result.append(")");
		return result.toString();
	}

}