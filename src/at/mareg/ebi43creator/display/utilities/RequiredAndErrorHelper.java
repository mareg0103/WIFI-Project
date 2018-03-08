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
  private static final Map <String, List <String>> requiredMap = new TreeMap <> ();
  private static final Map <String, List <String>> errorMap = new TreeMap <> ();
  private static final Map <Integer, List <String>> lineRequiredMap = new TreeMap <> ();
  private static final Map <String, List <String>> surchargeLineRequiredMap = new TreeMap <> ();
  private static final Map <String, List <String>> discountLineRequiredMap = new TreeMap <> ();

  /*
   * Translator map to show the missing required and erroneous id's as clear
   * text
   */
  private static Map <String, String> translatorMap;

  private RequiredAndErrorHelper ()
  {}

  /*
   * Initialize translator map (used for required and erroneous fields)
   */
  private static void _initTranslatorMap ()
  {
    translatorMap = new TreeMap <> ();

    /*
     * Order data
     */
    translatorMap.put (EFormElement.ORDER_ID.getID (), "Tab " + Data.TAB_ORDER_DATA + ", Feld Autragsreferenz");
    translatorMap.put (EFormElement.SUPPLIER_ID.getID (), "Tab " + Data.TAB_ORDER_DATA + ", Feld Lieferantennummer");
    translatorMap.put (EFormElement.INVOICE_NUMBER.getID (), "Tab " + Data.TAB_ORDER_DATA + ", Feld Rechnungsnummer");
    translatorMap.put (EFormElement.FROM_DATE.getID (),
                       "Tab " + Data.TAB_ORDER_DATA + ", Feld Lieferdatum / Leistungszeitraum von:");

    /*
     * Contact data - biller
     */
    translatorMap.put (EFormElement.BILLER_NAME.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ", Feld Name");
    translatorMap.put (EFormElement.BILLER_STREET.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ", Feld Straße");
    translatorMap.put (EFormElement.BILLER_ZIP.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ", Feld PLZ");
    translatorMap.put (EFormElement.BILLER_TOWN.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ", Feld Stadt");
    translatorMap.put (EFormElement.BILLER_COUNTRY.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ", Feld Land");
    translatorMap.put (EFormElement.BILLER_EMAIL.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_BILLER_NAME + ", Feld E-Mail");

    /*
     * Contact data - invoice recipient
     */
    translatorMap.put (EFormElement.INVOICERECIPIENT_NAME.getID (),
                       "Tab " +
                                                                    Data.TAB_CONTACT_DATA +
                                                                    ", Abschnitt " +
                                                                    Data.TITLEDPANE_INVOICERECIPIENT_NAME +
                                                                    ", Feld Name");
    translatorMap.put (EFormElement.INVOICERECIPIENT_STREET.getID (),
                       "Tab " +
                                                                      Data.TAB_CONTACT_DATA +
                                                                      ", Abschnitt " +
                                                                      Data.TITLEDPANE_INVOICERECIPIENT_NAME +
                                                                      ", Feld Straße");
    translatorMap.put (EFormElement.INVOICERECIPIENT_ZIP.getID (),
                       "Tab " +
                                                                   Data.TAB_CONTACT_DATA +
                                                                   ", Abschnitt " +
                                                                   Data.TITLEDPANE_INVOICERECIPIENT_NAME +
                                                                   ", Feld PLZ");
    translatorMap.put (EFormElement.INVOICERECIPIENT_TOWN.getID (),
                       "Tab " +
                                                                    Data.TAB_CONTACT_DATA +
                                                                    ", Abschnitt " +
                                                                    Data.TITLEDPANE_INVOICERECIPIENT_NAME +
                                                                    ", Feld Stadt");
    translatorMap.put (EFormElement.INVOICERECIPIENT_COUNTRY.getID (),
                       "Tab " +
                                                                       Data.TAB_CONTACT_DATA +
                                                                       ", Abschnitt " +
                                                                       Data.TITLEDPANE_INVOICERECIPIENT_NAME +
                                                                       ", Feld Land");

    /*
     * Contact data - delivery address
     */
    translatorMap.put (EFormElement.DELIVERY_NAME.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_DELIVERY_NAME + ", Feld Name");
    translatorMap.put (EFormElement.DELIVERY_STREET.getID (),
                       "Tab " +
                                                              Data.TAB_CONTACT_DATA +
                                                              ", Abschnitt " +
                                                              Data.TITLEDPANE_DELIVERY_NAME +
                                                              ", Feld Straße");
    translatorMap.put (EFormElement.DELIVERY_ZIP.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_DELIVERY_NAME + ", Feld PLZ");
    translatorMap.put (EFormElement.DELIVERY_TOWN.getID (),
                       "Tab " +
                                                            Data.TAB_CONTACT_DATA +
                                                            ", Abschnitt " +
                                                            Data.TITLEDPANE_DELIVERY_NAME +
                                                            ", Feld Stadt");
    translatorMap.put (EFormElement.DELIVERY_COUNTRY.getID (),
                       "Tab " + Data.TAB_CONTACT_DATA + ", Abschnitt " + Data.TITLEDPANE_DELIVERY_NAME + ", Feld Land");

    /*
     * Payment data
     */
    translatorMap.put (EFormElement.PAYMENT_IBAN.getID (), "Tab " + Data.TAB_PAYMENT_DATA + ", Feld IBAN");

    /*
     * Details data - invoice line
     */
    translatorMap.put (EFormElement.DETAILS_LINE_QUANTITY.getID (), "Menge");
    translatorMap.put (EFormElement.DETAILS_LINE_UNITPRICE.getID (), "Einzelpreis netto");
    translatorMap.put (EFormElement.DETAILS_LINE_DESCRIPTION.getID (), "Beschreibung");

    /*
     * Details data - right area
     */
    translatorMap.put (EFormElement.DETAILS_RIGHT_VATID_BILLER.getID (),
                       "Tab " + Data.TAB_DETAILS_DATA + "-rechter Bereich, Feld UID Rechnungssteller");
    translatorMap.put (EFormElement.DETAILS_RIGHT_VATID_INVOICERECIPIENT.getID (),
                       "Tab " + Data.TAB_DETAILS_DATA + "-rechter Bereich, Feld UID Rechnungsempfänger");

    /*
     * Summary - right area
     */
    translatorMap.put (EFormElement.SUMMARY_SAVENAME_TEXTFIELD.getID (),
                       "Tab " + Data.TAB_SUMMARY_DATA + "-rechter Bereich, Feld Dateiname angeben");
  }

  /*
   * Get id translation from translator map
   */
  public static String getTranslationForID (final String id)
  {
    if (translatorMap == null)
      _initTranslatorMap ();

    if (translatorMap.get (id) != null)
      return translatorMap.get (id);
    else
      return id;
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
      requiredMap.put (tabID, new ArrayList <> ());
    }

    if (!requiredMap.get (tabID).contains (fieldID))
      requiredMap.get (tabID).add (fieldID);
  }

  /*
   * Remove a required field from map if an required field is filled
   */
  public static void removeRequiredField (final String tabID, final String fieldID)
  {
    final List <String> al = requiredMap.get (tabID);

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
    for (final Entry <String, List <String>> e : requiredMap.entrySet ())
    {
      System.out.println (e.getKey ());

      for (final String s : e.getValue ())
        System.out.println ("   " + s);
    }
  }

  /*
   * Get requiredMap
   */
  public static Map <String, List <String>> getRequiredMap ()
  {
    return requiredMap;
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
      errorMap.put (tabID, new ArrayList <> ());
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
    final List <String> al = errorMap.get (tabID);

    if (al != null)
    {
      if (al.contains (fieldID))
        al.remove (fieldID);

      if (al.size () == 0)
        errorMap.remove (tabID);
    }
  }

  /*
   * Get errorMap
   */
  public static Map <String, List <String>> getErrorMap ()
  {
    return errorMap;
  }

  /*
   * Shows current errorMap entries
   */
  public static void showErrorMap ()
  {
    for (final Entry <String, List <String>> e : errorMap.entrySet ())
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
      lineRequiredMap.put (lineID, new ArrayList <> ());
    }

    if (!lineRequiredMap.get (lineID).contains (fieldID))
      lineRequiredMap.get (lineID).add (fieldID);
  }

  /*
   * Remove a required field from map if an required invoice line field is
   * filled
   */
  public static void removeRequiredFieldForLine (final Integer lineID, final String fieldID)
  {
    final List <String> al = lineRequiredMap.get (lineID);

    if (al != null)
    {
      if (al.contains (fieldID))
        al.remove (fieldID);

      if (al.size () == 0)
        lineRequiredMap.remove (lineID);
    }
  }

  /*
   * Removes the whole line from lineRequiredMap
   */
  public static void removeLineFromLineRequiredMap (final Integer lineID)
  {
    if (lineRequiredMap.containsKey (lineID))
      lineRequiredMap.remove (lineID);
  }

  /*
   * Get lineRequiredMap
   */
  public static Map <Integer, List <String>> getLineRequiredMap ()
  {
    return lineRequiredMap;
  }

  /*
   * Shows current requiredMap entries
   */
  public static void showLineReqMap ()
  {
    for (final Entry <Integer, List <String>> e : lineRequiredMap.entrySet ())
    {
      System.out.println (e.getKey ().intValue ());

      for (final String s : e.getValue ())
        System.out.println ("   " + s);
    }
  }

  /*
   * Show lineRequiredMap size
   */
  public static int getLineRequiredMapSize ()
  {
    return lineRequiredMap.size ();
  }

  /*
   * Methods for surchargeLineRequiredMap
   */

  /*
   * Add a required field to map if an surcharge is added to the document
   */
  public static void addRequiredFieldForSurchargeLine (final String lineID, final String fieldID)
  {
    if (!surchargeLineRequiredMap.containsKey (lineID))
    {
      surchargeLineRequiredMap.put (lineID, new ArrayList <> ());
    }

    if (!surchargeLineRequiredMap.get (lineID).contains (fieldID))
      surchargeLineRequiredMap.get (lineID).add (fieldID);
  }

  /*
   * Remove a required field from map if an required surcharge line field is
   * filled
   */
  public static void removeRequiredFieldForSurchargeLine (final String lineID, final String fieldID)
  {
    final List <String> al = surchargeLineRequiredMap.get (lineID);

    if (al != null)
    {
      if (al.contains (fieldID))
        al.remove (fieldID);

      if (al.size () == 0)
        surchargeLineRequiredMap.remove (lineID);
    }
  }

  /*
   * Removes the whole line from surchargeLineRequiredMap
   */
  public static void removeLineFromSurchargeLineRequiredMap (final String lineID)
  {
    if (surchargeLineRequiredMap.containsKey (lineID))
      surchargeLineRequiredMap.remove (lineID);
  }

  /*
   * Get suchargeLineRequiredField map
   */
  public static Map <String, List <String>> getSurchargeLineRequiredMap ()
  {
    return surchargeLineRequiredMap;
  }

  /*
   * Shows current surchargeLineRequiredMap entries
   */
  public static void showSurchargeLineReqMap ()
  {
    for (final Entry <String, List <String>> e : surchargeLineRequiredMap.entrySet ())
    {
      System.out.println (e.getKey ());

      for (final String s : e.getValue ())
        System.out.println ("   " + s);
    }
  }

  /*
   * Show surchargeLineRequiredMap size
   */
  public static int getSurchargeLineRequiredMapSize ()
  {
    return surchargeLineRequiredMap.size ();
  }

  /*
   * Methods for discountLineRequiredMap
   */

  /*
   * Add a required field to map if an discount is added to the document
   */
  public static void addRequiredFieldForDiscountLine (final String lineID, final String fieldID)
  {
    if (!discountLineRequiredMap.containsKey (lineID))
    {
      discountLineRequiredMap.put (lineID, new ArrayList <> ());
    }

    if (!discountLineRequiredMap.get (lineID).contains (fieldID))
      discountLineRequiredMap.get (lineID).add (fieldID);
  }

  /*
   * Remove a required field from map if an required discount line field is
   * filled
   */
  public static void removeRequiredFieldForDiscountLine (final String lineID, final String fieldID)
  {
    final List <String> al = discountLineRequiredMap.get (lineID);

    if (al != null)
    {
      if (al.contains (fieldID))
        al.remove (fieldID);

      if (al.size () == 0)
        discountLineRequiredMap.remove (lineID);
    }
  }

  /*
   * Removes the whole line from discountLineRequiredMap
   */
  public static void removeLineFromDiscountLineRequiredMap (final String lineID)
  {
    if (discountLineRequiredMap.containsKey (lineID))
      discountLineRequiredMap.remove (lineID);
  }

  /*
   * Get discountLineRequiredMap
   */
  public static Map <String, List <String>> getDiscountLineRequiredMap ()
  {
    return discountLineRequiredMap;
  }

  /*
   * Shows current discountLineRequiredMap entries
   */
  public static void showDiscountLineReqMap ()
  {
    for (final Entry <String, List <String>> e : discountLineRequiredMap.entrySet ())
    {
      System.out.println (e.getKey ());

      for (final String s : e.getValue ())
        System.out.println ("   " + s);
    }
  }

  /*
   * Show discountLineRequiredMap size
   */
  public static int getDiscountLineRequiredMapSize ()
  {
    return discountLineRequiredMap.size ();
  }

  /*
   * Return if all required and error maps are empty
   */
  public static boolean allFieldsAreFilledAndCorrect ()
  {
    return requiredMap.size () == 0 &&
           errorMap.size () == 0 &&
           lineRequiredMap.size () == 0 &&
           discountLineRequiredMap.size () == 0 &&
           surchargeLineRequiredMap.size () == 0;
  }

  /*
   * Return if all required maps are empty
   */
  public static boolean allRequiredFieldsAreFilled ()
  {
    return requiredMap.size () == 0 &&
           lineRequiredMap.size () == 0 &&
           discountLineRequiredMap.size () == 0 &&
           surchargeLineRequiredMap.size () == 0;
  }
}
