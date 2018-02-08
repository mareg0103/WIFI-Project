package at.mareg.ebi43creator.invoicedata.delivery;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.InvoiceDateManager;
import at.mareg.ebi43creator.invoicedata.address.Address;

@XmlType (propOrder = { "deliveryID", "deliveryDate", "deliveryPeriod", "address" })
public class Delivery
{

  private InvoiceDateManager idm;

  private String deliveryID;
  private String deliveryDate;
  private DeliveryPeriod deliveryPeriod;

  private Address address;

  public Delivery ()
  {

    deliveryID = null;
    deliveryDate = null;
    deliveryPeriod = null;

    address = null;

  }

  @XmlElement (name = "DeliveryID", namespace = Data.DEFAULT_NAMESPACE)
  public String getDeliveryID ()
  {
    return deliveryID;
  }

  @SuppressWarnings ("hiding")
  public void setDeliveryID (final String deliveryID)
  {
    this.deliveryID = deliveryID;
  }

  @XmlElement (name = "Date", namespace = Data.DEFAULT_NAMESPACE)
  public String getDeliveryDate ()
  {
    return deliveryDate;
  }

  @SuppressWarnings ("hiding")
  public void setDeliveryDate (final String deliveryDate)
  {
    this.deliveryDate = deliveryDate;
    this.deliveryPeriod = null;
  }

  @XmlElement (name = "Period", namespace = Data.DEFAULT_NAMESPACE)
  public DeliveryPeriod getDeliveryPeriod ()
  {
    return deliveryPeriod;
  }

  @SuppressWarnings ("hiding")
  public void setDeliveryPeriod (final DeliveryPeriod deliveryPeriod)
  {
    this.deliveryPeriod = deliveryPeriod;

    this.deliveryDate = null;
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

  public void setInvoiceDateManager (final InvoiceDateManager manager)
  {
    this.idm = manager;
  }

  // Internal working methods for saving invoice data
  public void setFromDateInternal (final String from)
  {
    if (deliveryPeriod == null)
    {
      deliveryDate = from;
    }
    else
    {
      String to = deliveryPeriod.getToDate ();

      if (idm.isFromDateAfterToDate (from, to))
      {
        deliveryDate = from;
        deliveryPeriod = null;
      }
      else
      {
        deliveryPeriod.setFromDate (from);
      }
    }
  }

  public void setToDateInternal (final String to)
  {
    if (deliveryPeriod == null)
    {
      deliveryPeriod = new DeliveryPeriod ();

      deliveryPeriod.setFromDate (deliveryDate);
      deliveryPeriod.setToDate (to);

      deliveryDate = null;
    }
    else
    {
      deliveryPeriod.setToDate (to);
    }
  }

  public void enableDeliveryAddress (final boolean activate)
  {
    if (activate)
      address = new Address ();
    else
      address = null;
  }

  // Set invoice data temporary (for testing purposes only)
  public void setTempData ()
  {
    final String fromDate = "20.10.2017";
    final String toDate = "23.10.2017";

    this.deliveryID = "LS123";
    this.setDeliveryDate (fromDate);
    this.setDeliveryPeriod (new DeliveryPeriod (fromDate, toDate, idm));

    // "salutation", "name", "street", "town", "zip", "country", "phone",
    // "email", "contact"

    this.setAddress (new Address ("Firma",
                                  "Test Leistung",
                                  "Teststr. 12",
                                  "Wien",
                                  "1234",
                                  "Österreich",
                                  "01 / 123 45 67",
                                  "test@leistung.at",
                                  "Frau Liebstöckl"));
  }

}
