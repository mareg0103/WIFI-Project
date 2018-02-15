package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.enums.ESurchargeType;
import at.mareg.ebi43creator.invoicedata.enums.EUnit;
import at.mareg.ebi43creator.invoicedata.enums.EVATRate;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * Creates standard and extended elements and provides methods for them (if
 * necessary)
 * 
 * @author Martin Regitnig
 */

public final class FormElementCreator
{

	private FormElementCreator ()
	{
	}

	/*
	 * Get labels
	 */
	public static Label getStandardLabel (final String text, final Insets insets)
	{
		Label l = new Label (text);

		if (insets != null)
			l.setPadding (insets);

		return l;
	}

	public static Label getDisabledLookingLabel (final String text, final Insets insets)
	{
		Label l = new Label (text);

		l.setTextFill (Color.DARKGRAY);

		if (insets != null)
			l.setPadding (insets);

		return l;
	}

	/*
	 * Get text fields
	 */
	public static TextField getStandardTextField (final String id, final boolean required)
	{
		final TextField t = new TextField ();

		t.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
		t.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		t.setId (id);

		return t;
	}

	public static TextField getDisabledTextField (final String id, final boolean required)
	{
		final TextField t = new TextField ();

		t.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
		t.disableProperty ().set (true);
		t.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		t.setId (id);

		return t;
	}

	// Invoice line text field
	public static TextField getInvoiceLineTextField (final String id, final boolean required)
	{
		final TextField t = new TextField ();

		t.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		t.setId (id);

		return t;
	}

	/*
	 * Get text areas
	 */
	public static TextArea getStandardTextArea (final String id, final boolean required)
	{
		final TextArea t = new TextArea ();

		t.setPrefWidth (2 * Data.DEFAULT_COMPONENT_WIDTH);
		t.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		t.setId (id);

		return t;
	}

	// Invoice line text Area
	public static TextArea getInvoiceLineTextArea (final String id, final boolean required)
	{
		final TextArea t = new TextArea ();

		t.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		t.setMaxHeight (75d);
		t.setId (id);

		return t;
	}

	/*
	 * Get date pickers
	 */
	public static DatePicker getStandardDatePicker (final String id, final boolean required)
	{
		final DatePicker dp = new DatePicker ();

		dp.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
		dp.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		dp.setEditable (false);
		dp.setId (id);

		return dp;
	}

	/*
	 * Receives a GridPane, search through it and returns the DatePicker with the
	 * given id else returns null
	 */
	public static DatePicker getDatePickerWithID (final GridPane grid, final String id)
	{
		for (final Node n : grid.getChildren ())
		{
			if (n.getClass () == VBox.class)
			{
				for (final Node vn : ((VBox) n).getChildren ())
				{
					if (vn.getClass () == DatePicker.class)
					{
						if (((DatePicker) vn).getId ().equals (id))
						{
							return ((DatePicker) vn);
						}
					}
				}
			}
		}

		return null;
	}

	/*
	 * Get check boxes
	 */
	public static CheckBox getStandardCheckBox (final String id, final boolean required, final String text)
	{
		final CheckBox cb = new CheckBox (text);

		cb.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
		cb.setStyle (
				"-fx-control-inner-background: #" + (required ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK));
		cb.setSelected (false);
		cb.setId (id);

		return cb;
	}

	/*
	 * Get combo boxes
	 */
	public static ComboBox<String> getSurchargeTypeComboBox (final String id)
	{
		ComboBox<String> cb = new ComboBox<> ();

		for (final ESurchargeType e : ESurchargeType.values ())
			cb.getItems ().add (e.getType ());

		cb.getSelectionModel ().selectFirst ();
		cb.setEditable (false);
		cb.setId (id);

		return cb;
	}

	public static ComboBox<String> getVatRateComboBox (final String id)
	{
		ComboBox<String> cb = new ComboBox<> ();

		for (final EVATRate e : EVATRate.values ())
			cb.getItems ().add (e.getVatRateOutput ());

		cb.getSelectionModel ().select (Data.DEFAULT_VAT_RATE);
		cb.setEditable (false);
		cb.setPrefWidth (200);

		cb.setId (id);
		return cb;
	}

	public static ComboBox<String> getInvoiceLineUnitComboBox (final String id)
	{
		ComboBox<String> cb = new ComboBox<> ();

		for (final EUnit e : EUnit.values ())
			cb.getItems ().add (e.getUnitDescription ());

		cb.getSelectionModel ().selectFirst ();
		cb.setEditable (false);
		cb.setPrefWidth (200);

		cb.setId (id);
		return cb;
	}

	/*
	 * Get buttons
	 */
	public static Button getStandardButton (final String id, final String text)
	{
		Button b = new Button (text);

		b.setId (id);
		b.textAlignmentProperty ().set (TextAlignment.CENTER);
		return b;
	}

	/*
	 * Helper methods
	 */

	/*
	 * Show label as enable
	 */
	public static void showLabelAsEnabled (final Label l, final EFormElement e)
	{
		l.setTextFill (Color.BLACK);

		if (e.isRequired () && !l.getText ().endsWith ("*"))
			l.setText (l.getText () + "*");

	}

	/*
	 * Enable text field
	 */
	public static void enableTextField (final TextField t, final EFormElement e)
	{
		boolean isRequired = e.isRequired ();

		t.disableProperty ().set (false);
		t.setStyle ("-fx-control-inner-background: #" + (t.getText () == null || t.getText ().isEmpty ()
				? (isRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
				: Data.BACKGROUND_HEX_OK));
	}

	/*
	 * Enable combo box
	 */
	public static void enableComboBox (final ComboBox<String> c)
	{
		c.disableProperty ().set (false);
	}

	/*
	 * Show label as disabled
	 */
	public static void showLabelAsDisabled (final Label l, final EFormElement e)
	{
		l.setTextFill (Color.DARKGRAY);

		String labelText = l.getText ();
		if (e != null && !e.isRequired () && labelText.endsWith ("*"))
			l.setText (labelText.substring (0, labelText.length () - 1));
	}

	/*
	 * Disable text field
	 */
	public static void disableTextField (final TextField t, final EFormElement e)
	{
		t.disableProperty ().set (true);

		if (e != null)
			t.setStyle ("-fx-control-inner-background: #" + (t.getText () == null || t.getText ().isEmpty ()
					? (e.isRequired () ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
					: Data.BACKGROUND_HEX_OK));

		t.setText ("");
	}

	/*
	 * Disable combo box
	 */
	public static void disableComboBox (final ComboBox<String> c)
	{
		c.disableProperty ().set (true);
	}
}
