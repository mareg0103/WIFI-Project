package at.mareg.ebi43creator.invoicedata.invoicerecipient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.address.Address;
import at.mareg.ebi43creator.invoicedata.orderreference.OrderReference;

@XmlType (propOrder = { "vatID", "orderReference", "address", "billerID" })
public class InvoiceRecipient
{
  /**
   * Class to save invoice recipient data
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables
   */
  private String vatID;
  private OrderReference orderReference;
  private Address address;
  private String billerID;

  public InvoiceRecipient ()
  {
    orderReference = new OrderReference ();
    address = new Address ();
  }

  /*
   * Set resource manager in order reference
   */
  public void setResourceManagerInOrderReferenceInternal (final ResourceManager resman)
  {
    orderReference.setResourceManagerInternal (resman);
  }

  /*
   * Getter / Setter
   */
  @XmlElement (name = "VATIdentificationNumber", namespace = Data.DEFAULT_NAMESPACE)
  public String getVatID ()
  {
    return vatID;
  }

  @SuppressWarnings ("hiding")
  public void setVatID (final String vatID)
  {
    this.vatID = vatID;
  }

  @XmlElement (name = "OrderReference", namespace = Data.DEFAULT_NAMESPACE)
  public OrderReference getOrderReference ()
  {
    return orderReference;
  }

  @SuppressWarnings ("hiding")
  public void setOrderReference (final OrderReference orderReference)
  {
    this.orderReference = orderReference;
  }

  @XmlElement (name = "Address", namespace = Data.DEFAULT_NAMESPACE)
  public Address getAddress ()
  {
    return address;
  }

  @SuppressWarnings ("hiding")
  public void setAddress (final Address address)
  {
    this.address = address;
  }

  @XmlElement (name = "BillersInvoiceRecipientID", namespace = Data.DEFAULT_NAMESPACE)
  public String getBillerID ()
  {
    return billerID;
  }

  @SuppressWarnings ("hiding")
  public void setBillerID (final String billerID)
  {
    this.billerID = billerID;
  }
}
