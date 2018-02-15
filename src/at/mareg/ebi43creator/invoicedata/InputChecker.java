package at.mareg.ebi43creator.invoicedata;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;

/**
 * This class checks forwarded data; the following data must be checked
 * <ul>
 * <li>Order reference</li>
 * <li>Supplier ID</li>
 * </ul>
 * 
 * @author Martin Regitnig
 */

public final class InputChecker
{

	private static ResourceManager rm;

	private InputChecker ()
	{
	}

	/*
	 * Check order reference
	 */
	public static String checkOrderReference (final String orderReference)
	{
		String ret = Data.CHECKMESSAGE_SUCCESS;

		if (orderReference != null)
		{
			// OEBB
			if (orderReference.toLowerCase ().startsWith ("oebb/"))
			{
				final String otherReference = orderReference.substring (5);

				final int otherReferenceLenght = otherReference.length ();
				if (otherReferenceLenght != 3 && otherReferenceLenght != 10)
					ret = "Die Referenz nach \'OEBB/\' hat nicht die richtigen Länge!\n"
							+ "Erforderliche Länge: 3 oder 10 Stellen; Aktuell: " + otherReferenceLenght + " Stellen";

				if (!_isNumeric (otherReference))
					ret = "Die Referenz nach \'OEBB/\' ist nicht rein numerisch";
			}
			// BRZ
			else if (orderReference.toLowerCase ().startsWith ("brz/"))
			{
				final String otherReference = orderReference.substring (4);

				final int otherReferenceLenght = otherReference.length ();
				if (otherReferenceLenght != 3 || otherReferenceLenght != 7)
					ret = "Die Referenz nach \'BRZ/\' hat nicht die richtigen Länge!\n"
							+ "Erforderliche Länge: 3 oder 7 Stellen; Aktuell: " + otherReferenceLenght + " Stellen";

				if (!_isNumeric (otherReference))
					ret = "Die Referenz nach \'BRZ/\' ist nicht rein numerisch";
			}
			// All others
			else
			{
				if (orderReference.isEmpty () || (orderReference.length () < 3 || orderReference.length () > 50))
					return "Die Auftragsreferenz ist ungültig (kürzer als 3 Zeichen oder länger als 50 Zeichen)";

				if (orderReference.length () == 10)
				{
					if (!orderReference.startsWith ("47") && !orderReference.startsWith ("45"))
						ret = "Dies ist keine gültige Bundbestellnummer. Bestellnummern sind\n" + ""
								+ "zehnstellig, numerisch und beginnen mit 47";

					if (!_isNumeric (orderReference))
						ret = "Die Bestellnummer ist nicht rein numerisch";
				}
			}
		}

		return ret;
	}

	/*
	 * Check supplier id
	 */
	public static String checkSupplierID (final String id)
	{
		String ret = Data.CHECKMESSAGE_SUCCESS;
		final String orderReference = rm.getInvoiceData ().getInvoiceRecipient ().getOrderReference ().getOrderId ();

		if (orderReference.toLowerCase ().startsWith ("oebb/"))
		{
			if (id.length () < 3 || id.length () > 6)
				ret = "Die Lieferantennummer hat nicht die richtige Länge (minimal dreistellig, maximal sechsstellig)";

			if (!_isNumeric (id))
				ret = "Die Lieferantennummer ist nicht ausschließlich numerisch";
		} else if (orderReference.toLowerCase ().startsWith ("brz/"))
		{
			if (id.length () != 6 || id.toLowerCase ().startsWith ("0"))
				ret = "Die Lieferantennummer hat das falsche Format\n"
						+ "Anforderung: genau sechsstellig, numerisch, keine führenden Nullen";

			if (!_isNumeric (id))
				ret = "Die Lieferantennummer ist nicht ausschließlich numerisch";
		} else if (orderReference.toLowerCase ().startsWith ("l2/"))
		{
			if (id.length () > 6)
				ret = "Die Lieferantennummer ist zu lang (maximal 6 Ziffern erlaubt)";

			if (id.length () == 5 && !id.toLowerCase ().startsWith ("2"))
				ret = "Die Lieferantennummer ist falsch. Wenn eine fünfstellige Lieferantennummer\n"
						+ "bei Rechnungen an das Land Kärnten verwendet wird, muss die zweite  Ziffer\n"
						+ "eine 2 sein";

			if (!_isNumeric (id))
				ret = "Die Lieferantennummer ist nicht ausschließlich numerisch";
		} else if (orderReference.toLowerCase ().startsWith ("oenb/"))
		{
			if (id.length () != 7)
				ret = "Die Lieferantennummer hat die falsche Länge.\n"
						+ "Anforderung bei OeNB-Rechnungen: genau sieben Ziffern";

			if (!_isNumeric (id))
				ret = "Die Lieferantennummer ist nicht ausschließlich numerisch";
		} else
		{
			if (id.length () != 8 && id.length () != 10)
				return "Die Lieferantennummer hat nicht die richtigen Länge!\n"
						+ "Erlaubt: 8 oder 10 Stellen; Aktuell: " + id.length () + " Stellen";

			if (id.length () == 10 && !_isNumeric (id))
				if (!id.toUpperCase ().startsWith ("ES"))
					ret = "Die Lieferantennummer hat das falsche Format\n"
							+ "Erlaubt: Acht oder Zehnstellig, rein numerisch;\n"
							+ "Zehnstellig (beginnend mit ES gefolgt von acht Ziffern)\n"
							+ "Andere Buchstaben als ES sind nicht gestattet";
		}

		return ret;
	}

	/*
	 * Check if order id is government order number
	 */
	public static boolean orderIDisGovermentOrderNumber (final String orderId)
	{
		if (orderId == null)
			return false;

		if (!_isNumeric (orderId))
			return false;

		if (orderId.length () != 10 || !orderId.startsWith ("47"))
			return false;

		return true;
	}

	/*
	 * Set resource manager
	 */
	public static void setResourceManager (final ResourceManager resman)
	{
		rm = resman;
	}

	/*
	 * Check if string is numeric
	 */
	private static boolean _isNumeric (final String number)
	{
		for (final char c : number.toCharArray ())
			if (Character.digit (c, 10) == -1)
				return false;

		return true;
	}
}
