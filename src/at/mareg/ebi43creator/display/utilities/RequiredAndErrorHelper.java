package at.mareg.ebi43creator.display.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;

public final class RequiredAndErrorHelper
{
	/*
	 * Maps with entries for required and error fields, if not all required fields
	 * are filled and error free, saving the invoice is not possible
	 */
	private static final Map<String, List<String>> requiredMap = new TreeMap<> ();
	private static final Map<String, List<String>> errorMap = new TreeMap<> ();
	private static final Map<Integer, List<String>> lineRequiredMap = new TreeMap<> ();

	/*
	 * Translator map to show the missing required and erroneous id's as clear text
	 */
	private static Map<String, String> translatorMap;

	private RequiredAndErrorHelper ()
	{
	}

	/*
	 * Initialize translator map (used for required and erroneous fields)
	 */
	private static void _initTranslatorMap ()
	{
		translatorMap = new TreeMap<> ();

		/*
		 * Order data
		 */
		translatorMap.put (EFormElement.ORDER_ID.getID (), "Tab " + Data.TAB_ORDER_DATA + ", Feld Autragsreferenz");
		translatorMap.put (EFormElement.SUPPLIER_ID.getID (),
				"Tab " + Data.TAB_ORDER_DATA + ", Feld Lieferantennummer");
		translatorMap.put (EFormElement.FROM_DATE.getID (),
				"Tab " + Data.TAB_ORDER_DATA + ", Feld Lieferdatum / Leistungszeitraum von:");

		/*
		 * Contact data - biller
		 */
		translatorMap.put (EFormElement.BILLER_NAME.getID (),
				"Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ",\n   Feld Name");
		translatorMap.put (EFormElement.BILLER_STREET.getID (),
				"Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ",\n   Feld Straße");
		translatorMap.put (EFormElement.BILLER_ZIP.getID (),
				"Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ",\n   Feld PLZ");
		translatorMap.put (EFormElement.BILLER_TOWN.getID (),
				"Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ",\n   Feld Stadt");
		translatorMap.put (EFormElement.BILLER_COUNTRY.getID (),
				"Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ",\n   Feld Land");
		translatorMap.put (EFormElement.BILLER_EMAIL.getID (),
				"Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ",\n   Feld E-Mail");

		/*
		 * Contact data - invoice recipient
		 */
		translatorMap.put (EFormElement.INVOICERECIPIENT_NAME.getID (), "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt "
				+ Data.TITLEDPANE_INVOICERECIPIENT_NAME + ",\n   Feld Name");
		translatorMap.put (EFormElement.INVOICERECIPIENT_STREET.getID (), "Tab " + Data.TAB_CONTACT_DATA
				+ ", Abschnitt " + Data.TITLEDPANE_INVOICERECIPIENT_NAME + ",\n   Feld Straße");
		translatorMap.put (EFormElement.INVOICERECIPIENT_ZIP.getID (), "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt "
				+ Data.TITLEDPANE_INVOICERECIPIENT_NAME + ",\n   Feld PLZ");
		translatorMap.put (EFormElement.INVOICERECIPIENT_TOWN.getID (), "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt "
				+ Data.TITLEDPANE_INVOICERECIPIENT_NAME + ",\n   Feld Stadt");
		translatorMap.put (EFormElement.INVOICERECIPIENT_COUNTRY.getID (), "Tab " + Data.TAB_CONTACT_DATA
				+ ", Abschnitt " + Data.TITLEDPANE_INVOICERECIPIENT_NAME + ",\n   Feld Land");

		/*
		 * Payment data
		 */
		translatorMap.put (EFormElement.PAYMENT_IBAN.getID (), "Tab " + Data.TAB_PAYMENT_DATA + ", Feld IBAN");
	}

	/*
	 * Get id translation from translator map
	 */
	public static String getTranslationForID (final String id)
	{
		if (translatorMap == null)
			_initTranslatorMap ();

		return translatorMap.get (id);
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

	/*
	 * Methods for lineRequiredMap
	 */

	/*
	 * Add a required field to map on start of application or if an required field
	 * becomes empty
	 */
	public static void addRequiredFieldForLine (final Integer lineID, final String fieldID)
	{
		if (!lineRequiredMap.containsKey (lineID))
		{
			lineRequiredMap.put (lineID, new ArrayList<> ());
		}

		if (!lineRequiredMap.get (lineID).contains (fieldID))
			lineRequiredMap.get (lineID).add (fieldID);
	}

	/*
	 * Remove a required field from map if an required invoice line field is filled
	 */
	public static void removeRequiredFieldForLine (final Integer lineID, final String fieldID)
	{
		List<String> al = lineRequiredMap.get (lineID);

		if (al != null)
		{
			if (al.contains (fieldID))
				al.remove (fieldID);

			if (al.size () == 0)
				lineRequiredMap.remove (lineID);
		}
	}

	/*
	 * Shows current requiredMap entries
	 */
	public static void showLineReqMap ()
	{
		for (final Entry<Integer, List<String>> e : lineRequiredMap.entrySet ())
		{
			System.out.println (e.getKey ().intValue ());

			for (final String s : e.getValue ())
				System.out.println ("   " + s);
		}
	}

	/*
	 * Show requiredMap size
	 */
	public static int getLineRequiredMapSize ()
	{
		return lineRequiredMap.size ();
	}

	/*
	 * Return if all required and error maps are empty
	 */
	public static boolean allFieldsAreFilledAndCorrect ()
	{
		return requiredMap.size () == 0 && errorMap.size () == 0 && lineRequiredMap.size () == 0;
	}
}
