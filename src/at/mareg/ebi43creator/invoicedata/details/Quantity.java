package at.mareg.ebi43creator.invoicedata.details;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import at.mareg.ebi43creator.display.resources.Data;

public class Quantity
{
  /**
   * Class to save quantity and unit data
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables
   */
  private String unit;
  private Double quantity;

  public Quantity ()
  {}

  /*
   * Getter / Setter
   */
  @XmlAttribute (name = "Unit", namespace = Data.DEFAULT_NAMESPACE)
  public String getUnit ()
  {
    return unit;
  }

  @SuppressWarnings ("hiding")
  public void setUnit (final String unit)
  {
    this.unit = unit;
  }

  @XmlValue
  public Double getQuantity ()
  {
    return quantity;
  }

  @SuppressWarnings ("hiding")
  public void setQuantity (final Double quantity)
  {
    this.quantity = quantity;
  }

}
