package Expression;

import java.util.ArrayList;

import model.RGBColor;
import model.util.ColorCombinations;

/**
 * the Expression of operators with two parameters
 * 
 * @author Donghe Zhao
 */

public class MultipleParameterOperatorExpression extends Expression {

	private String myCommand;
	private ArrayList<Expression> myOperandList;

	/**
	 * Create expression representing the given operation between the two given
	 * sub-expressions.
	 */
	public MultipleParameterOperatorExpression(String Command,
			ArrayList<Expression> OperandList) {
		myCommand = Command;
		myOperandList = OperandList;
	}

	public MultipleParameterOperatorExpression() {
		// TODO Auto-generated constructor stub
	}

	public RGBColor evaluate(double DomainX, double DomainY, double CurrentTime) {
		ArrayList<RGBColor> parameterList = new ArrayList<RGBColor>();
		for (Expression e : myOperandList) {
			parameterList.add(e.evaluate(DomainX, DomainY, CurrentTime));
		}
		return ColorCombinations.doMultipleParameterOperator(myCommand,
				parameterList);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(");
		result.append(" " + myCommand + " ");
		for (Expression e : myOperandList) {
			result.append(e.toString());
		}
		result.append(")");
		return result.toString();
	}

}