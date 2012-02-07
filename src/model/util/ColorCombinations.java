package model.util;

import java.util.ArrayList;

import model.ParserException;
import model.RGBColor;
import model.ParserException.Type;

/**
 * Combine some colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of ways to
 * combine colors, many may exist and we do not want to keep modifying the
 * RGBColor class.
 * 
 * @author Donghe Zhao
 */
public class ColorCombinations {

	public static RGBColor doTwoParameterOperator(String command,
			RGBColor left, RGBColor right) {
		if (command.equals("plus") || command.equals("+")) {
			return new RGBColor(left.getRed() + right.getRed(), left.getGreen()
					+ right.getGreen(), left.getBlue() + right.getBlue());
		} else if (command.equals("minus") || command.equals("-")) {
			return new RGBColor(left.getRed() - right.getRed(), left.getGreen()
					- right.getGreen(), left.getBlue() - right.getBlue());
		} else if (command.equals("mul") || command.equals("*")) {
			return new RGBColor(left.getRed() * right.getRed(), left.getGreen()
					* right.getGreen(), left.getBlue() * right.getBlue());
		} else if (command.equals("div") || command.equals("/")) {
			return new RGBColor(left.getRed() / right.getRed(), left.getGreen()
					/ right.getGreen(), left.getBlue() / right.getBlue());
		} else if (command.equals("mod") || command.equals("%")) {
			return new RGBColor(left.getRed() % right.getRed(), left.getGreen()
					% right.getGreen(), left.getBlue() % right.getBlue());
		} else if (command.equals("exp") || command.equals("^")) {
			return new RGBColor(Math.pow(left.getRed(), right.getRed()),
					Math.pow(left.getGreen(), right.getGreen()), Math.pow(
							left.getBlue(), right.getBlue()));
		} else if (command.equals("perlinColor")) {
			return PerlinNoise.colorNoise(left, right);
		} else if (command.equals("perlinBW")) {
			return PerlinNoise.greyNoise(left, right);
		}
		throw new ParserException("Unknown Command " + command,
				Type.UNKNOWN_COMMAND);
	}

	public static RGBColor doOneParameterOperator(String command, RGBColor left) {
		if (command.equals("neg") || command.equals("!")) {
			return new RGBColor(-left.getRed(), -left.getGreen(),
					-left.getBlue());
		} else if (command.equals("floor")) {
			return new RGBColor(Math.floor(left.getRed()), Math.floor(left
					.getGreen()), Math.floor(left.getBlue()));
		} else if (command.equals("ceil")) {
			return new RGBColor(Math.ceil(left.getRed()), Math.ceil(left
					.getGreen()), Math.ceil(left.getBlue()));
		} else if (command.equals("abs")) {
			return new RGBColor(Math.abs(left.getRed()), Math.abs(left
					.getGreen()), Math.abs(left.getBlue()));
		} else if (command.equals("clamp")) {
			left.clamp();
			return left;
		} else if (command.equals("wrap")) {
			left.wrap();
			return left;
		} else if (command.equals("sin")) {
			return new RGBColor(Math.sin(left.getRed()), Math.sin(left
					.getGreen()), Math.sin(left.getBlue()));
		} else if (command.equals("cos")) {
			return new RGBColor(Math.cos(left.getRed()), Math.cos(left
					.getGreen()), Math.cos(left.getBlue()));
		} else if (command.equals("tan")) {
			return new RGBColor(Math.tan(left.getRed()), Math.tan(left
					.getGreen()), Math.tan(left.getBlue()));
		} else if (command.equals("atan")) {
			return new RGBColor(Math.atan(left.getRed()), Math.atan(left
					.getGreen()), Math.atan(left.getBlue()));
		} else if (command.equals("log")) {
			return new RGBColor(Math.log(left.getRed()), Math.log(left
					.getGreen()), Math.log(left.getBlue()));
		} else if (command.equals("rgbToYCrCb")) {
			return ColorModel.rgb2ycrcb(left);
		} else if (command.equals("yCrCbtoRGB")) {
			return ColorModel.ycrcb2rgb(left);
		}
		throw new ParserException("Unknown Command " + command,
				Type.UNKNOWN_COMMAND);
	}

	public static RGBColor doMultipleParameterOperator(String command,
			ArrayList<RGBColor> parameterList) {
		double myRed;
		double myGreen;
		double myBlue;
		if (command.equals("sum")) {
			myRed = 0;
			myGreen = 0;
			myBlue = 0;
			for (RGBColor r : parameterList) {
				myRed += r.getRed();
				myGreen += r.getGreen();
				myBlue += r.getBlue();
			}
			return new RGBColor(myRed, myGreen, myBlue);
		} else if (command.equals("product")) {
			myRed = 1;
			myGreen = 1;
			myBlue = 1;
			for (RGBColor r : parameterList) {
				myRed *= r.getRed();
				myGreen *= r.getGreen();
				myBlue *= r.getBlue();
			}
			return new RGBColor(myRed, myGreen, myBlue);
		} else if (command.equals("average")) {
			myRed = 0;
			myGreen = 0;
			myBlue = 0;
			for (RGBColor r : parameterList) {
				myRed += r.getRed();
				myGreen += r.getGreen();
				myBlue += r.getBlue();
			}
			return new RGBColor(myRed / parameterList.size(), myGreen
					/ parameterList.size(), myBlue / parameterList.size());
		} else if (command.equals("min")) {
			myRed = 1;
			myGreen = 1;
			myBlue = 1;
			for (RGBColor r : parameterList) {
				myRed = Math.min(r.getRed(), myRed);
				myGreen = Math.min(r.getGreen(), myGreen);
				myBlue = Math.min(r.getBlue(), myBlue);
			}
			return new RGBColor(myRed, myGreen, myBlue);
		} else if (command.equals("max")) {
			myRed = -1;
			myGreen = -1;
			myBlue = -1;
			for (RGBColor r : parameterList) {
				myRed = Math.max(r.getRed(), myRed);
				myGreen = Math.max(r.getGreen(), myGreen);
				myBlue = Math.max(r.getBlue(), myBlue);
			}
			return new RGBColor(myRed, myGreen, myBlue);
		}
		throw new ParserException("Unknown Command " + command,
				Type.UNKNOWN_COMMAND);
	}

	public static RGBColor random() {
		return new RGBColor(Math.random(), Math.random(), Math.random());
	}

	public static RGBColor color(RGBColor left, RGBColor right, RGBColor last) {
		return new RGBColor(left.getRed(), right.getGreen(), last.getBlue());
	}

	public static RGBColor ifCondition(RGBColor condition, RGBColor greater,
			RGBColor lesser) {
		double myRed = condition.getRed() > 0 ? greater.getRed() : lesser
				.getRed();
		double myGreen = condition.getGreen() > 0 ? greater.getGreen() : lesser
				.getGreen();
		double myBlue = condition.getBlue() > 0 ? greater.getBlue() : lesser
				.getBlue();
		return new RGBColor(myRed, myGreen, myBlue);
	}
}
