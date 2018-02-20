package at.mareg.ebi43creator.display.form.surcharge;

import java.util.ArrayList;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.reductionandsurcharge.Surcharge;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class SurchargeArea extends BasePane
{
	/**
	 * Class to add surcharge lines to the current document (negative surcharge is
	 * an reduction)
	 * 
	 * @author Martin Regitnig
	 */

	/*
	 * Surcharge lines
	 */
	private List<SurchargeLine> surchargeLineList;

	/*
	 * Pane elements
	 */
	private ScrollPane scroll;
	private GridPane grid;
	private int areaRow;

	public SurchargeArea (final ResourceManager resman)
	{
		super (resman);

		init ();
	}

	@Override
	protected void init ()
	{
		super.init ();

		scroll = new ScrollPane ();
		scroll.setPrefHeight (Data.SURCHARGE_SCROLLPANE_HEIGHT); // to change
		scroll.setMaxHeight (scroll.getPrefHeight ());
		scroll.setPrefWidth (Data.SURCHARGE_SCROLLPANE_WIDTH); // to change
		scroll.setMaxWidth (scroll.getPrefWidth ());

		grid = new GridPane ();

		scroll.setContent (grid);
		this.getChildren ().add (scroll);
	}

	/*
	 * (Re) Build invoice line area
	 */
	private void _buildArea ()
	{
		areaRow = 0;

		for (final SurchargeLine sl : surchargeLineList)
		{
			grid.add (sl, 0, areaRow);
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
	 * Add a new surcharge item to list
	 */
	public void addEmptySurchargeLine (final Surcharge surcharge)
	{
		if (surchargeLineList == null)
			surchargeLineList = new ArrayList<> ();

		surchargeLineList.add (new SurchargeLine (rm, surcharge));

		_refreshArea ();
	}

	/*
	 * Remove a surcharge line
	 */
	public void removeSurchargeLine (final SurchargeLine line)
	{
		if (surchargeLineList.contains (line))
		{
			surchargeLineList.remove (line);
			rm.getInvoiceData ().removeSurchargeItem (line.getSurcharge ());

			_refreshArea ();
		}
	}

	/*
	 * Create surcharge line list after loading a XML
	 */
	public void createSurchargeLineAfterLoading ()
	{
		for (final Surcharge s : rm.getInvoiceData ().getSurchargeList ())
		{
			SurchargeLine sl = new SurchargeLine (rm, s);

			// InvoiceLineFiller.fillLineWithLoadedData (il);

			surchargeLineList.add (sl);
		}

		_refreshArea ();
	}

	/*
	 * Getter / Setter
	 */
	public List<SurchargeLine> getSurchargeLineList ()
	{
		return surchargeLineList;
	}
}
