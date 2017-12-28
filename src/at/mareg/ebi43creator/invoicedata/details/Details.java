package at.mareg.ebi43creator.invoicedata.details;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import at.mareg.ebi43creator.display.resources.Data;

public class Details
{

  private List <ListLineItem> listLineItems;

  public Details ()
  {
    listLineItems = new ArrayList<> ();
  }

  @XmlElementWrapper (name = "ItemList", namespace = Data.DEFAULT_NAMESPACE)
  @XmlElement (name = "ListLineItem", namespace = Data.DEFAULT_NAMESPACE)
  public List <ListLineItem> getListLineItems ()
  {
    return listLineItems;
  }

  @SuppressWarnings ("hiding")
  public void setListLineItems (final List <ListLineItem> listLineItems)
  {
    this.listLineItems = listLineItems;
  }

  public void addListLineItem (final ListLineItem listLineItem)
  {
    if (listLineItem != null)
      listLineItems.add (listLineItem);
  }

  public void removeListLineItem (final ListLineItem listLineItem)
  {
    if (listLineItems.indexOf (listLineItem) != -1)
      listLineItems.remove (listLineItem);
  }

  public void addEmptyListLineItem ()
  {
    listLineItems.add (new ListLineItem ());
  }

  public void setTempData ()
  {
    this.addEmptyListLineItem ();
    final ListLineItem lli = listLineItems.get (0);
    lli.setDescription ("Testartikel");
    lli.setUnitPrice(Double.valueOf(123.25));
    lli.getQuantity ().setQuantity(Double.valueOf (2));
    lli.getQuantity().setUnit("Stück");
    lli.setVatRate (Integer.valueOf (20));
    lli.setLineItemAmount (Double.valueOf (246.50));
  }

}
