package at.mareg.ebi43creator.invoicedata.invoicerecipient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.address.Address;
import at.mareg.ebi43creator.invoicedata.orderreference.OrderReference;

@XmlType (propOrder = { "vatID", "orderID", "address", "billerID" })
public class InvoiceRecipient
{

  private String vatID;
  private OrderReference orderID;
  private Address address;
  private String billerID;

  public InvoiceRecipient ()
  {

  }

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
  public OrderReference getOrderID ()
  {
    return orderID;
  }

  @SuppressWarnings ("hiding")
  public void setOrderID (final OrderReference orderID)
  {
    this.orderID = orderID;
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

  public void setTempData ()
  {
    this.setVatID ("ATU00000000");
    this.setOrderID (new OrderReference ("Z01"));
    this.setAddress (new Address ("Firma",
                                  "Test Empfanger",
                                  "Teststr. 201",
                                  "Wien",
                                  "1234",
                                  "Österreich",
                                  "01 / 654 12 38",
                                  "test@empfaenger.at",
                                  "Mutzi Rene"));
    this.setBillerID ("13469");

  }

}
