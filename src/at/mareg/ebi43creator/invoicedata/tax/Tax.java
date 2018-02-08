package at.mareg.ebi43creator.invoicedata.tax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import at.mareg.ebi43creator.display.resources.Data;

public class Tax
{

  private List <VATItem> vatItems;

  public Tax ()
  {

    vatItems = new ArrayList <> ();

  }

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

  public void addEmptyVatItemWithVatRate (final Integer vr)
  {
    boolean vrExists = false;

    for (final VATItem vi : vatItems)
      if (vi.getVatRate () == vr)
        vrExists = true;

    if (!vrExists)
      vatItems.add (new VATItem (vr));
  }

  public void setTempData ()
  {
    this.addEmptyVatItemWithVatRate (Integer.valueOf (20));
    final VATItem vi = vatItems.get (0);
    vi.setTaxedAmount (Double.valueOf (240));
    vi.setAmount (vi.calculateVat ());

  }

}
