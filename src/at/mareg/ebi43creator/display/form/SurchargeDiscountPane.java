package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.form.discount.DiscountArea;
import at.mareg.ebi43creator.display.form.surcharge.SurchargeArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
	private Accordion ac;
	private GridPane grid;
	private TitledPane tp_Surcharge;
	private TitledPane tp_Discount;
	private SurchargeArea surchargeArea;
	private DiscountArea discountArea;
	private int rightAreaRow;

	public SurchargeDiscountPane (final ResourceManager resman)
	{
		rm = resman;

		init ();
	}

	/*
	 * Initialize SurchargeDiscountPane
	 */
	protected void init ()
	{
		/*
		 * Center content of BorderPane
		 */
		ac = new Accordion ();

		tp_Surcharge = new TitledPane ();
		surchargeArea = new SurchargeArea (rm);
		tp_Surcharge.setContent (surchargeArea);
		tp_Surcharge.setText (Data.TITLEDPANE_SURCHARGE_NAME);

		tp_Discount = new TitledPane ("Skonto", new DiscountArea (rm));
		discountArea = new DiscountArea (rm);
		tp_Discount.setContent (discountArea);
		tp_Discount.setText (Data.TITLEDPANE_DISCOUNT_NAME);

		ac.getPanes ().addAll (tp_Surcharge, tp_Discount);

		/*
		 * Right content of BorderPane
		 */

		grid = new GridPane ();
		grid.setPadding (Data.BASEPANE_PADDING);
		grid.setHgap (Data.BASEPANE_HVGAP);
		grid.setVgap (grid.getHgap ());

		for (final EFormElement eb : EFormElement.values ())
		{
			if (eb.getTiteldPaneID ().equals (Data.SURCHARGE_RIGHT_AREA))
			{
				String elementID = eb.getID ();
				String labelText = eb.getLabelText ();

				if (eb.getType ().equals (Data.ELEMENTTYPE_BUTTON))
				{
					Button b = FormElementCreator.getStandardButton (elementID, labelText);
					b.setPrefWidth (Data.SURCHARGE_RIGHT_AREA_COMPONENT_WIDTH);

					if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_ADDSURCHARGEBUTTON.getID ()))
					{
						b.setOnAction (e -> {
							rm.getInvoiceData ().addEmptySurchargeItem ();
						});
					} else
					{
						b.setOnAction (e -> {
							rm.getInvoiceData ().getPaymentConditions ().addEmptyDiscount ();
						});
					}

					grid.add (b, 0, rightAreaRow);

					rightAreaRow++;
				}

				if (eb.getType ().equals (Data.ELEMENTTYPE_TEXTFIELD))
				{
					VBox v = new VBox ();

					TextField t = FormElementCreator.getStandardTextField (elementID, eb.isRequired ());
					t.setPrefWidth (Data.SURCHARGE_RIGHT_AREA_COMPONENT_WIDTH);

					if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_TOTALNETAMOUNT.getID ()))
						v.getChildren ()
								.add (FormElementCreator.getStandardLabel (labelText, new Insets (20, 0, 0, 0)));

					if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_TOTALGROSSAMOUNT.getID ()))
						v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));

					v.getChildren ().add (t);
					grid.add (v, 0, rightAreaRow);

					rightAreaRow++;
				}
			}
		}

		this.setCenter (ac);
		this.setRight (grid);
	}
}
