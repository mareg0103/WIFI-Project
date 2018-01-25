package at.mareg.ebi43creator.invoicedata.orderreference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "orderId", "referenceDate", "description" })
public class OrderReference
{
  private String orderId;
  private String referenceDate;
  private String description;

  public OrderReference ()
  {}

  public OrderReference (final String id)
  {
    orderId = id;
  }

  @XmlElement (name = "OrderID", namespace = Data.DEFAULT_NAMESPACE)
  public String getOrderId ()
  {
    return orderId;
  }

  @SuppressWarnings ("hiding")
  public void setOrderid (final String orderid)
  {
    this.orderId = orderid;
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

}
