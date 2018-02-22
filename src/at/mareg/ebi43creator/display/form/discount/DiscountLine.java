package at.mareg.ebi43creator.display.form.discount;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.payment.Discount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DiscountLine extends BasePane
{
	/*
	 * Save discount item this line belongs to
	 */
	private Discount discount;

	/*
	 * Help area instance
	 */
	private HelpArea helpArea;

	/*
	 * Pane elements
	 */
	private GridPane grid;
	private TextField discountPercentField;
	private DatePicker ifPaidUntilDatePicker;
	private Button deleteThisLine;

	/*
	 * Line variables
	 */
	private Double discountPercent;
	private String ifPaidUntil;

	/*
	 * EventHandler
	 */
	private EventHandler<KeyEvent> onlyNumbersSemicolonTwoDecimalDigits;

	public DiscountLine (final ResourceManager rm, final Discount d)
	{
		super (rm);

		discount = d;
		helpArea = rm.getHelpArea ();

		_initEventHandler ();
		init ();
	}

	/*
	 * Initialize event handler
	 */
	private void _initEventHandler ()
	{
		onlyNumbersSemicolonTwoDecimalDigits = new EventHandler<KeyEvent> ()
		{
			@Override
			public void handle (KeyEvent event)
			{
				String text = ((TextField) event.getTarget ()).getText ();

				if ((!(event.getCharacter ().matches ("[0-9]")) && (!(event.getCharacter ().equals (",")))))
					event.consume ();

				int indexOfSemicolon = text.indexOf (",");
				if (indexOfSemicolon != -1)
					if (text.substring (indexOfSemicolon + 1).length () == 2)
						event.consume ();

				System.out.println ("CursorPosition: " + ((TextField) event.getTarget ()).getCaretPosition ());
			}
		};
	}

	/*
	 * Initialize discount line
	 */
	@Override
	protected void init ()
	{
		// super.init ();

		grid = new GridPane ();
		for (int i = 0; i < 4; i++)
		{
			ColumnConstraints column = new ColumnConstraints ((Data.SURCHARGE_SCROLLPANE_WIDTH - 255) / 4);
			grid.getColumnConstraints ().add (column);
		}
		grid.setPadding (Data.LINE_PADDING);
		grid.setHgap (Data.LINE_HVGAP);
		grid.setVgap (grid.getHgap ());

		/*
		 * Discount percent
		 */
		EFormElement percentElement = EFormElement.DISCOUNT_PERCENT;

		VBox percentBox = new VBox ();

		Label percentLabel = FormElementCreator
				.getStandardLabel (percentElement.getLabelText () + (percentElement.isRequired () ? "*" : ""), null);

		discountPercentField = FormElementCreator.getStandardTextField (percentElement.getID (),
				percentElement.isRequired ());
		discountPercentField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersSemicolonTwoDecimalDigits);
		discountPercentField.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					helpArea.show (percentElement.getID ());
				}

			}
		});

		percentBox.getChildren ().addAll (percentLabel, discountPercentField);

		VBoxHelper.structureVBox (percentBox);
		grid.add (percentBox, 0, 0, 2, 1);

		this.add (grid, 0, 0);
	}

	/*
	 * Getter / Setter
	 */
	public Discount getDiscount ()
	{
		return discount;
	}
}
