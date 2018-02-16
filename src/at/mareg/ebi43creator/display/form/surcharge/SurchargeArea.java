package at.mareg.ebi43creator.display.form.surcharge;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
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
		scroll.setPrefHeight (Data.DETAILS_SCROLLSURCHARGE_HEIGHT); // to change
		scroll.setMaxHeight (scroll.getPrefHeight ());
		scroll.setPrefWidth (Data.DETAILS_SCROLLSURCHARGE_WIDTH); // to change
		scroll.setMaxWidth (scroll.getPrefWidth ());

		grid = new GridPane ();

		scroll.setContent (grid);
		this.getChildren ().add (scroll);
	}
}
