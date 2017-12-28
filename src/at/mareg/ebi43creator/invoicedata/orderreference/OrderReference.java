package at.mareg.ebi43creator.invoicedata.orderreference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "orderid", "referenceDate", "description" })
public class OrderReference
{
  private String orderid;
  private String referenceDate;
  private String description;
  
  protected OrderReference ()
  {}

  public OrderReference (final String id)
  {
    orderid = id;
  }

  @XmlElement (name = "OrderID", namespace = Data.DEFAULT_NAMESPACE)
  public String getOrderid ()
  {
    return orderid;
  }

  @SuppressWarnings ("hiding")
  public void setOrderid (final String orderid)
  {
    this.orderid = orderid;
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
