package Expression;

import model.RGBColor;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 * 
 * @author Donghe Zhao
 */
public abstract class Expression {

	/**
	 * @return value of expression
	 */
	public abstract RGBColor evaluate(double DomainX, double DomainY,
			double CurrentTime);

	/**
	 * @return string representation of expression
	 */
	public abstract String toString();

}
