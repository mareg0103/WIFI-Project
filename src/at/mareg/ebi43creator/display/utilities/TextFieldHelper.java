package at.mareg.ebi43creator.display.utilities;

public final class TextFieldHelper
{
	private TextFieldHelper ()
	{
	}

	/*
	 * Return a string with two decimal places from given double
	 */
	public static String getTwoDecimalsStringFromDouble (final Double value)
	{
		return String.format ("%.2f", value);
	}

	/*
	 * Return a string with four decimal places from given double
	 */
	public static String getFourDecimalsStringFromDouble (final Double value)
	{
		return String.format ("%.4f", value);
	}

	/*
	 * Return a string with two decimal places from given string
	 */
	public static String getTwoDecimalsStringFromString (final String value)
	{
		return String.format ("%.2f", getDoubleFromString (value));
	}

	/*
	 * Return a string with four decimal places from given string
	 */
	public static String getFourDecimalsStringFromString (final String value)
	{
		return String.format ("%.4f", getDoubleFromString (value));
	}

	/*
	 * Return a double from given string
	 */
	public static Double getDoubleFromString (final String value)
	{
		return Double.valueOf (replaceSemicolonsWithDots (value));
	}

	/*
	 * Return a integer from given string
	 */
	public static Integer getIntegerFromString (final String value)
	{
		return Integer.valueOf (value);
	}

	/*
	 * Replace semicolons with dots
	 */
	private static String replaceSemicolonsWithDots (final String value)
	{
		return value.replace (",", ".");
	}
}
