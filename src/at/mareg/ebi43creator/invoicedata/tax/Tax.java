package at.mareg.ebi43creator.invoicedata.tax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.reductionandsurcharge.Surcharge;

public class Tax
{
  /**
   * Class to save vat items and calculate the vats
   *
   * @author Martin Regitnig
   */

  /*
   * ResourceManager instance
   */
  private ResourceManager rm;

  /*
   * List of vat items (could never be null)
   */
  private List <VATItem> vatItems;

  public Tax ()
  {
    vatItems = new ArrayList <> ();
  }

  /*
   * Add an empty vat item for a specific vat rate
   */
  public void addEmptyVatItemWithVatRate (final Integer vr)
  {
    boolean vrExists = false;

    for (final VATItem vi : vatItems)
      if (vi.getVatRate () == vr)
      {
        vrExists = true;
        break;
      }

    if (!vrExists)
    {
      final VATItem vi = new VATItem (vr, null);
      vi.setTaxedAmount (Double.valueOf (0d));

      vatItems.add (vi);
    }
  }

  /*
   * Add an empty vat item for a tax exemption
   */
  public void addEmptyVatItemWithTaxExemption (final String te)
  {
    boolean teExists = false;

    for (final VATItem vi : vatItems)
      if (vi.getTaxExemption () == te)
      {
        teExists = true;
        break;
      }

    if (!teExists)
    {
      final VATItem vi = new VATItem (null, te);
      vi.setTaxedAmount (Double.valueOf (0d));

      vatItems.add (vi);
    }
  }

  /*
   * Run through all list line items and surcharges and build vat items
   */
  public void calculateVATItems ()
  {
    final InvoiceData id = rm.getInvoiceData ();
    VATItem vi = null;

    /*
     * Delete vat items, total gross an payable amount and recalculate
     */
    vatItems.clear ();
    id.setTotalGrossAmount (null);
    id.setPayableAmount (null);

    /*
     * List line items
     */
    for (final ListLineItem lli : id.getDetails ().getListLineItems ())
    {
      final Integer vatRate = lli.getVatRate ();
      final String taxExemption = lli.getTaxExemption ();

      if (vatRate != null)
        addEmptyVatItemWithVatRate (vatRate);
      else
        addEmptyVatItemWithTaxExemption (taxExemption);

      if (taxExemption == null)
      {
        for (final VATItem v : vatItems)
          if (v.getVatRate () != null && v.getVatRate () == vatRate)
          {
            vi = v;
            break;
          }
      }
      else
      {
        for (final VATItem v : vatItems)
          if (v.getTaxExemption () != null && v.getTaxExemption ().equals (taxExemption))
          {
            vi = v;
            break;
          }
      }

      vi.setTaxedAmount (Double.valueOf (vi.getTaxedAmount ().doubleValue () + lli.getLineItemAmount ()));
      vi.calculateVatInternal ();
    }

    /*
     * Surcharges
     */
    final List <Surcharge> sl = id.getSurchargeList ();

    if (sl != null)
    {
      for (final Surcharge s : sl)
      {
        addEmptyVatItemWithVatRate (s.getVatRate ());

        for (final VATItem vis : vatItems)
        {
          if (vis.getVatRate () == s.getVatRate ())
          {
            vi = vis;
            break;
          }
        }

        vi.setTaxedAmount (Double.valueOf (vi.getTaxedAmount ().doubleValue () + s.getAmount ().doubleValue ()));
        vi.calculateVatInternal ();
      }
    }

    /*
     * Total gross and payable amount
     */
    Double totalGrossAmount = Double.valueOf (0d);

    for (final VATItem vic : vatItems)
    {
      totalGrossAmount += Double.valueOf (vic.getTaxedAmount ().doubleValue () + vic.getAmount ().doubleValue ());
    }

    id.setTotalGrossAmount (totalGrossAmount);
    id.setPayableAmount (totalGrossAmount);
  }

  /*
   * Getters / Setters
   */
  @XmlElementWrapper (name = "VAT", namespace = Data.DEFAULT_NAMESPACE)
  @XmlElement (name = "VATItem", namespace = Data.DEFAULT_NAMESPACE)
  public List <VATItem> getVatItems ()
  {
    return vatItems;
  }

  @SuppressWarnings ("hiding")
  public void setVatItems (final List <VATItem> vatItems)
  {
    this.vatItems = vatItems;
  }

  public void setResourceManagerInternal (final ResourceManager resman)
  {
    rm = resman;
  }
}
