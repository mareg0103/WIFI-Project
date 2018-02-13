package at.mareg.ebi43creator.display.utilities;

public final class DoubleAsStringUtility
{
	private DoubleAsStringUtility ()
	{
	}

	/*
	 * Return a string with two decimal places
	 */
	public static String getTwoDigitValue (final double value)
	{
		return String.format ("%.2f", value);
	}

	/*
	 * Return a string with four decimal places
	 */
	public static String getFourDigitValue (final double value)
	{
		return String.format ("%.4f", value);
	}
}
