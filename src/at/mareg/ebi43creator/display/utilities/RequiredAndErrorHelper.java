package at.mareg.ebi43creator.display.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class RequiredAndErrorHelper
{

	private static final Map<String, List<String>> requiredMap = new TreeMap<> ();
	private static final Map<String, List<String>> errorMap = new TreeMap<> ();
	private static final Map<String, List<String>> lineRequiredMap = new TreeMap<> ();

	private RequiredAndErrorHelper ()
	{
	}

	/*
	 * Methods for requiredMap
	 */

	/*
	 * Add a required field to map on start of application or if an required field
	 * becomes empty
	 */
	public static void addRequiredField (final String tabID, final String fieldID)
	{
		if (!requiredMap.containsKey (tabID))
		{
			requiredMap.put (tabID, new ArrayList<> ());
		}

		if (!requiredMap.get (tabID).contains (fieldID))
			requiredMap.get (tabID).add (fieldID);
	}

	/*
	 * Remove a required field from map if an required field is filled
	 */
	public static void removeRequiredField (final String tabID, final String fieldID)
	{
		List<String> al = requiredMap.get (tabID);

		if (al != null)
		{
			if (al.contains (fieldID))
				al.remove (fieldID);

			if (al.size () == 0)
				requiredMap.remove (tabID);
		}
	}

	/*
	 * Shows current requiredMap entries
	 */
	public static void showReqMap ()
	{
		for (final Entry<String, List<String>> e : requiredMap.entrySet ())
		{
			System.out.println (e.getKey ());

			for (final String s : e.getValue ())
				System.out.println ("   " + s);
		}
	}

	/*
	 * Show requiredMap size
	 */
	public static int getRequiredMapSize ()
	{
		return requiredMap.size ();
	}

	/*
	 * Methods for errorMap
	 */

	/*
	 * Add an error field to map if the input is erroneous
	 */
	public static void addErrorField (final String tabID, final String fieldID)
	{
		if (!errorMap.containsKey (tabID))
		{
			errorMap.put (tabID, new ArrayList<> ());
		}

		if (!errorMap.get (tabID).contains (fieldID))
			errorMap.get (tabID).add (fieldID);
	}

	/*
	 * Remove an error field from map if the input is correct or the field becomes
	 * empty
	 */
	public static void removeErrorField (final String tabID, final String fieldID)
	{
		List<String> al = errorMap.get (tabID);

		if (al != null)
		{
			if (al.contains (fieldID))
				al.remove (fieldID);

			if (al.size () == 0)
				errorMap.remove (tabID);
		}
	}

	/*
	 * Shows current errorMap entries
	 */
	public static void showErrorMap ()
	{
		for (final Entry<String, List<String>> e : errorMap.entrySet ())
		{
			System.out.println (e.getKey ());

			for (final String s : e.getValue ())
				System.out.println ("   " + s);
		}
	}

	/*
	 * Show errorMap size
	 */
	public static int getErrorMapSize ()
	{
		return errorMap.size ();
	}
}
