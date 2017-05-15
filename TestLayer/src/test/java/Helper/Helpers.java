package Helper;

import org.apache.commons.lang3.RandomStringUtils;

public class Helpers {

	/* Generates a random Alphanumeric String of given length */
	public static String random_AlphaNumericString_Generator(int i) throws Throwable {
		return RandomStringUtils.randomAlphanumeric(i);
	}

	/* Generates a random Alphabetic String of given length */
	public static String random_AlphabeticString_Generator(int i) throws Throwable {
		return RandomStringUtils.randomAlphabetic(i);
	}



}
