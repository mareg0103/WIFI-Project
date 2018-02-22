package at.mareg.ebi43creator.display.form.discount;

import java.util.ArrayList;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.payment.Discount;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class DiscountArea extends BasePane
{
	/*
	 * Discount lines
	 */
	private List<DiscountLine> discountLineList;

	/*
	 * Pane elements
	 */
	private GridPane grid;
	private int areaRow;

	public DiscountArea (ResourceManager resman)
	{
		super (resman);

		init ();
	}

	@Override
	protected void init ()
	{
		super.init ();

		grid = new GridPane ();

		this.getChildren ().add (grid);
	}

	/*
	 * (Re) Build discount line area
	 */
	private void _buildArea ()
	{
		areaRow = 0;

		for (final DiscountLine dl : discountLineList)
		{
			grid.add (dl, 0, areaRow);
			areaRow++;
		}
	}

	/*
	 * Remove all nodes an rebuild grid
	 */
	private void _refreshArea ()
	{
		ObservableList<Node> nodes = grid.getChildren ();
		grid.getChildren ().removeAll (nodes);

		_buildArea ();
	}

	/*
	 * Add a new discount item to list
	 */
	public void addEmptyDiscountLine (final Discount discount)
	{
		if (discountLineList == null)
			discountLineList = new ArrayList<> ();

		DiscountLine dl = new DiscountLine (rm, discount);
		discountLineList.add (dl);

		_refreshArea ();
	}

	/*
	 * Remove a discount line
	 */
	public void removeDiscountLine (final DiscountLine line)
	{
		if (discountLineList.contains (line))
		{
			rm.getInvoiceData ().getPaymentConditions ().removeDiscount (line.getDiscount ());
			discountLineList.remove (line);

			_refreshArea ();
		}
	}

	/*
	 * Get an dicount line
	 */
	public DiscountLine getDiscountLine (final DiscountLine line)
	{
		return discountLineList.get (discountLineList.indexOf (line));
	}

	/*
	 * Create discount line list after loading a XML
	 */
	public void createSurchargeLineAfterLoading ()
	{
		for (final Discount d : rm.getInvoiceData ().getPaymentConditions ().getDiscounts ())
		{
			DiscountLine dl = new DiscountLine (rm, d);

			// InvoiceLineFiller.fillLineWithLoadedData (il);

			discountLineList.add (dl);
		}

		_refreshArea ();
	}

	/*
	 * Getter / Setter
	 */
	public List<DiscountLine> getDiscountLineList ()
	{
		return discountLineList;
	}

	public void showInvoiceLineList ()
	{
		for (final DiscountLine dl : discountLineList)
			System.out.println (((DiscountLine) dl).toString ());
	}
}
