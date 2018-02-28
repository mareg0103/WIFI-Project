package at.mareg.ebi43creator.invoicedata.reductionandsurcharge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "baseAmount", "amount", "vatRate", "comment" })
public class Surcharge
{
  private Double baseAmount;
  private Double amount;
  private Integer vatRate;
  private String comment;

  public Surcharge ()
  {}

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

  @XmlElement (name = "VATRate", namespace = Data.DEFAULT_NAMESPACE)
  public Integer getVatRate ()
  {
    return vatRate;
  }

  @SuppressWarnings ("hiding")
  public void setVatRate (final Integer vatRate)
  {
    this.vatRate = vatRate;
  }

  @XmlElement (name = "Comment", namespace = Data.DEFAULT_NAMESPACE)
  public String getComment ()
  {
    return comment;
  }

  @SuppressWarnings ("hiding")
  public void setComment (String comment)
  {
    this.comment = comment;
  }
}
