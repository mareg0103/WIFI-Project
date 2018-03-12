package at.mareg.ebi43creator.invoicedata.biller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.address.Address;

@XmlType (propOrder = { "vatID", "address", "supplierID" })
public class Biller
{
  /**
   * Class to save biller data
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables
   */
  private String vatID;
  private Address address;
  private String supplierID;

  public Biller ()
  {
    address = new Address ();
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

  @XmlElement (name = "InvoiceRecipientsBillerID", namespace = Data.DEFAULT_NAMESPACE)
  public String getSupplierID ()
  {
    return supplierID;
  }

  @SuppressWarnings ("hiding")
  public void setSupplierID (final String supplierID)
  {
    this.supplierID = supplierID;
  }
}
