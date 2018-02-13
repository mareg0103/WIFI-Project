package at.mareg.ebi43creator.display.form.invoicelines;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.DoubleAsStringUtility;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InvoiceLine extends BasePane
{
	/*
	 * Save list line item this line belongs to
	 */
	private ListLineItem listLineItem;

	/*
	 * Pane elements
	 */
	private GridPane grid;
	private Button removeThisLine;

	private TextField opnField;
	private TextField quantityField;
	private ComboBox<String> unitComboBox;
	private TextField unitPriceField;
	private TextArea descriptionArea;
	private TextField totalNetAmountField;
	private TextField surchargeField;
	private TextField surchargeDescriptionField;
	private ComboBox<String> vatComboBox;
	private CheckBox taxExemptionCheckBox;
	private TextField taxExemptionReasonField;
	private TextField totalGrossAmountField;

	/*
	 * Line variables
	 */
	private Integer orderPositionNumber;
	private Double quantity;
	private String unit;
	private Double unitprice;
	private String description;
	private Double totalNetAmount;
	private Double surcharge;
	private Integer vatRate;
	private String taxexemptionreason;
	private Double totalGrossAmount;

	public InvoiceLine (final ResourceManager resman, final ListLineItem li)
	{
		super (resman);
		listLineItem = li;

		this.init ();
	}

	@Override
	/*
	 * Initialize invoice line, manual build of invoice line necessary because of
	 * needed focus listeners for calculation and direct saving of values
	 */

	protected void init ()
	{
		// super.init ();

		grid = new GridPane ();
		for (int i = 0; i < 4; i++)
		{
			ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 4);
			grid.getColumnConstraints ().add (column);
		}
		grid.setPadding (Data.LINE_PADDING);
		grid.setHgap (Data.LINE_HVGAP);
		grid.setVgap (grid.getHgap ());
		// grid.setGridLinesVisible (true);

		// Order Position Number ****************
		VBox opnBox = new VBox ();

		Label opnLabel;
		if (rm.getInvoiceData ().getInvoiceRecipient ().getOrderReference ().isOrderIDGovernmentOrderNumber ())
		{
			opnLabel = FormElementCreator
					.getStandardLabel (EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.getLabelText (), null);
			opnField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.getID (),
					EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.isRequired ());
		} else
		{
			opnLabel = FormElementCreator
					.getDisabledLookingLabel (EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.getLabelText (), null);
			opnField = FormElementCreator.getDisabledTextField (EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.getID (),
					EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.isRequired ());
		}
		opnField.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{

			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					System.out.println ("opnField is focused");
				} else
				{
					System.out.println ("opnField lost focus");
				}
			}

		});

		opnBox.getChildren ().addAll (opnLabel, opnField);

		VBoxHelper.structureVBox (opnBox);
		grid.add (opnBox, 0, 0);

		// Quantity ****************
		VBox quantityBox = new VBox ();

		Label quantityLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_QUANTITY.getLabelText (),
				null);
		quantityField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_QUANTITY.getID (),
				EFormElement.DETAILS_LINE_QUANTITY.isRequired ());
		quantityField.focusedProperty ().addListener (new ChangeListener<Boolean> ()
		{

			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					System.out.println ("quantityField is focused");
				} else
				{
					System.out.println ("quantityField lost focus");
				}
			}

		});

		quantityBox.getChildren ().addAll (quantityLabel, quantityField);

		VBoxHelper.structureVBox (quantityBox);
		grid.add (quantityBox, 1, 0);

		// Unit ****************
		VBox unitBox = new VBox ();

		Label unitLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_UNIT.getLabelText (), null);
		unitComboBox = FormElementCreator.getInvoiceLineUnitComboBox (EFormElement.DETAILS_LINE_UNIT.getID ());

		unitBox.getChildren ().addAll (unitLabel, unitComboBox);

		VBoxHelper.structureVBox (unitBox);
		grid.add (unitBox, 2, 0);

		// Unit price ****************
		VBox unitPriceBox = new VBox ();

		Label unitPriceLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_UNITPRICE.getLabelText (),
				null);
		unitPriceField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_UNITPRICE.getID (),
				EFormElement.DETAILS_LINE_UNITPRICE.isRequired ());

		unitPriceBox.getChildren ().addAll (unitPriceLabel, unitPriceField);

		VBoxHelper.structureVBox (unitPriceBox);
		grid.add (unitPriceBox, 3, 0);

		// Description ****************
		VBox descriptionBox = new VBox ();

		Label descriptionLabel = FormElementCreator
				.getStandardLabel (EFormElement.DETAILS_LINE_DESCRIPTION.getLabelText (), null);
		descriptionArea = FormElementCreator.getInvoiceLineTextArea (EFormElement.DETAILS_LINE_DESCRIPTION.getID (),
				EFormElement.DETAILS_LINE_DESCRIPTION.isRequired ());

		descriptionBox.getChildren ().addAll (descriptionLabel, descriptionArea);

		VBoxHelper.structureVBox (descriptionBox);
		grid.add (descriptionBox, 0, 1, 2, 2);

		// Total Net Amount ****************
		VBox totalNetBox = new VBox ();

		Label totalNetLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_TOTALNET.getLabelText (),
				null);
		totalNetAmountField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_TOTALNET.getID (),
				EFormElement.DETAILS_LINE_DESCRIPTION.isRequired ());
		totalNetAmountField.setEditable (false);

		totalNetBox.getChildren ().addAll (totalNetLabel, totalNetAmountField);

		VBoxHelper.structureVBox (totalNetBox);
		grid.add (totalNetBox, 2, 1, 2, 1);

		// Surcharge ****************
		VBox surchargeBox = new VBox ();

		Label surchargeLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_SURCHARGE.getLabelText (),
				null);
		surchargeField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_SURCHARGE.getID (),
				EFormElement.DETAILS_LINE_SURCHARGE.isRequired ());

		surchargeBox.getChildren ().addAll (surchargeLabel, surchargeField);

		VBoxHelper.structureVBox (surchargeBox);
		grid.add (surchargeBox, 2, 2);

		// Surcharge description ****************
		VBox surchargeDescriptionBox = new VBox ();

		Label surchargeDescriptionLabel = FormElementCreator
				.getStandardLabel (EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.getLabelText (), null);
		surchargeDescriptionField = FormElementCreator.getStandardTextField (
				EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.getID (),
				EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.isRequired ());

		surchargeDescriptionBox.getChildren ().addAll (surchargeDescriptionLabel, surchargeDescriptionField);

		VBoxHelper.structureVBox (surchargeDescriptionBox);
		grid.add (surchargeDescriptionBox, 3, 2);

		// VAT rate ****************
		VBox vatRateBox = new VBox ();

		Label vatRateLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_VAT.getLabelText (), null);
		vatComboBox = FormElementCreator.getVatRateComboBox (EFormElement.DETAILS_LINE_VAT.getID ());

		vatRateBox.getChildren ().addAll (vatRateLabel, vatComboBox);

		VBoxHelper.structureVBox (vatRateBox);
		grid.add (vatRateBox, 0, 4);

		// Tax exemption ****************
		VBox taxExemptionBox = new VBox ();

		Label taxExemptionLabel = FormElementCreator.getStandardLabel ("", null);
		taxExemptionCheckBox = new CheckBox (EFormElement.DETAILS_LINE_TAXEXEMPTION_CHECK.getLabelText ());

		taxExemptionBox.getChildren ().addAll (taxExemptionLabel, taxExemptionCheckBox);

		VBoxHelper.structureVBox (taxExemptionBox);
		grid.add (taxExemptionBox, 1, 4);

		// Tax exemption description ****************
		VBox taxExemptionReasonBox = new VBox ();

		Label taxExemptionReasonLabel = FormElementCreator
				.getDisabledLookingLabel (EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.getLabelText (), null);
		taxExemptionReasonField = FormElementCreator.getDisabledTextField (
				EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.getID (),
				EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.isRequired ());

		taxExemptionReasonBox.getChildren ().addAll (taxExemptionReasonLabel, taxExemptionReasonField);

		VBoxHelper.structureVBox (taxExemptionReasonBox);
		grid.add (taxExemptionReasonBox, 2, 4);

		// Total Gross Amount ****************
		VBox totalGrosBox = new VBox ();

		Label totalGrossAmountLabel = FormElementCreator
				.getStandardLabel (EFormElement.DETAILS_LINE_TOTALGROSS.getLabelText (), null);
		totalGrossAmountField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_TOTALGROSS.getID (),
				EFormElement.DETAILS_LINE_TOTALGROSS.isRequired ());
		totalGrossAmountField.setEditable (false);

		totalGrosBox.getChildren ().addAll (totalGrossAmountLabel, totalGrossAmountField);

		VBoxHelper.structureVBox (totalGrosBox);
		grid.add (totalGrosBox, 3, 4);

		// Delete button ****************
		removeThisLine = new Button ("",
				new ImageView (new Image ("at/mareg/ebi43creator/display/images/Loeschen_50x33.png")));
		removeThisLine.setOnAction (e -> {
			System.out.println ("listLineItem in dieser InvoiceLine = " + listLineItem.toString ());
			System.out.println ("listLineItem.lineItemAmount: " + listLineItem.getLineItemAmount ());
			System.out.println ("Alle eingetragenen ListLineItems:");
			for (ListLineItem l : rm.getInvoiceData ().getDetails ().getListLineItems ())
				System.out.println (" " + l.toString ());
		});

		grid.add (removeThisLine, 5, 0);

		this.setBorder (new Border (new BorderStroke (Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
				null, BorderWidths.DEFAULT, new Insets (3, 3, 3, 3))));
		this.add (grid, 0, 0);
	}

	/*
	 * Calls all calculate methods below
	 */
	public void calculateLine ()
	{
		_calculateTotalNetAmount ();
		_calculateTotalGrossAmount ();
	}

	/*
	 * Calculates the total net amount of this line
	 */
	public void _calculateTotalNetAmount ()
	{
		totalNetAmount = quantity * unitprice;

		totalNetAmountField.setText (DoubleAsStringUtility.getTwoDigitValue (totalNetAmount));
	}

	/*
	 * Calculates the total gross amount of this line and sets the line item amount
	 * to the list line item
	 */
	public void _calculateTotalGrossAmount ()
	{
		double rate = 1 + ((double) vatRate / 100);
		System.out.println ("In invoiceLine.calculateTotaaGrossAmount ()...");
		System.out.println ("   Rate = " + rate + "(" + vatRate + ")");

		Double lineItemAmount = totalNetAmount + (surcharge == null ? 0 : surcharge.doubleValue ());

		totalGrossAmount = lineItemAmount.doubleValue () * rate;

		totalGrossAmountField.setText (DoubleAsStringUtility.getTwoDigitValue (totalGrossAmount));

		_setLineItemAmountToLidtLineItem (lineItemAmount);
	}

	/*
	 * Saves line item amount in list line item
	 */
	private void _setLineItemAmountToLidtLineItem (final Double lineItemAmount)
	{
		listLineItem.setLineItemAmount (lineItemAmount);
	}

	/*
	 * Return list line item instance save in this line
	 */
	public ListLineItem getListLineItem ()
	{
		return listLineItem;
	}

	/*
	 * Get grid to run through elements to fill them with data
	 */
	public GridPane getGrid ()
	{
		return grid;
	}

	/*
	 * Getter / Setter for line variables
	 */
	public Integer getOrderPositionNumber ()
	{
		return orderPositionNumber;
	}

	public void setOrderPositionNumber (Integer orderPositionNumber)
	{
		this.orderPositionNumber = orderPositionNumber;
	}

	public Double getQuantity ()
	{
		return quantity;
	}

	public void setQuantity (Double quantity)
	{
		this.quantity = quantity;
	}

	public String getUnit ()
	{
		return unit;
	}

	public void setUnit (String unit)
	{
		this.unit = unit;
	}

	public Double getUnitprice ()
	{
		return unitprice;
	}

	public void setUnitprice (Double unitprice)
	{
		this.unitprice = unitprice;
	}

	public String getDescription ()
	{
		return description;
	}

	public void setDescription (String description)
	{
		this.description = description;
	}

	public Double getTotalNetAmount ()
	{
		return totalNetAmount;
	}

	public void setTotalNetAmount (Double totalNetAmount)
	{
		this.totalNetAmount = totalNetAmount;
	}

	public Double getSurcharge ()
	{
		return surcharge;
	}

	public void setSurcharge (Double surcharge)
	{
		this.surcharge = surcharge;
	}

	public Integer getVatRate ()
	{
		return vatRate;
	}

	public void setVatRate (Integer vatRate)
	{
		this.vatRate = vatRate;
	}

	public String getTaxexemptionreason ()
	{
		return taxexemptionreason;
	}

	public void setTaxexemptionreason (String taxexemptionreason)
	{
		this.taxexemptionreason = taxexemptionreason;
	}

	public Double getTotalGrossAmount ()
	{
		return totalGrossAmount;
	}

	public void setTotalGrossAmount (Double totalGrossAmount)
	{
		this.totalGrossAmount = totalGrossAmount;
	}
}