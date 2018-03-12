package at.mareg.ebi43creator.invoicedata.enums;

public enum ECurrency
{
  /**
   * Currency entries for ComboBox invoice currency in OrderPane. Currently only
   * Euro is provided
   *
   * @author Martin REgitnig
   */

  /*
   * Values
   */
  EURO ("Euro (€)", "EUR");

  /*
   * Data variables
   */
  private String invoiceCurrency;
  private String invoiceCurrencyShort;

  private ECurrency (final String currency, final String currencyShort)
  {
    invoiceCurrency = currency;
    invoiceCurrencyShort = currencyShort;
  }

  /*
   * Returns an element or null based on invoice currency string
   */
  public static ECurrency getFromInvoiceCurrencyOrNull (final String currency)
  {
    if (currency != null)
      for (final ECurrency e : values ())
        if (e.invoiceCurrency.equals (currency))
          return e;

    return null;
  }

  /*
   * Returns an element or null based on invoice currency short string
   */
  public static ECurrency getFromInvoiceCurrencyShortOrNull (final String currencyShort)
  {
    if (currencyShort != null)
      for (final ECurrency e : values ())
        if (e.invoiceCurrencyShort.equals (currencyShort))
          return e;

    return null;
  }

  /*
   * Getter
   */
  public String getInvoiceCurrency ()
  {
    return invoiceCurrency;
  }

  public String getInvoiceCurrencyShort ()
  {
    return invoiceCurrencyShort;
  }
}
