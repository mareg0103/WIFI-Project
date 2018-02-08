package at.mareg.ebi43creator.display.form.invoicelines;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

	/*
	 * Create form control variables
	 */
	private int lineCol;
	private int lineRow;
	private boolean textAreaIsSet;

	private Double totalNetAmount;
	private Double totalNetAmountOld;
	private Double totalGrossAmount;
	private Double totalGrossAmountOld;

	public InvoiceLine (final ResourceManager resman, final ListLineItem li)
	{
		super (resman);
		listLineItem = li;

		lineCol = 0;
		lineRow = 0;
		textAreaIsSet = false;

		init ();
	}

	@Override
	protected void init ()
	{
		// super.init ();

		grid = new GridPane ();
		for (int i = 0; i < 4; i++)
		{
			ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 210) / 4);
			grid.getColumnConstraints ().add (column);
		}
		grid.setPadding (Data.LINE_PADDING);
		grid.setHgap (Data.LINE_HVGAP);
		grid.setVgap (this.getHgap ());

		for (final EFormElement eb : EFormElement.values ())
		{
			if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_DETAILS))
			{
				final boolean isRequired = eb.isRequired ();
				final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
				final String elementID = eb.getID ();
				final String elementType = eb.getType ();

				if (elementType == Data.ELEMENTTYPE_TEXTFIELD)
				{
					final VBox v = new VBox ();

					v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
					v.getChildren ().add (FormElementCreator.getInvoiceLineTextField (elementID, isRequired));

					grid.add (v, lineCol, lineRow);
					VBoxHelper.structureVBox (v);

					_incrementLineCol ();
				}

				if (elementType == Data.ELEMENTTYPE_COMBOBOX)
				{
					final VBox v = new VBox ();

					v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
					v.getChildren ().add (FormElementCreator.getUnitComboBox (elementID));

					grid.add (v, lineCol, lineRow);
					VBoxHelper.structureVBox (v);

					_incrementLineCol ();
				}

				if (eb.getType () == Data.ELEMENTTYPE_TEXTAREA)
				{
					final VBox v = new VBox ();

					v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
					v.getChildren ().add (FormElementCreator.getStandardTextArea (elementID, isRequired));

					while (lineCol != 0)
						_incrementLineCol ();

					grid.add (v, lineCol, lineRow, 4, 1);
					VBoxHelper.structureVBox (v);

					textAreaIsSet = true;

					_incrementLineCol ();
				}

				if (isRequired)
					RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);
			}
		}

		removeThisLine = new Button ("Zeige listLineItem-ID");
		removeThisLine.setOnAction (e -> {
			System.out.println ("listLineItem in dieser InvoiceLine = " + listLineItem.toString ());
			System.out.println ("Alle eingetragenen ListLineItems:");
			for (ListLineItem l : rm.getInvoiceData ().getDetails ().getListLineItems ())
				System.out.println ("   " + l.toString ());
		});

		this.add (grid, 0, 0);
	}

	/*
	 * Calculate new column and row values for mostly automated creation of the form
	 */
	private void _incrementLineCol ()
	{
		lineCol++;

		if (textAreaIsSet)
		{
			lineCol = 0;
			lineRow += 2;

			textAreaIsSet = false;
		} else
		{
			if (lineCol == Data.DETAILS_LINE_COLUMN_COUNT_PER_ROW)
			{
				lineCol = 0;
				lineRow++;
			}
		}

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

	public Double getTotalNetAmount ()
	{
		return totalNetAmount;
	}

	public void setTotalNetAmount (Double totalNetAmount)
	{
		this.totalNetAmount = totalNetAmount;
	}

	public Double getTotalNetAmountOld ()
	{
		return totalNetAmountOld;
	}

	public void setTotalNetAmountOld (Double totalNetAmountOld)
	{
		this.totalNetAmountOld = totalNetAmountOld;
	}

	public Double getTotalGrossAmount ()
	{
		return totalGrossAmount;
	}

	public void setTotalGrossAmount (Double totalGrossAmount)
	{
		this.totalGrossAmount = totalGrossAmount;
	}

	public Double getTotalGrossAmountOld ()
	{
		return totalGrossAmountOld;
	}

	public void setTotalGrossAmountOld (Double totalGrossAmountOld)
	{
		this.totalGrossAmountOld = totalGrossAmountOld;
	}
}
