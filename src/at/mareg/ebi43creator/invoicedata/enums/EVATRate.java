package at.mareg.ebi43creator.invoicedata.enums;

public enum EVATRate
{
  /**
   * VAT rate entries for multiple ComboBoxes
   *
   * @author Martin Regitnig
   */

  /*
   * Values
   */
  VAT_ZERO ("0%", 0),
  VAT_SEVEN ("7%", 7),
  VAT_EIGHT ("8%", 8),
  VAT_TEN ("10%", 10),
  VAT_TWELFE ("12%", 12),
  VAT_THIRTEEN ("13%", 13),
  VAT_NINETEEN ("19%", 19),
  VAT_TWENTY ("20%", 20);

  /*
   * Data variables
   */
  final String vatRateOutput;
  final Integer vatRateInteger;

  private EVATRate (final String out, final Integer i)
  {
    vatRateOutput = out;
    vatRateInteger = i;
  }

  /*
   * Returns an element or null based on a given vat rate output
   */
  public static EVATRate getFromIDOrNull (final String vatRate)
  {
    if (vatRate != null)
      for (final EVATRate e : values ())
        if (e.vatRateOutput.equals (vatRate))
          return e;

    return null;
  }

  /*
   * Getter / Setter
   */
  public String getVatRateOutput ()
  {
    return vatRateOutput;
  }

  public Integer getVatRateInteger ()
  {
    return vatRateInteger;
  }
}
