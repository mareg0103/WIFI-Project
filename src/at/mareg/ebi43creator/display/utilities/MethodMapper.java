package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.invoicedata.InvoiceData;

public final class MethodMapper
{

	private static InvoiceData invoiceData = null;

	private MethodMapper ()
	{
	}

	public static final void callMethodFor (final String field, final String value)
	{
		switch (field)
		{

			// case EBiller.NAME.getID ():
			// invoiceData.getBiller ().getAddress ().setName (value);
			// break;
			//
			// case EBiller.STREET.getID ():
			// invoiceData.getBiller ().getAddress ().setStreet (value);
			// break;
			//
			default:
				System.out.println ("Keine Methode für Feld '" + field + "' vorhanden!");
		}
	}

	public static void setInvoiceData (final InvoiceData data)
	{
		invoiceData = data;
	}

}
