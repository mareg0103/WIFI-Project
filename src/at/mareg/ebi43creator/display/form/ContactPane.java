package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ContactPane extends BasePane
{

	private int billerCol = 0;
	private int billerRow = 0;
	private int invoiceRecipientCol = 0;
	private int invoiceRecipientRow = 0;

	public ContactPane (final ResourceManager resman)
	{

		super (resman);

		init ();

	}

	@Override
	protected void init ()
	{

		super.init ();

		final Accordion ac = new Accordion ();

		final TitledPane tp_Biller = new TitledPane ();
		tp_Biller.setText (Data.TITLEDPANE_BILLER);

		final TitledPane tp_InvoiceRecipient = new TitledPane ();
		tp_InvoiceRecipient.setText (Data.TITLEDPANE_INVOICERECIPIENT);

		final GridPane grid_Biller = new GridPane ();
		grid_Biller.setPadding (Data.BASEPANE_PADDING);
		grid_Biller.setHgap (Data.BASEPANE_HVGAP);
		grid_Biller.setVgap (this.getHgap ());

		final GridPane grid_InvoiceRecipient = new GridPane ();
		grid_InvoiceRecipient.setPadding (Data.BASEPANE_PADDING);
		grid_InvoiceRecipient.setHgap (Data.BASEPANE_HVGAP);
		grid_InvoiceRecipient.setVgap (this.getHgap ());

		for (final EFormFields eb : EFormFields.values ())
		{

			if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_BILLER))
			{

				final boolean isRequired = eb.isRequired ();

				final VBox v = new VBox ();

				final Label l = new Label (eb.getLabelText ());
				v.getChildren ().add (l);

				final TextField t = FormElementCreator.getStandardTextField (eb.getID (), isRequired);
				v.getChildren ().add (t);

				if (isRequired)
					RequiredAndErrorHelper.incrementTabCount (eb.getTiteldPaneID ());

				grid_Biller.add (v, billerCol, billerRow);
				VBoxHelper.structureVBox (v);

				incrementBillerCol ();

			}

			if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_INVOICERECIPIENT))
			{

				final boolean isRequired = eb.isRequired ();

				final VBox v = new VBox ();

				final Label l = new Label (eb.getLabelText ());
				v.getChildren ().add (l);

				final TextField t = FormElementCreator.getStandardTextField (eb.getID (), isRequired);
				v.getChildren ().add (t);

				if (isRequired)
					RequiredAndErrorHelper.incrementTabCount (eb.getTiteldPaneID ());

				grid_InvoiceRecipient.add (v, invoiceRecipientCol, invoiceRecipientRow);
				VBoxHelper.structureVBox (v);

				incrementInvoiceRecipientCol ();

			}

		}

		tp_Biller.setContent (grid_Biller);
		tp_InvoiceRecipient.setContent (grid_InvoiceRecipient);

		ac.getPanes ().addAll (tp_Biller, tp_InvoiceRecipient);
		ac.setExpandedPane (tp_Biller);

		this.add (ac, 0, 0);

	}

	public void incrementBillerCol ()
	{
		billerCol++;

		if (billerCol == Data.COLUMN_COUNT_PER_ROW)
		{
			billerCol = 0;
			billerRow++;
		}
	}

	public void incrementInvoiceRecipientCol ()
	{
		invoiceRecipientCol++;

		if (invoiceRecipientCol == Data.COLUMN_COUNT_PER_ROW)
		{
			invoiceRecipientCol = 0;
			invoiceRecipientRow++;
		}
	}

}
