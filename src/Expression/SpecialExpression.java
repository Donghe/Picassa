package Expression;

import model.ParserException;
import model.RGBColor;
import model.ParserException.Type;
import model.util.ColorCombinations;
/**
 * the Expression of special operator
 * until now it includes "random" and "color"
 * 
 * @author Donghe Zhao
 */
public class SpecialExpression extends Expression {

	private String myCommand;
	private Expression myOperand1;
	private Expression myOperand2;
	private Expression myOperand3;

	/**
	 * Create expression representing the given operation of the given
	 * sub-expression.
	 */

	/**
	 * Create expression representing the given operation among the three given
	 * sub-expressions.
	 */
	public SpecialExpression(String Command, Expression Operand1,
			Expression Operand2, Expression Operand3) {
		myCommand = Command;
		myOperand1 = Operand1;
		myOperand2 = Operand2;
		myOperand3 = Operand3;
	}

	public SpecialExpression() {
		// TODO Auto-generated constructor stub
	}

	public RGBColor evaluate(double DomainX, double DomainY,double CurrentTime) {
		/*
		RGBColor left=myOperand1.evaluate(DomainX, DomainY);
		RGBColor middle=myOperand2.evaluate(DomainX, DomainY);
		RGBColor right=myOperand3.evaluate(DomainX, DomainY);
		return ColorCombinations.doSpecial(myCommand, left, middle, right);
		*/
		if(myCommand.equals("random")){
			return ColorCombinations.random();
		}
		else if (myCommand.equals("color"))
			return ColorCombinations.color(
					myOperand1.evaluate(DomainX, DomainY,CurrentTime),
					myOperand2.evaluate(DomainX, DomainY,CurrentTime),
					myOperand3.evaluate(DomainX, DomainY,CurrentTime));
		else if (myCommand.equals("if"))
			return ColorCombinations.ifCondition(
					myOperand1.evaluate(DomainX, DomainY,CurrentTime),
					myOperand2.evaluate(DomainX, DomainY,CurrentTime),
					myOperand3.evaluate(DomainX, DomainY,CurrentTime));
		else
			throw new ParserException("Unknown Command " + myCommand,
					Type.UNKNOWN_COMMAND);

	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(myOperand1.toString());
		result.append(myOperand2.toString());
		result.append(myOperand3.toString());
		result.append(")");

		return result.toString();
	}

}
