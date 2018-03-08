package at.mareg.ebi43creator.invoicedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.InvoiceDateManager;
import at.mareg.ebi43creator.display.resources.MyNamespaceMapper;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.biller.Biller;
import at.mareg.ebi43creator.invoicedata.delivery.Delivery;
import at.mareg.ebi43creator.invoicedata.details.Details;
import at.mareg.ebi43creator.invoicedata.enums.ECurrency;
import at.mareg.ebi43creator.invoicedata.invoicerecipient.InvoiceRecipient;
import at.mareg.ebi43creator.invoicedata.payment.PaymentConditions;
import at.mareg.ebi43creator.invoicedata.payment.PaymentMethod;
import at.mareg.ebi43creator.invoicedata.reductionandsurcharge.Surcharge;
import at.mareg.ebi43creator.invoicedata.tax.Tax;

@XmlRootElement (name = "Invoice", namespace = Data.DEFAULT_NAMESPACE)
@XmlType (propOrder = { "invoiceNumber",
                        "invoiceDate",
                        "delivery",
                        "biller",
                        "invoiceRecipient",
                        "details",
                        "surchargeList",
                        "tax",
                        "totalGrossAmount",
                        "payableAmount",
                        "paymentMethod",
                        "paymentConditions",
                        "comment" })
public class InvoiceData
{
  private InvoiceDateManager idm;

  private ResourceManager rm;

  private String documentType;
  private String generatingSystem;
  private String invoiceCurrency;
  private String language;
  private String invoiceNumber;
  private String invoiceDate;

  private Delivery delivery;

  private Biller biller;

  private InvoiceRecipient invoiceRecipient;

  private Details details;

  private List <Surcharge> surchargeList;

  private Tax tax;

  private Double totalGrossAmount;
  private Double payableAmount;

  private PaymentMethod paymentMethod;

  private PaymentConditions paymentConditions;

  private String comment;

  public InvoiceData ()
  {}

  @SuppressWarnings ("hiding")
  public InvoiceData (final InvoiceDateManager idm, final String docType, final ResourceManager resman)
  {
    this.idm = idm;

    rm = resman;

    documentType = docType;
    generatingSystem = Data.GENERATING_SYSTEM;
    invoiceCurrency = ECurrency.EURO.getInvoiceCurrencyShort ();
    language = Data.INVOICE_LANGUAGE;
    invoiceNumber = null;
    invoiceDate = idm.getCurrentDateAsString ();

    delivery = new Delivery ();
    delivery.setInvoiceDateManager (idm);

    biller = new Biller ();

    invoiceRecipient = new InvoiceRecipient ();
    invoiceRecipient.setResourceManagerInOrderReferenceInternal (resman);

    details = new Details ();

    tax = new Tax ();
    tax.setResourceManagerInternal (resman);

    paymentMethod = new PaymentMethod ();
  }

  @XmlAttribute (name = "DocumentType", namespace = Data.DEFAULT_NAMESPACE)
  public String getDocumentType ()
  {
    return documentType;
  }

  @XmlAttribute (name = "GeneratingSystem", namespace = Data.DEFAULT_NAMESPACE)
  public String getGeneratingSystem ()
  {
    return generatingSystem;
  }

  @XmlAttribute (name = "InvoiceCurrency", namespace = Data.DEFAULT_NAMESPACE)
  public String getInvoiceCurrency ()
  {
    return invoiceCurrency;
  }

  @XmlAttribute (name = "Language", namespace = Data.DEFAULT_NAMESPACE)
  public String getLanguage ()
  {
    return language;
  }

  @XmlElement (name = "InvoiceNumber", namespace = Data.DEFAULT_NAMESPACE)
  public String getInvoiceNumber ()
  {
    return invoiceNumber;
  }

  @XmlElement (name = "InvoiceDate", namespace = Data.DEFAULT_NAMESPACE)
  public String getInvoiceDate ()
  {
    return invoiceDate;
  }

  @XmlElement (name = "Delivery", namespace = Data.DEFAULT_NAMESPACE)
  public Delivery getDelivery ()
  {
    return delivery;
  }

  @XmlElement (name = "Biller", namespace = Data.DEFAULT_NAMESPACE)
  public Biller getBiller ()
  {
    return biller;
  }

  @XmlElement (name = "InvoiceRecipient", namespace = Data.DEFAULT_NAMESPACE)
  public InvoiceRecipient getInvoiceRecipient ()
  {
    return invoiceRecipient;
  }

  @XmlElement (name = "Details", namespace = Data.DEFAULT_NAMESPACE)
  public Details getDetails ()
  {
    return details;
  }

  @XmlElementWrapper (name = "ReductionAndSurchargeDetails", namespace = Data.DEFAULT_NAMESPACE)
  @XmlElement (name = "Surcharge", namespace = Data.DEFAULT_NAMESPACE)
  public List <Surcharge> getSurchargeList ()
  {
    return surchargeList;
  }

  @XmlElement (name = "Tax", namespace = Data.DEFAULT_NAMESPACE)
  public Tax getTax ()
  {
    return tax;
  }

