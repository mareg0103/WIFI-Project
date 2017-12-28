package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.invoicedata.InvoiceData;

public final class SaveMethodMapper
{

	private static InvoiceData invoiceData = null;

	private SaveMethodMapper ()
	{
	}

	public static final void callMethodFor (final String field, final String value)
	{
		switch (field)
		{

			default:
				System.out.println ("Keine Methode für Feld '" + field + "' vorhanden!");
		}
	}

	public static void setInvoiceData (final InvoiceData data)
	{
		invoiceData = data;
	}

}
