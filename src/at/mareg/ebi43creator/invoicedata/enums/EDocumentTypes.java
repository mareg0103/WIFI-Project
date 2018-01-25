package at.mareg.ebi43creator.invoicedata.enums;

public enum EDocumentTypes
{
  INVOICE ("Rechnung", "Invoice"),
  CREDIT_MEMO ("Gutschrift", "CreditMemo"),
  INVOICE_FOR_ADVANCED_PAYMENT ("Vorauszahlung", "InvoiceForAdvancePayment"),
  INVOICE_FOR_PARTIAL_DELIVERY ("Teilrechnung", "InvoiceForPartialDelivery"),
  FINAL_SETTLEMENT ("Schlussrechnung", "FinalSettlement");

  final String elementText;
  final String elementID;

  private EDocumentTypes (final String text, final String id)
  {
    elementText = text;
    elementID = id;
  }

  public String getElementText ()
  {
    return elementText;
  }

  public String getElementID ()
  {
    return elementID;
  }
}