  @XmlElement (name = "TotalGrossAmount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getTotalGrossAmount ()
  {
    return totalGrossAmount;
  }

  @XmlElement (name = "PayableAmount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getPayableAmount ()
  {
    return payableAmount;
  }

  @XmlElement (name = "PaymentMethod", namespace = Data.DEFAULT_NAMESPACE)
  public PaymentMethod getPaymentMethod ()
  {
    return paymentMethod;
  }

  @XmlElement (name = "PaymentConditions", namespace = Data.DEFAULT_NAMESPACE)
  public PaymentConditions getPaymentConditions ()
  {
    return paymentConditions;
  }

  @XmlElement (name = "Comment", namespace = Data.DEFAULT_NAMESPACE)
  public String getComment ()
  {
    return comment;
  }

  @SuppressWarnings ("hiding")
  public boolean setDocumentType (final String documentType)
  {
    this.documentType = documentType;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setGeneratingSystem (final String generatingSystem)
  {
    this.generatingSystem = generatingSystem;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setInvoiceCurrency (final String invoiceCurrency)
  {
    this.invoiceCurrency = invoiceCurrency;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setLanguage (final String language)
  {
    this.language = language;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setInvoiceNumber (final String invoiceNumber)
  {
    this.invoiceNumber = invoiceNumber;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setInvoiceDate (final String invoiceDate)
  {
    this.invoiceDate = invoiceDate;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setDelivery (final Delivery delivery)
  {
    this.delivery = delivery;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setBiller (final Biller biller)
  {
    this.biller = biller;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setInvoiceRecipient (final InvoiceRecipient invoiceRecipient)
  {
    this.invoiceRecipient = invoiceRecipient;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setDetails (final Details details)
  {
    this.details = details;
    return true;
  }

  @SuppressWarnings ("hiding")
  public void setSurchargeList (final List <Surcharge> surchargeList)
  {
    this.surchargeList = surchargeList;
  }

  @SuppressWarnings ("hiding")
  public boolean setTax (final Tax tax)
  {
    this.tax = tax;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setTotalGrossAmount (final Double totalGrossAmount)
  {
    this.totalGrossAmount = totalGrossAmount;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setPayableAmount (final Double payableAmount)
  {
    this.payableAmount = payableAmount;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setPaymentMethod (final PaymentMethod paymentMethod)
  {
    this.paymentMethod = paymentMethod;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setPaymentContitions (final PaymentConditions paymentConditions)
  {
    this.paymentConditions = paymentConditions;
    return true;
  }

  @SuppressWarnings ("hiding")
  public boolean setComment (final String comment)
  {
    this.comment = comment;
    return true;
  }

  /*
   * Set resource manager after loading an XML
   */
  public void setResourceManagerInternal (final ResourceManager resman)
  {
    rm = resman;
    invoiceRecipient.setResourceManagerInOrderReferenceInternal (resman);
    tax.setResourceManagerInternal (resman);
  }

  /*
   * Set invoice date manager after loading an XML
   */
  public void setInvoiceDateManagerInternal (final InvoiceDateManager invdatman)
  {
    idm = invdatman;
    delivery.setInvoiceDateManager (invdatman);
  }

  /*
   * Add an empty sucharge item
   */
  public void addEmptySurchargeItem ()
  {
    if (surchargeList == null)
      surchargeList = new ArrayList <> ();

    final Surcharge s = new Surcharge ();
    surchargeList.add (s);

    rm.getSurchargeDiscountPane ().getSurchargeArea ().addEmptySurchargeLine (s);
  }

  /*
   * Remove an surcharge item
   */
  public void removeSurchargeItem (final Surcharge surcharge)
  {
    if (surchargeList != null && surchargeList.contains (surcharge))
    {
      surchargeList.remove (surcharge);

      if (surchargeList.size () == 0)
        surchargeList = null;
    }
  }

  /*
   * Add a payment conditions element
   */
  public void addPaymentContitions ()
  {
    if (paymentConditions == null)
      paymentConditions = new PaymentConditions (rm);
  }

  /*
   * Remove the payment conditions element
   */
  public void removePaymentContitions ()
  {
    paymentConditions = null;
  }

  /*
   * Write invoice data to xml file
   */
  public boolean serializeInvoiceAsXML (final String path)
  {
    try
    {

      final Marshaller m = JAXBContext.newInstance (InvoiceData.class).createMarshaller ();
      m.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf (true));
      m.setProperty (Marshaller.JAXB_ENCODING, "UTF-8");
      m.setProperty ("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper ());
      m.marshal (this, new FileOutputStream (path));

      return true;

    }
    catch (final Exception e)
    {
      System.out.println ("Fehler beim Schreiben der Datei!");
      e.printStackTrace ();

      return false;
    }
  }

  /*
   * Read existing xml invoice file
   */
  public InvoiceData readXMLInvoice (final String path)
  {
    try (FileInputStream aIS = new FileInputStream (path))
    {

      final Unmarshaller um = JAXBContext.newInstance (InvoiceData.class).createUnmarshaller ();
      return (InvoiceData) um.unmarshal (aIS);

    }
    catch (final Exception e)
    {
      System.out.println ("Fehler beim Lesen der Datei!");
      e.printStackTrace ();
    }

    return null;
  }
}
