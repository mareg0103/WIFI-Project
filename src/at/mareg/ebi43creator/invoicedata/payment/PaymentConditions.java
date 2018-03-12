package at.mareg.ebi43creator.invoicedata.payment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;

@XmlType (propOrder = { "dueDate", "discounts" })
public class PaymentConditions
{
  /**
   * Class to save payment conditions
   *
   * @author Martin Regitnig
   */

  /*
   * ResourceManager instance
   */
  private ResourceManager rm;

  private String dueDate;
  private List <Discount> discounts;

  public PaymentConditions ()
  {}

  public PaymentConditions (final ResourceManager resman)
  {
    rm = resman;
  }

  /*
   * Add an empty discount to list and invoke method to create a new discount
   * line
   */
  public void addEmptyDiscount ()
  {
    if (discounts == null)
      discounts = new ArrayList <> ();

    final Discount d = new Discount ();
    discounts.add (d);
    rm.getSurchargeDiscountPane ().getDiscountArea ().addEmptyDiscountLine (d);
  }

  /*
   * Remove a discount
   */
  public void removeDiscount (final Discount discount)
  {
    if (discounts.indexOf (discount) >= 0)
      discounts.remove (discount);

    if (discounts.size () == 0)
      discounts = null;
  }

  /*
   * Getter / Setter
   */
  @XmlElement (name = "DueDate", namespace = Data.DEFAULT_NAMESPACE)
  public String getDueDate ()
  {

    return dueDate;

  }

  public void setDueDate (final String dueDate)
  {

    this.dueDate = dueDate;

  }

  @XmlElement (name = "Discount", namespace = Data.DEFAULT_NAMESPACE)
  public List <Discount> getDiscounts ()
  {

    return discounts;

  }

  public void setDiscounts (final List <Discount> discounts)
  {

    this.discounts = discounts;

  }
}
