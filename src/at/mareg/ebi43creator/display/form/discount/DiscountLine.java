package at.mareg.ebi43creator.display.form.discount;

import java.time.LocalDate;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.InvoiceDateManager;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.payment.Discount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DiscountLine extends BasePane
{
	/*
	 * Save discount item this line belongs to
	 */
	private Discount discount;

	/*
	 * DiscountArea instance
	 */
	private DiscountArea discountArea;

	/*
	 * Help area instance
	 */
	private HelpArea helpArea;

	/*
	 * DateManager instance
	 */
	private InvoiceDateManager invoiceDateManager;

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
		discountArea = rm.getSurchargeDiscountPane ().getDiscountArea ();
		helpArea = rm.getHelpArea ();
		invoiceDateManager = rm.getInvoiceDateManager ();

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
				} else
				{
					String text = discountPercentField.getText ();
					List<Discount> d = rm.getInvoiceData ().getPaymentConditions ().getDiscounts ();

					if (text != null && !text.isEmpty ())
					{
						discountPercent = TextFieldHelper.getDoubleFromString (text);
					} else
					{
						discountPercent = null;
					}

					d.get (d.indexOf (discount)).setPercentage (discountPercent);

				}

			}
		});

		percentBox.getChildren ().addAll (percentLabel, discountPercentField);

		VBoxHelper.structureVBox (percentBox);
		grid.add (percentBox, 0, 0, 2, 1);

		/*
		 * Discount if paid until date
		 */
		EFormElement ifPaidUntilDateElement = EFormElement.DISCOUNT_UNTIL_DATE;

		VBox untilDateBox = new VBox ();

		Label ifPaidUntilDateLabel = FormElementCreator.getStandardLabel (
				ifPaidUntilDateElement.getLabelText () + (ifPaidUntilDateElement.isRequired () ? "*" : ""), null);

		ifPaidUntilDatePicker = FormElementCreator.getStandardDatePicker (ifPaidUntilDateElement.getID (),
				ifPaidUntilDateElement.isRequired ());
		ifPaidUntilDatePicker.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					helpArea.show (ifPaidUntilDateElement.getID ());
				} else
				{
					int listSize = 0;

					if (discountArea.getDiscountLineList () != null)
					{
						listSize = discountArea.getDiscountLineList ().size ();

						if (listSize == 1)
						{

						}

						if (listSize == 2)
						{
							// if (discountArea.getDiscountLine (this))
							{
								/*
								 * Hier verstehe ich nicht ganz -> Wenn ich das oberhalb stehene if aktiviere,
								 * wird es rot unterringelt und eclipse sagt mir
								 * 
								 * "The method getDiscountLine(DiscountLine) in the type DiscountArea is not applicable for the arguments (new ChangeListener<Boolean>(){})"
								 * 
								 * Ich kapier nicht ganz, wo das ChangeListener<Boolean> herkommt?!? this sollte
								 * hier doch diese InvoiceLine sein, auch wenn ich mich hier in einem
								 * ChangeListener bezüglich FocusProperty befinde.
								 * 
								 * Komischerweise wenn ich im "deleteThisLine"-Button gleich unterhalb die
								 * Funktion discountArea.removeDiscountLine (this); aufrufe, gibt es keine
								 * Probleme, die Zeile und der dazugeöhrige Discount in
								 * InvoiceData/PaymentConditions/discountList wird entfernt.
								 */
							}
						}
					}
				}

			}
		});

		untilDateBox.getChildren ().addAll (ifPaidUntilDateLabel, ifPaidUntilDatePicker);

		VBoxHelper.structureVBox (untilDateBox);
		grid.add (untilDateBox, 2, 0, 2, 1);

		/*
		 * Delete this line button
		 */
		EFormElement deleteThisLineElement = EFormElement.DISCOUNT_REMOVE;

		deleteThisLine = new Button (deleteThisLineElement.getLabelText (),
				new ImageView (new Image ("at/mareg/ebi43creator/display/images/Loeschen_50x33.png")));
		deleteThisLine.hoverProperty ().addListener ( (observable) -> {
			helpArea.show (deleteThisLineElement.getID ());
		});
		deleteThisLine.setOnAction (e -> {
			discountArea.removeDiscountLine (this);
			rm.getSurchargeDiscountPane ().setAddDiscountButtonToEnable ();
		});

		grid.add (deleteThisLine, 4, 0);

		/*
		 * Add grid to this
		 */
		this.setBorder (new Border (new BorderStroke (Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
				null, BorderWidths.DEFAULT, new Insets (3, 3, 3, 3))));
		this.add (grid, 0, 0);
	}

	/*
	 * Check if new day cell factorys must be set
	 */
	private void _checkDayCellFactorys ()
	{
		if (discountArea.getDiscountLineList ().size () == 0)
		{
			ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (
					LocalDate.parse (rm.getInvoiceDateManager ().getCurrentDateAsString ()).plusDays (1l)));
		} else
		{
			System.out.println ("Es ist bereits eine Skontozeile vorhanden");

			DatePicker firstDatePicker = discountArea.getDiscountLineList ().get (0).getifPaidUntilDatePicker ();

			if (firstDatePicker.getValue () == null)
			{
				ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (
						LocalDate.parse (rm.getInvoiceDateManager ().getCurrentDateAsString ()).plusDays (1l)));
			} else
			{
				ifPaidUntilDatePicker.setDayCellFactory (
						invoiceDateManager.getDayCellFectoryDisableBefore (firstDatePicker.getValue ().plusDays (1l)));
			}
		}
	}

	/*
	 * Getter / Setter
	 */
	public Discount getDiscount ()
	{
		return discount;
	}

	public DatePicker getifPaidUntilDatePicker ()
	{
		return ifPaidUntilDatePicker;
	}
}
