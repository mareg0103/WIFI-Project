package at.mareg.ebi43creator.invoicedata.enums;

public enum ESurchargeType
{
  /*
   * Surcharge types entries for ComboBox surcharge type in
   * SurchargeAndDiscountPane.
   */
  AMOUNT ("Betrag"),
  PERCENT ("Prozent");

  /*
   * Variables and methods
   */
  private String surchargeType;

  private ESurchargeType (final String t)
  {
    surchargeType = t;
  }

  public String getType ()
  {
    return surchargeType;
  }

  public static ESurchargeType getFromIDOrNull (final String type)
  {
    if (type != null)
      for (final ESurchargeType e : values ())
        if (e.surchargeType.equals (type))
          return e;

    return null;
  }
}
