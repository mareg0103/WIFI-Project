package at.mareg.ebi43creator.invoicedata.enums;

import at.mareg.ebi43creator.display.resources.Data;

/**
 * Field description for all necessary form fields Contains:
 * <ul>
 * <li>Field id</li>
 * <li>Label text for field</li>
 * <li>Field type</li>
 * <li>Field is required</li>
 * <li>TitledPane to add to</li>
 * </ul>
 * 
 * @author Martin Regitnig
 */

public enum EFormElement
{

  // Procedure to add a new element to form
  //
  // Add new element to this enum
  // Create a corresponding field in InvoiceData (or included class)
  // Create a new entry in MethodMapper to save this element
  // Create a new entry in HelpArea

  /*
   * Order data elements
   */
  ORDER_ID ("orderid", "Auftragsreferenz", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER_NAME),
  ORDER_REFERENCEDATE ("orderreferencedate", "Auftragsdatum", Data.ELEMENTTYPE_DATEPICKER, false, Data.TITLEDPANE_ORDER_NAME),
  ORDER_DESCRIPTION ("orderdescription", "Auftragsbeschreibung", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_ORDER_NAME),
  SUPPLIER_ID ("supplierid", "Lieferantennummer", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER_NAME),
  INVOICE_NUMBER ("invoicenumber", "Rechnungsnummer", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER_NAME),
  INVOICE_DATE ("invoicedate", "Rechnungsdatum", Data.ELEMENTTYPE_DATEPICKER, true, Data.TITLEDPANE_ORDER_NAME),
  INVOICE_CURRENCY ("invoicecurrency", "Währung", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_ORDER_NAME),
  FROM_DATE ("fromdate", "Lieferdatum / Leistungszeitraum Von", Data.ELEMENTTYPE_DATEPICKER, true, Data.TITLEDPANE_ORDER_NAME),
  TO_DATE ("todate", "Leistungszeitraum Bis", Data.ELEMENTTYPE_DATEPICKER, false, Data.TITLEDPANE_ORDER_NAME),
  COMMENT ("comment", "Mitteilung", Data.ELEMENTTYPE_TEXTAREA, false, Data.TITLEDPANE_ORDER_NAME),

