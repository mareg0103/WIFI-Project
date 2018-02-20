package at.mareg.ebi43creator.display.form.surcharge;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.enums.EVATRate;
import at.mareg.ebi43creator.invoicedata.reductionandsurcharge.Surcharge;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class SurchargeLine extends BasePane
{
	/*
	 * Save list line item this line belongs to
	 */
	private Surcharge surchargeItem;

	/*
	 * Help area instance
	 */
	private HelpArea helpArea;

	/*
	 * Pane elements
	 */
	private GridPane grid;
	private TextField surchargeField;
	private ComboBox<String> vatRateComboBox;
	private TextField commentField;
	private Button deleteThisLine;

	/*
	 * Line variables
	 */
	private Double surchargeValue;
	private Integer vatRate;
	private String comment;
	private Double totalGross;

	/*
	 * EventHandler
	 */
	private EventHandler<KeyEvent> onlyNumbersSemicolonMinusTwoDecimalDigits;

	protected SurchargeLine (final ResourceManager resman, final Surcharge surcharge)
	{
		super (resman);

		helpArea = resman.getHelpArea ();
		this.surchargeItem = surcharge;

		vatRate = EVATRate.getFromIDOrNull (Data.DEFAULT_VAT_RATE).getVatRateInteger ();

		initEventHandler ();
		init ();
	}

	private void initEventHandler ()
	{
		onlyNumbersSemicolonMinusTwoDecimalDigits = new EventHandler<KeyEvent> ()
		{
			@Override
			public void handle (KeyEvent event)
			{
				String text = ((TextField) event.getTarget ()).getText ();

				if ((text.length () > 0) && (event.getCharacter ().equals ("-")))
					event.consume ();

				if ((!(event.getCharacter ().matches ("[0-9]")) && (!(event.getCharacter ().equals (",")))
						&& (!(event.getCharacter ().equals ("-")))))
					event.consume ();

				int indexOfSemicolon = text.indexOf (",");
				if (indexOfSemicolon != -1)
					if (text.substring (indexOfSemicolon + 1).length () == 2)
						event.consume ();
			}
		};
	}

	/*
	 * Initialize surcharge line
	 */
	@Override
	protected void init ()
	{
		super.init ();

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
		 * Manual build of surcharge line
		 */

		/*
		 * Surcharge value
		 */
		EFormElement surchargeElement = EFormElement.SURCHARGE_VALUE;

		VBox surchargeBox = new VBox ();

		Label surchargeLabel = FormElementCreator.getStandardLabel (
				surchargeElement.getLabelText () + (surchargeElement.isRequired () ? "*" : ""), null);

		surchargeField = FormElementCreator.getStandardTextField (surchargeElement.getID (),
				surchargeElement.isRequired ());
		surchargeField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersSemicolonMinusTwoDecimalDigits);
		surchargeField.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					helpArea.show (surchargeElement.getID ());
				} else
				{
					if (!surchargeField.getText ().isEmpty ())
					{
						surchargeValue = TextFieldHelper.getDoubleFromString (surchargeField.getText ());
						surchargeField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (surchargeValue));
						surchargeItem.setAmount (surchargeValue);
					} else
					{
						surchargeValue = null;
						surchargeItem.setAmount (null);
					}

					calculateLine ();
				}
			}
		});

		surchargeBox.getChildren ().addAll (surchargeLabel, surchargeField);

		VBoxHelper.structureVBox (surchargeBox);
		grid.add (surchargeBox, 0, 0);

		/*
		 * VAT rate combo box
		 */
		EFormElement vatRateElement = EFormElement.SURCHARGE_VAT;

		VBox vatRateBox = new VBox ();

		Label vatRateLabel = FormElementCreator
				.getStandardLabel (vatRateElement.getLabelText () + (vatRateElement.isRequired () ? "*" : ""), null);

		vatRateComboBox = FormElementCreator.getVatRateComboBox (vatRateElement.getID ());
		surchargeItem.setVatRate (EVATRate.getFromIDOrNull (Data.DEFAULT_VAT_RATE).getVatRateInteger ());
		vatRateComboBox.setOnAction (e -> {
			vatRate = EVATRate.getFromIDOrNull (vatRateComboBox.getSelectionModel ().getSelectedItem ())
					.getVatRateInteger ();
			surchargeItem.setVatRate (vatRate);

			calculateLine ();
		});
		vatRateComboBox.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					helpArea.show (vatRateElement.getID ());
				}
			}
		});

		vatRateBox.getChildren ().addAll (vatRateLabel, vatRateComboBox);

		VBoxHelper.structureVBox (vatRateBox);
		grid.add (vatRateBox, 1, 0);

		/*
		 * Surcharge comment
		 */
		EFormElement commentElement = EFormElement.SURCHARGE_COMMENT;

		VBox commentBox = new VBox ();

		Label commentLabel = FormElementCreator
				.getStandardLabel (commentElement.getLabelText () + (commentElement.isRequired () ? "*" : ""), null);

		commentField = FormElementCreator.getStandardTextField (commentElement.getID (), commentElement.isRequired ());
		commentField.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					helpArea.show (commentElement.getID ());
				} else
				{
					comment = commentField.getText ();
					surchargeItem.setComment (comment);

					// calculateLine ();
				}
			}
		});

		commentBox.getChildren ().addAll (commentLabel, commentField);

		VBoxHelper.structureVBox (commentBox);
		grid.add (commentBox, 2, 0, 2, 1);

		/*
		 * Delete this line
		 */
		deleteThisLine = new Button (EFormElement.SURCHARGE_REMOVE.getLabelText (),
				new ImageView (new Image ("at/mareg/ebi43creator/display/images/Loeschen_50x33.png")));
		deleteThisLine.hoverProperty ().addListener ( (observable) -> {
			helpArea.show (EFormElement.SURCHARGE_REMOVE.getID ());
		});
		deleteThisLine.setOnAction (e -> {
			System.out.println ("surchargeItem in dieser SurchargeLine = " + surchargeItem.toString ());
			System.out.println ("Alle eingetragenen surchargeItems:");
			for (Surcharge l : rm.getInvoiceData ().getSurchargeList ())
				System.out.println (" " + l.toString ());
			System.out.println ("BaseAmount = " + surchargeItem.getBaseAmount ());
			System.out.println ("VATRate = " + surchargeItem.getVatRate ());
			System.out.println ("Comment = " + surchargeItem.getComment ());
			System.out.println ("Amount = " + surchargeItem.getAmount ());
		});

		grid.add (deleteThisLine, 4, 0);

		/*
		 * Add grid to line
		 */
		this.setBorder (new Border (new BorderStroke (Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
				null, BorderWidths.DEFAULT, new Insets (3, 3, 3, 3))));
		this.add (grid, 0, 0);
	}

	/*
	 * Calculate this line and refresh total net and total gross in SurchargeArea
	 */
	private void calculateLine ()
	{
		if (surchargeValue == null)
			surchargeValue = Double.valueOf (0);

		double netAumount = surchargeValue.doubleValue ();
		double rate = 1 + ((double) vatRate / 100);

		totalGross = netAumount * rate;

		rm.getSurchargeDiscountPane ().refreshTotalNetandTotalGross ();
	}

	/*
	 * Getter / Setter
	 */
	public Surcharge getSurcharge ()
	{
		return surchargeItem;
	}

	public Double getSurchargeLineTotalNet ()
	{
		return surchargeValue;
	}

	public Double getSurchargeLineTotalGross ()
	{
		return totalGross;
	}
}
