package at.mareg.ebi43creator.invoicedata.tax;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "taxedAmount", "vatRate", "amount" })
public class VATItem
{

  private Double taxedAmount;
  private Integer vatRate;
  private Double amount;

  protected VATItem ()
  {}
  
  public VATItem (final Integer vr)
  {

    vatRate = vr;

  }

  @XmlElement (name = "TaxedAmount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getTaxedAmount ()
  {
    return taxedAmount;
  }

  @SuppressWarnings ("hiding")
  public void setTaxedAmount (final Double taxedAmount)
  {
    this.taxedAmount = taxedAmount;
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

  @XmlElement (name = "Amount", namespace = Data.DEFAULT_NAMESPACE)
  public Double getAmount ()
  {
    return amount;
  }

  @SuppressWarnings ("hiding")
  public void setAmount (final Double amount)
  {
    this.amount = amount;
  }

  public Double calculateVat ()
  {
    return Double.valueOf ((taxedAmount.doubleValue () / 100) * vatRate.doubleValue ());
  }

}
