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

public enum EFormFields
{

  // Procedure to add a new element to form
  //
  // Add new element to this enum
  // Create a corresponding Field in InvoiceData (or included class)
  // Create a new entry in MethodMapper to save this element
  // Create a new entry in HelpArea

  /*
   * Order data fields
   */
  ORDER_ID ("orderid", "Auftragsreferenz", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  ORDER_REFERENCEDATE ("orderreferencedate", "Auftragsdatum", Data.ELEMENTTYPE_DATEPICKER, false, Data.TITLEDPANE_ORDER),
  ORDER_DESCRIPTION ("orderdescription", "Auftragsbeschreibung", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_ORDER),
  SUPPLIER_ID ("supplierid", "Lieferantennummer", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  INVOICE_NUMBER ("invoicenumber", "Rechnungsnummer", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  INVOICE_DATE ("invoicedate", "Rechnungsdatum", Data.ELEMENTTYPE_DATEPICKER, true, Data.TITLEDPANE_ORDER),
  INVOICE_CURRENCY ("invoicecurrency", "Währung", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  FROM_DATE ("fromdate", "Lieferdatum / Leistungszeitraum Von", Data.ELEMENTTYPE_DATEPICKER, true, Data.TITLEDPANE_ORDER),
  TO_DATE ("todate", "Leistungszeitraum Bis", Data.ELEMENTTYPE_DATEPICKER, false, Data.TITLEDPANE_ORDER),
  COMMENT ("comment", "Mitteilung", Data.ELEMENTTYPE_TEXTAREA, false, Data.TITLEDPANE_ORDER),

  /*
   * Biller fields
   */
  BILLER_NAME ("billername", "Name", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_STREET ("billerstreet", "Strasse", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_ZIP ("billerzip", "PLZ", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_TOWN ("billertown", "Stadt", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_COUNTRY ("billercountry", "Land", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_EMAIL ("billeremail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_PHONE ("billerphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER),
  BILLER_CONTACT ("billercontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER),

  /*
   * Delivery fields
   */
  DELIVERY_ID ("deliveryid", "Lieferscheinnummer", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY),
  DELIVERY_USE ("deliveryuse", "Abweichende Lieferanschrift verwenden", Data.ELEMENTTYPE_CHECKBOX, false, Data.TITLEDPANE_DELIVERY),
  DELIVERY_NAME ("deliveryname", "Name", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY),
  DELIVERY_STREET ("deliverystreet", "Strasse", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY),
  DELIVERY_ZIP ("deliveryzip", "PLZ", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY),
  DELIVERY_TOWN ("deliverytown", "Stadt", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY),
  DELIVERY_COUNTRY ("deliverycountry", "Land", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY),
  DELIVERY_EMAIL ("deliveryemail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_DELIVERY),
  DELIVERY_PHONE ("deliveryphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY),
  DELIVERY_CONTACT ("dleiverycontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_DELIVERY),

  /*
   * Invoice recipient fields
   */
  INVOICERECIPIENT_NAME ("invoicerecipientname", "Name", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_STREET ("invoicerecipienstreet", "Strasse", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_ZIP ("invoicerecipienzip", "PLZ", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_TOWN ("invoicerecipientown", "Stadt", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_COUNTRY ("invoicerecipiencountry", "Land", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_EMAIL ("invoicerecipienemail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_PHONE ("invoicerecipienphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_CONTACT ("invoicerecipiencontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT),

  /*
   * Payment fields
   */
  PAYMENT_BIC ("paymentbic", "BIC", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_PAYMENT),
  PAYMENT_IBAN ("paymentiban", "IBAN", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_PAYMENT),
  PAYMENT_ACCOUNTOWNER ("paymentaccountowner", "Kontoinhaber", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_PAYMENT);

  /*
   * Fields and Methods
   */
  private final String sID;
  private final String sLabelText;
  private final String sType;
  private final boolean bRequired;
  private final String sTitledPaneID;

  private EFormFields (final String id, final String desc, final String t, final boolean req, final String tID)
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

  public String getTiteldPaneID ()
  {
    return sTitledPaneID;
  }

  public static EFormFields getFromIDOrNull (final String sID)
  {
    if (sID != null)
      for (final EFormFields e : values ())
        if (e.sID.equals (sID))
          return e;

    return null;
  }

}
