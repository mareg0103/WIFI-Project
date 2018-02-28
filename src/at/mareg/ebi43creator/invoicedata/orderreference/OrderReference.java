package at.mareg.ebi43creator.invoicedata.orderreference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.InputChecker;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;

@XmlType (propOrder = { "orderId", "referenceDate", "description" })
public class OrderReference
{
  /*
   * ResourceManager instance
   */
  private ResourceManager rm;
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

  public void setResourceManagerInternal (final ResourceManager resman)
  {
    rm = resman;
  }

  public void checkIfOrderIDIsGovernmentOrderNumber ()
  {
    isGovernmentOrderNumber = InputChecker.orderIDisGovermentOrderNumber (orderId);

    EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.setIsRequired (isGovernmentOrderNumber);

    rm.getDetailsPane ().getInvoiceLineArea ().setOrderPostionNumberInLinesStatus (isGovernmentOrderNumber);
  }

  public boolean isOrderIDGovernmentOrderNumber ()
  {
    return isGovernmentOrderNumber;
  }

}
