package at.mareg.ebi43creator.invoicedata.reductionandsurcharge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "baseAmount", "amount", "percentage", "vatRate" })
public class Surcharge
{
  private Double baseAmount;
  private Double amount;
  private Integer percentage;
  private Integer vatRate;

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

  @XmlElement (name = "Percentage", namespace = Data.DEFAULT_NAMESPACE)
  public Integer getPercentage ()
  {
    return percentage;
  }

  @SuppressWarnings ("hiding")
  public void setPercentage (final Integer percentage)
  {
    this.percentage = percentage;
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
}
