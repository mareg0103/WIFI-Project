package at.mareg.ebi43creator.invoicedata.delivery;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.InvoiceDateManager;

@XmlType (propOrder = { "fromDate", "toDate" })
public class DeliveryPeriod
{

  private InvoiceDateManager idm;

  private String fromDate;
  private String toDate;

  protected DeliveryPeriod ()
  {
    idm = null;
  }

  public DeliveryPeriod (final String from, final String to, final InvoiceDateManager manager)
  {
    idm = manager;
    fromDate = idm.getLocalDateStringFromGermanFormattedDateString (from);
    toDate = idm.getLocalDateStringFromGermanFormattedDateString (to);
  }

  @XmlElement (name = "FromDate", namespace = Data.DEFAULT_NAMESPACE)
  public String getFromDate ()
  {
    return fromDate;
  }

  @SuppressWarnings ("hiding")
  public void setFromDate (final String fromDate)
  {
    this.fromDate = fromDate;
  }

  @XmlElement (name = "ToDate", namespace = Data.DEFAULT_NAMESPACE)
  public String getToDate ()
  {
    return toDate;
  }

  @SuppressWarnings ("hiding")
  public void setToDate (final String toDate)
  {
    this.toDate = toDate;
  }

}
