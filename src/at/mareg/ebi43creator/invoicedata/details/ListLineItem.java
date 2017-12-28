package at.mareg.ebi43creator.invoicedata.details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "orderPositionNumber", "description", "quantity", "unitPrice", "vatRate", "lineItemAmount" })
public class ListLineItem
{

  private Integer orderPositionNumber;
  private String description;
  private Quantity quantity;
  private Double unitPrice;
  private Integer vatRate;
  private Double lineItemAmount;

  public ListLineItem ()
  {
	quantity = new Quantity();  
  }

  @XmlElement (name = "OrderPositionNumber", namespace = Data.DEFAULT_NAMESPACE)
  public Integer getOrderPositionNumber ()
  {
    return orderPositionNumber;
  }

  @SuppressWarnings ("hiding")
  public void setOrderPositionNumber (final Integer orderPositionNumber)
  {
    this.orderPositionNumber = orderPositionNumber;
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

  @XmlElement (name = "Quantity", namespace = Data.DEFAULT_NAMESPACE)
  public Quantity getQuantity ()
  {
    return quantity;
  }

  @SuppressWarnings ("hiding")
  public void setQuantity (final Quantity quantity)
  {
    this.quantity = quantity;
  }

  @XmlElement (name = "UnitPrice", namespace = Data.DEFAULT_NAMESPACE)
  public Double getUnitPrice ()
  {
    return unitPrice;
  }

  @SuppressWarnings ("hiding")
  public void setUnitPrice (final Double unitPrice)
  {
    this.unitPrice = unitPrice;
  }

  @XmlElement (name = "VATRate", namespace = Data.DEFAULT_NAMESPACE)
  public Integer getVatRate ()
  {
    return vatRate;
  }

  @SuppressWarnings ("hiding")
  public void setVatRate (final Integer vatRate)
  {
    this.vatRate = vatRate;
  }

  @XmlElement (name = "LineItemAmount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getLineItemAmount ()
  {
    return lineItemAmount;
  }

  @SuppressWarnings ("hiding")
  public void setLineItemAmount (final Double lineItemAmount)
  {
    this.lineItemAmount = lineItemAmount;
  }

}
