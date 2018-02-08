package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SurchargeDiscountPane extends BorderPane
{
	/**
	 * Class to show help texts to form elements
	 * 
	 * @author Martin Regitnig
	 */

	/*
	 * Instance of ResourceManager because this pane doesn't extend BasePane
	 */
	final ResourceManager rm;

	/*
	 * Pane elements
	 */
	private ScrollPane scroll;

	private Accordion ac;
	private GridPane grid_Surcharge;
	private GridPane grid_Discount;
	private TitledPane tp_Surcharge;
	private TitledPane tp_Discount;

	public SurchargeDiscountPane (final ResourceManager resman)
	{
		rm = resman;

		init ();

		ac = new Accordion ();

		tp_Surcharge = new TitledPane ();
		tp_Surcharge.setText (Data.TITLEDPANE_SURCHARGE);

		tp_Discount = new TitledPane ();
		tp_Discount.setText (Data.TITLEDPANE_DISCOUNT);

		grid_Surcharge = new GridPane ();
		grid_Surcharge.setPadding (Data.BASEPANE_PADDING);
		grid_Surcharge.setHgap (Data.BASEPANE_HVGAP);
		grid_Surcharge.setVgap (grid_Surcharge.getHgap ());

		grid_Discount = new GridPane ();
		grid_Discount.setPadding (Data.BASEPANE_PADDING);
		grid_Discount.setHgap (Data.BASEPANE_HVGAP);
		grid_Discount.setVgap (grid_Discount.getHgap ());

		tp_Surcharge.setContent (grid_Surcharge);
		tp_Discount.setContent (grid_Discount);

		ac.getPanes ().addAll (tp_Surcharge, tp_Discount);
		ac.setExpandedPane (tp_Surcharge);

		this.setCenter (ac);

	}

	/*
	 * Initialize SurchargeDiscountPane
	 */
	protected void init ()
	{

	}
}
