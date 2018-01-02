package at.mareg.ebi43creator.invoicedata.biller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.address.Address;
import at.mareg.ebi43creator.invoicedata.furtheridentification.FurtherIdentification;

@XmlType (propOrder = { "vatID", "furtherFN", "furtherFS", "furtherFBG", "address", "supplierID" })
public class Biller
{

  private String vatID;
  private FurtherIdentification furtherFN;
  private FurtherIdentification furtherFS;
  private FurtherIdentification furtherFBG;
  private Address address;
  private String supplierID;

  public Biller ()
  {
    address = new Address ();
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

  @XmlElement (name = "FurtherIdentification", namespace = Data.DEFAULT_NAMESPACE)
  public FurtherIdentification getFurtherFN ()
  {
    return furtherFN;
  }

  @SuppressWarnings ("hiding")
  public void setFurtherFN (final FurtherIdentification furtherFN)
  {
    this.furtherFN = furtherFN;
  }

  @XmlElement (name = "FurtherIdentification", namespace = Data.DEFAULT_NAMESPACE)
  public FurtherIdentification getFurtherFS ()
  {
    return furtherFS;
  }

  @SuppressWarnings ("hiding")
  public void setFurtherFS (final FurtherIdentification furtherFS)
  {
    this.furtherFS = furtherFS;
  }

  @XmlElement (name = "FurtherIdentification", namespace = Data.DEFAULT_NAMESPACE)
  public FurtherIdentification getFurtherFBG ()
  {
    return furtherFBG;
  }

  @SuppressWarnings ("hiding")
  public void setFurtherFBN (final FurtherIdentification furtherFBG)
  {
    this.furtherFBG = furtherFBG;
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

  public void setTempData ()
  {
    this.setVatID ("ATU00000000");
    this.furtherFN = new FurtherIdentification ("123456FN", Data.FURTERIDENTIFICATION_TYPE_FN);
    this.furtherFS = new FurtherIdentification ("Wien", Data.FURTERIDENTIFICATION_TYPE_FS);
    this.furtherFBG = new FurtherIdentification ("Wien", Data.FURTERIDENTIFICATION_TYPE_FBG);
    this.setAddress (new Address ("Firma",
                                  "Test Ersteller",
                                  "Teststr. 34",
                                  "Wien",
                                  "1234",
                                  "�sterreich",
                                  "01 / 987 65 43",
                                  "test@ersteller.at",
                                  "Herr Mayer"));
    this.setSupplierID ("0050012345");

  }

}
