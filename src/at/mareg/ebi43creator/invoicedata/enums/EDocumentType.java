package at.mareg.ebi43creator.invoicedata.enums;

public enum EDocumentType
{
  /**
   * Available document types in application
   *
   * @author Martin Regitnig
   */

  /*
   * Values
   */
  INVOICE ("Rechnung", "Invoice"),
  CREDIT_MEMO ("Gutschrift", "CreditMemo"),
  INVOICE_FOR_ADVANCED_PAYMENT ("Vorauszahlung", "InvoiceForAdvancePayment"),
  INVOICE_FOR_PARTIAL_DELIVERY ("Teilrechnung", "InvoiceForPartialDelivery"),
  FINAL_SETTLEMENT ("Schlussrechnung", "FinalSettlement");

  /*
   * Data variables
   */
  final String elementText;
  final String elementID;

  private EDocumentType (final String text, final String id)
  {
    elementText = text;
    elementID = id;
  }

  /*
   * Getter
   */
  public String getElementText ()
  {
    return elementText;
  }

  public String getElementID ()
  {
    return elementID;
  }
}
