package Expression;

/**
 * An Expression Factory where determine which kind of Expression should be
 * created and create the Expression
 * 
 * @author Donghe Zhao
 */
public abstract class ExpressionFactory {

	/**
	 * create the Expression
	 */
	public abstract Expression CreateExpression(String Input,
			int CurrentPosition);

	/**
	 * determine which kind of Expression
	 */
	public abstract boolean isThisKindOfExpression(String Input,
			int CurrentPosition);

	/**
	 * return current position
	 */
	public abstract int getMyCurrentPosition();

	/**
	 * skip all the continuous white space
	 */
	public void skipWhiteSpace(String Input, int CurrentPosition) {
		while (notAtEndOfString(Input, CurrentPosition)
				&& Character.isWhitespace(currentCharacter(Input,
						CurrentPosition))) {
			CurrentPosition++;
		}
	}

	public char currentCharacter(String Input, int CurrentPosition) {
		return Input.charAt(CurrentPosition);
	}

	public boolean notAtEndOfString(String Input, int CurrentPosition) {
		return CurrentPosition < Input.length();
	}

}
