package at.mareg.ebi43creator.invoicedata.enums;

public enum EUnit
{
  /**
   * Unit name entries for invoice line combo box
   *
   * @author Martin Regitnig
   */

  /*
   * Values
   */
  UNIT_01 ("Eins/Mal"),
  UNIT_02 ("Pauschal"),
  UNIT_03 ("Stück"),
  UNTI_04 ("Stunden");

  /*
   * Data variables
   */
  final String unitDescription;

  private EUnit (final String desc)
  {
    unitDescription = desc;
  }

  /*
   * Returns an element or null based on a given ID
   */
  public static EUnit getFromIDOrNull (final String desc)
  {
    if (desc != null)
      for (final EUnit e : values ())
        if (e.unitDescription.equals (desc))
          return e;

    return null;
  }

  /*
   * Getter / Setter
   */
  public String getUnitDescription ()
  {
    return unitDescription;
  }
}
