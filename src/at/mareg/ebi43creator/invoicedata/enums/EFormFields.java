package at.mareg.ebi43creator.invoicedata.enums;

import at.mareg.ebi43creator.display.resources.Data;

public enum EFormFields
{

  /*
   * Order data fields
   */
  ORDER_ID ("orderid", "Auftragsreferenz*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  SUPPLIER_ID ("supplierid", "Lieferantennummer*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  INVOICE_NUMBER ("invoicenumber", "Rechnungsnummer*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  INVOICE_DATE ("invoicedate", "Rechnungsdatum*", Data.ELEMENTTYPE_DATEPICKER, true, Data.TITLEDPANE_ORDER),
  INVOICE_CURRENCY ("invoicecurrency", "Währung*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_ORDER),
  FROM_DATE ("fromdate", "Lieferdatum / Leistungszeitraum Von*", Data.ELEMENTTYPE_DATEPICKER, true, Data.TITLEDPANE_ORDER),
  TO_DATE ("todate", "Leistungszeitraum Bis", Data.ELEMENTTYPE_DATEPICKER, false, Data.TITLEDPANE_ORDER),
  COMMENT ("comment", "Mitteilung", Data.ELEMENTTYPE_TEXTAREA, false, Data.TITLEDPANE_ORDER),

  /*
   * Biller fields
   */
  BILLER_NAME ("billername", "Name*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_STREET ("billerstreet", "Strasse*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_ZIP ("billerzip", "PLZ*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_TOWN ("billertown", "Stadt*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_COUNTRY ("billercountry", "Land*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_BILLER),
  BILLER_EMAIL ("billeremail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER),
  BILLER_PHONE ("billerphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER),
  BILLER_CONTACT ("billercontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_BILLER),

  /*
   * Invoice recipient fields
   */
  INVOICERECIPIENT_NAME ("invoicerecipientname", "Name*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_STREET ("invoicerecipienstreet", "Strasse*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_ZIP ("invoicerecipienzip", "PLZ*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_TOWN ("invoicerecipientown", "Stadt*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_COUNTRY ("invoicerecipiencountry", "Land*", Data.ELEMENTTYPE_TEXTFIELD, true, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_EMAIL ("invoicerecipienemail", "E-Mail", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_PHONE ("invoicerecipienphone", "Telefon", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT),
  INVOICERECIPIENT_CONTACT ("invoicerecipiencontact", "Kontaktperson", Data.ELEMENTTYPE_TEXTFIELD, false, Data.TITLEDPANE_INVOICERECIPIENT),

  /*
   * Test fields
   */
  TEST_FROM ("testdp", "Von:", "dp", true, ""),
  TEST_TO ("testdp", "Bis:", "dp", true, ""),
  TEST_TA ("testta", "Test TextArea", "ta", false, ""),
  TEST_CP ("testcp", "Test CheckBox", "cb", false, "");

  private final String sID;
  private final String sLabelText;
  private final String sType;
  private final boolean bRequired;
  private final String sTitledPaneID;

  private EFormFields (final String id, final String bez, final String t, final boolean req, final String tID)
  {
    sID = id;
    sLabelText = bez;
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
