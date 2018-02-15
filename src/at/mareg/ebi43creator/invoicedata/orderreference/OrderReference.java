package at.mareg.ebi43creator.invoicedata.orderreference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.InputChecker;

@XmlType (propOrder = { "orderId", "referenceDate", "description" })
public class OrderReference
{
  private String orderId;
  private String referenceDate;
  private String description;
  private boolean isGovernmentOrderNumber;

  public OrderReference ()
  {
    isGovernmentOrderNumber = false;
  }

  @XmlElement (name = "OrderID", namespace = Data.DEFAULT_NAMESPACE)
  public String getOrderId ()
  {
    return orderId;
  }

  @SuppressWarnings ("hiding")
  public void setOrderId (final String orderId)
  {
    this.orderId = orderId;
  }

  @XmlElement (name = "ReferenceDate", namespace = Data.DEFAULT_NAMESPACE)
  public String getReferenceDate ()
  {
    return referenceDate;
  }

  @SuppressWarnings ("hiding")
  public void setReferenceDate (final String referenceDate)
  {
    this.referenceDate = referenceDate;
  }

  @XmlElement (name = "Description", namespace = Data.DEFAULT_NAMESPACE)
  public String getDescription ()
  {
    return description;
  }

  @SuppressWarnings ("hiding")
  public void setDescription (final String description)
  {
    this.description = description;
  }

  public void checkIfOrderIDIsGovernmentOrderNumber ()
  {
    isGovernmentOrderNumber = InputChecker.orderIDisGovermentOrderNumber (orderId);
  }

  public boolean isOrderIDGovernmentOrderNumber ()
  {
    return isGovernmentOrderNumber;
  }

}
