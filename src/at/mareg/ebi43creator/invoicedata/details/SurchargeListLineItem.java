package at.mareg.ebi43creator.invoicedata.details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "baseAmount", "amount", "comment" })
public class SurchargeListLineItem
{
  /**
   * Class to save surcharge data of an invoice line (list line item)
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables
   */
  private Double baseAmount;
  private Double amount;
  private String comment;

  public SurchargeListLineItem ()
  {}

  /*
   * Getter / Setter
   */
  @XmlElement (name = "BaseAmount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getBaseAmount ()
  {
    return baseAmount;
  }

  @SuppressWarnings ("hiding")
  public void setBaseAmount (final Double baseAmount)
  {
    this.baseAmount = baseAmount;
  }

  @XmlElement (name = "Amount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getAmount ()
  {
    return amount;
  }

  @SuppressWarnings ("hiding")
  public void setAmount (final Double amount)
  {
    this.amount = amount;
  }

  @XmlElement (name = "Comment", namespace = Data.DEFAULT_NAMESPACE)
  public String getComment ()
  {
    return comment;
  }

  @SuppressWarnings ("hiding")
  public void setComment (final String comment)
  {
    this.comment = comment;
  }

}
