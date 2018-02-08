package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLine;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public final class InvoiceLineFiller
{
	private static InvoiceData invoiceData;

	private InvoiceLineFiller ()
	{
	}

	/*
	 * Fill invoice line with loaded data
	 */
	public static void fillLineWithLoadedData (final InvoiceLine il)
	{
		ListLineItem lli = il.getListLineItem ();
		GridPane grid = il.getGrid ();

		for (final Node n : grid.getChildren ())
		{
			if (n.getClass () == VBox.class)
			{
				for (final Node vn : ((VBox) n).getChildren ())
				{
					if (vn.getClass () == TextField.class)
					{
						String returnValue = LoadInvoiceDataMapper
								.callMethodFor (EFormElement.getFromIDOrNull (vn.getId ()), lli);

						if (returnValue != null)
							((TextField) vn).setText (returnValue);
						System.out.println (returnValue);
					}
				}
			}
		}
	}

	public static void setInvoiceData (final InvoiceData id)
	{
		invoiceData = id;
	}
}
