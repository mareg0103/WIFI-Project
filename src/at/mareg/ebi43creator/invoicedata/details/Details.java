package at.mareg.ebi43creator.invoicedata.details;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.enums.EVATRate;

public class Details
{
	private ResourceManager rm;

	private List<ListLineItem> listLineItems;

	public Details ()
	{
		listLineItems = new ArrayList<> ();
	}

	@XmlElementWrapper (name = "ItemList", namespace = Data.DEFAULT_NAMESPACE)
	@XmlElement (name = "ListLineItem", namespace = Data.DEFAULT_NAMESPACE)
	public List<ListLineItem> getListLineItems ()
	{
		return listLineItems;
	}

	@SuppressWarnings ("hiding")
	public void setListLineItems (final List<ListLineItem> listLineItems)
	{
		this.listLineItems = listLineItems;
	}

	public void setResourceManagerInternal (final ResourceManager resman)
	{
		rm = resman;
	}

	/*
	 * List line management internal
	 */
	public void removeListLineItem (final ListLineItem listLineItem)
	{
		if (listLineItems.indexOf (listLineItem) != -1)
			listLineItems.remove (listLineItem);
	}

	public void addEmptyListLineItem ()
	{
		if (listLineItems != null)
		{
			ListLineItem l = new ListLineItem ();

			listLineItems.add (l);
			rm.getDetailsPane ().getInvoiceLineArea ().addEmptyInvoiceLine (l);
		}
	}

	// Temporary methods
	public void setTempData ()
	{
		this.addEmptyListLineItem ();
		final ListLineItem lli = listLineItems.get (0);
		lli.setDescription ("Testartikel");
		lli.setUnitPrice (Double.valueOf (123.25));
		lli.getQuantity ().setQuantity (Double.valueOf (2));
		lli.getQuantity ().setUnit ("Stück");
		lli.addSurcharge (246.50, -6.5);

		lli.setVatRate (EVATRate.VAT_TWENTY.getVatRateInteger ());
		lli.setLineItemAmount (Double.valueOf (240));
	}

}
