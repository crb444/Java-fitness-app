package Utility;

public class StringUtilityFunctions {
	public static String lowerCaseAllButFirstCharacter(String inputString) {
		return inputString.charAt(0) + inputString.substring(1).toLowerCase();
	}
}
