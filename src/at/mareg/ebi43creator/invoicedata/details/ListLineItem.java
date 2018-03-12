package at.mareg.ebi43creator.invoicedata.details;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "orderPositionNumber",
                        "description",
                        "quantity",
                        "unitPrice",
                        "vatRate",
                        "surcharge",
                        "taxExemption",
                        "lineItemAmount" })
public class ListLineItem
{
  /**
   * Class to save invoice line values
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables for saving an calculating values
   */
  private Integer orderPositionNumber;
  private String description;
  private Quantity quantity;
  private Double unitPrice;
  private List <SurchargeListLineItem> surcharge;
  private Integer vatRate;
  private String taxExemption;
  private Double lineItemAmount;

  public ListLineItem ()
  {
    quantity = new Quantity ();
  }

  /*
   * Sets the tax exemption reason
   */
  public void setTaxExemptionReasonInternal (final String reason)
  {
    this.vatRate = null;
    this.taxExemption = reason;
  }

  /*
   * Removes the tax exemption reason
   */
  public void removeTaxExemptionReasonInternal (final Integer vat)
  {
    this.taxExemption = null;
    this.vatRate = vat;
  }

  /*
   * Adds a surcharge
   */
  public void addSurcharge (final Double totalNetAmount, final Double surcharge)
  {
    this.surcharge = new ArrayList <> ();

    final SurchargeListLineItem s = new SurchargeListLineItem ();
    s.setBaseAmount (totalNetAmount);
    s.setAmount (surcharge);

    this.surcharge.add (s);
  }

  /*
   * Adds a comment to surcharge
   */
  public void addSurchargeComment (final String comment)
  {
    if (this.surcharge != null)
    {
      surcharge.get (0).setComment (comment);
    }
  }

  /*
   * Removes the surcharge
   */
  public void removeSurchare ()
  {
    this.surcharge = null;
  }

  /*
   * Getters / Setters
   */
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

  @XmlElementWrapper (name = "ReductionAndSurchargeListLineItemDetails", namespace = Data.DEFAULT_NAMESPACE)
  @XmlElement (name = "SurchargeListLineItem", namespace = Data.DEFAULT_NAMESPACE)
  public List <SurchargeListLineItem> getSurcharge ()
  {
    return surcharge;
  }

  @SuppressWarnings ("hiding")
  public void setSurcharge (final List <SurchargeListLineItem> surcharge)
  {
    this.surcharge = surcharge;
  }

  @XmlElement (name = "TaxExemption", namespace = Data.DEFAULT_NAMESPACE)
  public String getTaxExemption ()
  {
    return taxExemption;
  }

  @SuppressWarnings ("hiding")
  public void setTaxExemption (final String taxExemption)
  {
    this.taxExemption = taxExemption;
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
