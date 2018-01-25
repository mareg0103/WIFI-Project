package at.mareg.ebi43creator.invoicedata.enums;

public enum ECurrency
{
  /*
   * Currency entries for ComboBox invoice currency in OrderPane. Currently only
   * Euro is provided
   */
  EURO ("Euro (€)", "EUR");

  private String invoiceCurrency;
  private String invoiceCurrencyShort;

  private ECurrency (final String currency, final String currencyShort)
  {
    invoiceCurrency = currency;
    invoiceCurrencyShort = currencyShort;
  }

  public String getInvoiceCurrency ()
  {
    return invoiceCurrency;
  }

  public String getInvoiceCurrencyShort ()
  {
    return invoiceCurrencyShort;
  }

  public static ECurrency getFromIDOrNull (final String sID)
  {
    if (sID != null)
      for (final ECurrency e : values ())
        if (e.invoiceCurrency.equals (sID))
          return e;

    return null;
  }

}