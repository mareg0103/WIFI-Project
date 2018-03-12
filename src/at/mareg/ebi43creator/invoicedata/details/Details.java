package at.mareg.ebi43creator.invoicedata.details;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;

public class Details
{
  /**
   * Class to save details data (invoice lines)
   *
   * @author Martin Regitnig
   */

  /*
   * Resource manager instance
   */
  private ResourceManager rm;

  /*
   * List line item list (invoice line data)
   */
  private List <ListLineItem> listLineItems;

  public Details ()
  {
    listLineItems = new ArrayList <> ();
  }

  /*
   * Remove an list line item from list
   */
  public void removeListLineItem (final ListLineItem listLineItem)
  {
    if (listLineItems.indexOf (listLineItem) != -1)
      listLineItems.remove (listLineItem);
  }

  /*
   * Add an empty list line item to list and invoke method to create a new
   * invoice line
   */
  public void addEmptyListLineItem ()
  {
    if (listLineItems != null)
    {
      final ListLineItem l = new ListLineItem ();

      listLineItems.add (l);
      rm.getDetailsPane ().getInvoiceLineArea ().addEmptyInvoiceLine (l);
    }
  }

  /*
   * Set current resource manager instance after loading an XML file
   */
  public void setResourceManagerInternal (final ResourceManager resman)
  {
    rm = resman;
  }

  /*
   * Getter / Setter
   */
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
}
