package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "paymentDate", "percentage" })
public class Discount
{

  private String paymentDate;
  private Double percentage;

  public Discount ()
  {

  }

  @XmlElement (name = "PaymentDate", namespace = Data.DEFAULT_NAMESPACE)
  public String getPaymentDate ()
  {

    return paymentDate;

  }

  public void setPaymentDate (final String paymentDate)
  {

    this.paymentDate = paymentDate;

  }

  @XmlElement (name = "Percentage", namespace = Data.DEFAULT_NAMESPACE)
  public Double getPercentage ()
  {

    return percentage;

  }

  public void setPercentage (final Double percentage)
  {

    this.percentage = percentage;

  }

}