  /*
   * Biller elements
   */
  BILLER_NAME ("billername", "Name", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER_NAME),
  BILLER_STREET ("billerstreet", "Strasse", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER_NAME),
  BILLER_ZIP ("billerzip", "PLZ", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER_NAME),
  BILLER_TOWN ("billertown", "Stadt", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER_NAME),
  BILLER_COUNTRY ("billercountry", "Land", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER_NAME),
  BILLER_EMAIL ("billeremail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER_NAME),
  BILLER_PHONE ("billerphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER_NAME),
  BILLER_CONTACT ("billercontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER_NAME),

  /*
   * Invoice recipient elements
   */
  INVOICERECIPIENT_NAME ("invoicerecipientname", "Name", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_STREET ("invoicerecipienstreet", "Strasse", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_ZIP ("invoicerecipienzip", "PLZ", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_TOWN ("invoicerecipientown", "Stadt", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_COUNTRY ("invoicerecipiencountry", "Land", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_EMAIL ("invoicerecipienemail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_PHONE ("invoicerecipienphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT_NAME),
  INVOICERECIPIENT_CONTACT ("invoicerecipiencontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT_NAME),

  /*
   * Delivery elements
   */
  DELIVERY_ID ("deliveryid", "Lieferscheinnummer", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_USE ("deliveryuse", "Abweichende Lieferanschrift verwenden", Data.ELEMENTTYPE_CHECKBOX, false, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_NAME ("deliveryname", "Name", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_STREET ("deliverystreet", "Strasse", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_ZIP ("deliveryzip", "PLZ", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_TOWN ("deliverytown", "Stadt", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_COUNTRY ("deliverycountry", "Land", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_EMAIL ("deliveryemail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_PHONE ("deliveryphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY_NAME),
  DELIVERY_CONTACT ("dleiverycontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY_NAME),

  /*
   * Payment elements
   */
  PAYMENT_BIC ("paymentbic", "BIC", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_PAYMENT_NAME),
  PAYMENT_IBAN ("paymentiban", "IBAN", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_PAYMENT_NAME),
  PAYMENT_ACCOUNTOWNER ("paymentaccountowner", "Kontoinhaber", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_PAYMENT_NAME),

  /*
   * Details Pane - right area elements
   */
  DETAILS_RIGHT_ADDINVOICELINE_BUTTON ("addinvoiceline", "Rechnungszeile\nhinzufügen", Data.ELEMENTTYPE_BUTTON, false, Data.DETAILS_RIGHT_AREA),
  DETAILS_RIGHT_TOTALNETAMOUND ("detailsrighttotalnetamount", "Gesamtnetto\ninkl. Auf-/Abschläge", Data.ELEMENTTYPE_TEXTFIELD, false, Data.DETAILS_RIGHT_AREA),
  DETAILS_RIGHT_TOTALGROSSAMOUND ("detailsrighttotalgrossamount", "Gesamtbrutto", Data.ELEMENTTYPE_TEXTFIELD, false, Data.DETAILS_RIGHT_AREA),
  DETAILS_RIGHT_VATID_BILLER ("billervatid", "UID Rechnungssteller", Data.ELEMENTTYPE_TEXTFIELD, false, Data.DETAILS_RIGHT_AREA),
  DETAILS_RIGHT_VATID_INVOICERECIPIENT ("invoicerecipientvatid", "UID Rechnungsempfänger", Data.ELEMENTTYPE_TEXTFIELD, false, Data.DETAILS_RIGHT_AREA),

  /*
   * Details Pane - invoice lines
   */
  DETAILS_LINE_ORDERPOSITIONNUMER ("orderpositionnumber", "Pos.Nr. aus Bestellung", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_QUANTITY ("quantity", "Menge", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_UNIT ("unit", "Mengeneinheit", Data.ELEMENTTYPE_COMBOBOX, true, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_UNITPRICE ("unitprice", "Einzelpreis netto", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_DESCRIPTION ("description", "Beschreibung", Data.ELEMENTTYPE_TEXTAREA, true, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_TOTALNET ("totalnetamount", "Gesamtnetto", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_SURCHARGE ("surcharge", "Auf-/Abschlag", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_SURCHARGE_DESCRIPTION ("surchargedescription", "Beschreibung", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_VAT ("vatrate", "Steuersatz", Data.ELEMENTTYPE_COMBOBOX, true, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_TAXEXEMPTION_CHECK ("taxexemptioncheck", "Steuerbefreiung", Data.ELEMENTTYPE_CHECKBOX, false, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_TAXEXEMPTION_REASON ("taxexemptionreason", "Steuerbefreiungsgrund", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_TOTALGROSS ("totalgrossamount", "Gesamtbrutto", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DETAILS_NAME),
  DETAILS_LINE_REMOVE ("deletethisline", "", Data.ELEMENTTYPE_BUTTON, false, Data.TITLEDPANE_DETAILS_NAME),
  
  /*
   * Reduction/Surcharge/Discount - right area
   */
  SURCHARGE_DISCOUNT_RIGHT_ADDSURCHARGEBUTTON ("addsurcharge", "Auf-/Abschlag\nhinzufügen", Data.ELEMENTTYPE_BUTTON, false, Data.SURCHARGE_RIGHT_AREA),
  SURCHARGE_DISCOUNT_RIGHT_ADDDISCOUNTBUTTON ("adddiscount", "Skonto\nhinzufügen", Data.ELEMENTTYPE_BUTTON, false, Data.SURCHARGE_RIGHT_AREA),
  SURCHARGE_DISCOUNT_RIGHT_TOTALNETAMOUNT ("surchargerighttotalnetamount", "Gesamtnetto inkl.\naller Auf-/Abschläge", Data.ELEMENTTYPE_TEXTFIELD, false, Data.SURCHARGE_RIGHT_AREA),
  SURCHARGE_DISCOUNT_RIGHT_TOTALGROSSAMOUNT ("surchargerighttotalgrossamount", "Gesamtbrutto", Data.ELEMENTTYPE_TEXTFIELD, false, Data.SURCHARGE_RIGHT_AREA),

  /*
   * Reduction/Surcharge elements
   */
  SURCHARGE_VALUE ("surchargevalue", "Wert", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_SURCHARGE_NAME),
  SURCHARGE_VAT ("surchargevat", "Steuersatz", Data.ELEMENTTYPE_COMBOBOX, false, Data.TITLEDPANE_SURCHARGE_NAME),
  SURCHARGE_COMMENT ("surchargecomment", "Kommentar", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_SURCHARGE_NAME),
  SURCHARGE_REMOVE ("deletethissurcharge", "", Data.ELEMENTTYPE_BUTTON, false, Data.TITLEDPANE_SURCHARGE_NAME);

  /*
   * Variables and methods
   */
  private final String sID;
  private final String sLabelText;
  private final String sType;
  private boolean bRequired;
  private final String sTitledPaneID;

  private EFormElement (final String id, final String desc, final String t, final boolean req, final String tID)
  {
    sID = id;
    sLabelText = desc;
    sType = t;
    bRequired = req;
    sTitledPaneID = tID;
  }

  public String getName ()
  {
    return name ();
  }

  public String getID ()
  {
    return sID;
  }

  public String getLabelText ()
  {
    return sLabelText;
  }

  public String getType ()
  {
    return sType;
  }

  public boolean isRequired ()
  {
    return bRequired;
  }
  
  public void setIsRequired (final boolean required)
  {
	  bRequired = required;
  }

  public String getTiteldPaneID ()
  {
    return sTitledPaneID;
  }

  public static EFormElement getFromIDOrNull (final String sID)
  {
    if (sID != null)
      for (final EFormElement e : values ())
        if (e.sID.equals (sID))
          return e;

    return null;
  }
}
